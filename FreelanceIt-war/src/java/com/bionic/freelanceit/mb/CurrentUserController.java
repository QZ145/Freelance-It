/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.freelanceit.mb;

import com.bionic.freelanceit.entities.Task;
import com.bionic.freelanceit.entities.User;
import com.bionic.freelanceit.entities.UserTask;
import com.bionic.freelanceit.entitybeans.user.UserFacadeLocal;
import com.bionic.freelanceit.manager.Config;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author qz
 */
@ManagedBean
@SessionScoped
public class CurrentUserController {

    @Resource(mappedName = "queueDestination")
    private Queue queueDestination;
    @Inject
    @JMSConnectionFactory("connectionFactory")
    private JMSContext context;
    @EJB
    private UserFacadeLocal userFacade;
    private User user;
    private String currentUserLogin;
    private Task task;

    public CurrentUserController() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        user = new User();
        task = new Task();
        currentUserLogin = request.getUserPrincipal().getName();
    }

    public String viewMyProfile() {
        user = userFacade.findByLogin(currentUserLogin);
        return Config.getInstance().getProperty(Config.PROFILE);
    }

    public String viewChangeProfile() {
        user = userFacade.findByLogin(currentUserLogin);
        return Config.getInstance().getProperty(Config.CHANGE_PROFILE);
    }

    public String updateProfile() {
        userFacade.edit(user);
        return Config.getInstance().getProperty(Config.PROFILE);
    }

    public String viewCreateTask() {
        return Config.getInstance().getProperty(Config.CREATE_TASK);
    }

    public String createTask() {
        UserTask ut = new UserTask();
        ut.setRelationType("creator");
        ut.setUser(userFacade.findByLogin(currentUserLogin));
        Collection<UserTask> userTaskCollection = new ArrayList<>();
        userTaskCollection.add(ut);
        task.setUserTaskCollection(userTaskCollection);
        task.setStatus(true);
        task.setDateOfCreation(new Date());
        ObjectMessage message = context.createObjectMessage(task);
        sendJMSMessageToQueueDestination(message);
        return Config.getInstance().getProperty(Config.MAIN);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCurrentUserLogin() {
        return currentUserLogin;
    }

    public void setCurrentUserLogin(String currentUserLogin) {
        this.currentUserLogin = currentUserLogin;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    private void sendJMSMessageToQueueDestination(Message message) {
        context.createProducer().send(queueDestination, message);
    }
}
