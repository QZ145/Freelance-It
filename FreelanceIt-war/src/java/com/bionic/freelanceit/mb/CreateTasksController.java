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
import javax.faces.bean.RequestScoped;
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
@RequestScoped
public class CreateTasksController {

    @Resource(mappedName = "queueDestination")
    private Queue queueDestination;
    @Inject
    @JMSConnectionFactory("connectionFactory")
    private JMSContext context;
    @EJB
    private UserFacadeLocal userFacade;
    private Task task;
    private String currentUserLogin;

    public CreateTasksController() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        currentUserLogin = request.getUserPrincipal().getName();
        task = new Task();
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
        task = new Task();
        return Config.getInstance().getProperty(Config.MAIN);
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
