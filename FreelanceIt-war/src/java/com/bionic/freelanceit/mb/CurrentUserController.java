/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.freelanceit.mb;

import com.bionic.freelanceit.entities.Task;
import com.bionic.freelanceit.entities.User;
import com.bionic.freelanceit.entitybeans.user.UserFacadeLocal;
import com.bionic.freelanceit.manager.Config;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author qz
 */
@ManagedBean
@SessionScoped
public class CurrentUserController {

    @EJB
    private UserFacadeLocal userFacade;
    private User user;
    private String currentUserLogin;
    private Collection<Task> taskCollection;

    public CurrentUserController() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        currentUserLogin = request.getUserPrincipal().getName();
        taskCollection = new ArrayList<>();
    }

    public String viewMyProfile() {
        getCurrentUser();
        return Config.getInstance().getProperty(Config.PROFILE);
    }

    public String viewChangeProfile() {
        getCurrentUser();
        return Config.getInstance().getProperty(Config.CHANGE_PROFILE);
    }

    public String updateProfile() {
        userFacade.edit(user);
        return Config.getInstance().getProperty(Config.PROFILE);
    }

    public String viewCreateTask() {
        return Config.getInstance().getProperty(Config.CREATE_TASK);
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
        } catch (ServletException ex) {
            Logger.getLogger(CurrentUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Config.getInstance().getProperty(Config.INDEX);
    }

    public String viewMyTasks() {
        getCurrentUser();
        taskCollection = userFacade.getTasksHeCreated(user);
        return Config.getInstance().getProperty(Config.TASKS_CREATED_BY_ME);
    }

    public User getUser() {
        return user;
    }

    public String getCurrentUserLogin() {
        return currentUserLogin;
    }

    public Collection<Task> getTaskCollection() {
        return taskCollection;
    }

    public void getCurrentUser() {
        if (user == null) {
            user = userFacade.findByLogin(currentUserLogin);
        }
    }
}
