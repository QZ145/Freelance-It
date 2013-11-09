/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.freelanceit.commands;

import com.bionic.freelanceit.dao.TaskDAO;
import com.bionic.freelanceit.dao.UserTaskDAO;
import com.bionic.freelanceit.entity.Task;
import com.bionic.freelanceit.entity.User;
import com.bionic.freelanceit.entity.UserTask;
import com.bionic.freelanceit.entity.UserTaskPK;
import com.bionic.freelanceit.entitybeans.task.TaskFacade;
import com.bionic.freelanceit.entitybeans.task.TaskFacadeLocal;
import com.bionic.freelanceit.entitybeans.user.UserFacadeLocal;
import com.bionic.freelanceit.entitybeans.usertask.UserTaskFacadeLocal;
import com.bionic.freelanceit.factory.DaoFactory;
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
    @EJB(name = "TaskFacadeLocal", beanInterface = TaskFacadeLocal.class)
})
public class CommandCreateTask implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TaskFacadeLocal taskEJB = null;
        UserTaskFacadeLocal userTaskEJB = null;
        UserTask userTask = null;
        Task task = null;
        String page = null;
        HttpSession session = request.getSession(false);


        if (session != null) {
            if (request.getParameter("mode").equals("view")) {
                page = Config.getInstance().getProperty(Config.CREATE_TASK);
            } else {
                String title = request.getParameter("title");
                String description = request.getParameter("description");
                taskEJB = lookupTaskFacadeLocal();
                userTaskEJB = lookupUserTaskFacadeLocal();
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
                taskEJB.create(task);
                User user = (User) session.getAttribute("user");
                userTask.setRelationType("creator");
                UserTaskPK userTaskPK = new UserTaskPK(user.getId(), task.getId());
                userTask.setUserTaskPK(userTaskPK);
                userTaskEJB.create(userTask);
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
            Logger.getLogger(CommandLogin.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
    }

    private UserTaskFacadeLocal lookupUserTaskFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (UserTaskFacadeLocal) c.lookup("java:comp/env/UserTaskFacadeLocal");
        } catch (NamingException ex) {
            Logger.getLogger(CommandLogin.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
    }
}
