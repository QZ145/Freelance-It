/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.freelanceit.commands;

import com.bionic.freelanceit.entities.Task;
import com.bionic.freelanceit.entities.User;
import com.bionic.freelanceit.entitybeans.task.TaskFacadeLocal;
import com.bionic.freelanceit.entities.UserInfo;
import com.bionic.freelanceit.manager.Config;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class CommandEditTask implements ICommand {

    TaskFacadeLocal taskFacade = lookupTaskFacadeLocal();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        Task task = null;
        User creator = null;
        UserInfo userInfo = null;
        HttpSession session = request.getSession(false);
        Long idTask = Long.valueOf(request.getParameter("idTask"));
        if (session != null && session.getAttribute(UserInfo.USER_INFO) != null) {
            userInfo = (UserInfo) session.getAttribute(UserInfo.USER_INFO);
            task = taskFacade.find(idTask);
            creator = taskFacade.getCreator(task);
//            if(!creator.getId().equals(userInfo.getId()));

        } else {
            page = Config.getInstance().getProperty(Config.LOGIN);
        }
        return page;
    }

    private TaskFacadeLocal lookupTaskFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (TaskFacadeLocal) c.lookup("java:global/FreelanceIt/FreelanceIt-ejb/TaskFacade!com.bionic.freelanceit.entitybeans.task.TaskFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
