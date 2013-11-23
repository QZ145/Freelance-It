/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.freelanceit.commands;

import com.bionic.freelanceit.entities.Task;
import com.bionic.freelanceit.entities.User;
import com.bionic.freelanceit.entities.UserTask;
import com.bionic.freelanceit.entitybeans.task.TaskFacadeLocal;
import com.bionic.freelanceit.entitybeans.user.UserFacadeLocal;
import com.bionic.freelanceit.entitybeans.usertask.UserTaskFacadeLocal;
import com.bionic.freelanceit.entities.UserInfo;
import com.bionic.freelanceit.manager.Config;
import java.io.IOException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBs;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author qz
 */
@EJBs({
    @EJB(name = "UserTaskFacadeLocal", beanInterface = UserTaskFacadeLocal.class),
    @EJB(name = "TaskFacadeLocal", beanInterface = TaskFacadeLocal.class),
    @EJB(name = "UserFacadeLocal", beanInterface = UserFacadeLocal.class)
})
public class CommandCreateTask implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TaskFacadeLocal taskFacade = null;
        UserTaskFacadeLocal userTaskFacade = null;
        UserFacadeLocal userFacade = null;
        UserTask userTask = null;
        User user = null;
        Task task = null;
        String page = null;
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute(UserInfo.USER_INFO) != null) {
            if (request.getParameter("mode").equals("view")) {
                page = Config.getInstance().getProperty(Config.CREATE_TASK);
            } else {
//                String title = request.getParameter("title");
//                String description = request.getParameter("description");
                String title = new String(request.getParameter("title").getBytes(
                        "iso-8859-1"), "UTF-8");
                String description = new String(request.getParameter("description").getBytes(
                        "iso-8859-1"), "UTF-8");
                taskFacade = lookupTaskFacadeLocal();
                userTaskFacade = lookupUserTaskFacadeLocal();
                userFacade = lookupUserFacadeLocal();
                task = new Task();
                userTask = new UserTask();
                if (title != null) {
                    task.setTitle(title);
                }
                if (description != null) {
                    task.setDescription(description);
                }
                task.setStatus(true);
                task.setDateOfCreation(new Date(2010, 11, 15));
                taskFacade.create(task);
                UserInfo userInfo = (UserInfo) session.getAttribute(UserInfo.USER_INFO);
//                user = userFacade.find(userInfo.getId());
                userTask.setTask(task);
                userTask.setUser(user);
                userTask.setRelationType("creator");
                userTaskFacade.create(userTask);
                page = Config.getInstance().getProperty(Config.MAIN);
            }
        } else {
            page = Config.getInstance().getProperty(Config.LOGIN);
        }
        return page;
    }

    private TaskFacadeLocal lookupTaskFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (TaskFacadeLocal) c.lookup("java:comp/env/TaskFacadeLocal");
        } catch (NamingException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
    }

    private UserTaskFacadeLocal lookupUserTaskFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (UserTaskFacadeLocal) c.lookup("java:comp/env/UserTaskFacadeLocal");
        } catch (NamingException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
    }

    private UserFacadeLocal lookupUserFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (UserFacadeLocal) c.lookup("java:comp/env/UserFacadeLocal");
        } catch (NamingException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
    }
}
