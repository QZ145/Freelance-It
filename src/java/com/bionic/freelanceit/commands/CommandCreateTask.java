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
import com.bionic.freelanceit.factory.DaoFactory;
import com.bionic.freelanceit.manager.Config;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author qz
 */
public class CommandCreateTask implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TaskDAO taskDao = DaoFactory.getTaskDAO();
        UserTaskDAO userTaskDAO = DaoFactory.getUserTaskDAO();
        UserTask userTask = null;
        Task task = null;
        String page = null;
        HttpSession session = request.getSession(false);
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        if (session != null) {
            if (request.getParameter("mode").equals("view")) {
                page = Config.getInstance().getProperty(Config.CREATE_TASK);
            } else {
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
                task = taskDao.add(task);
                User user = (User) session.getAttribute("user");
                userTask.setRelationType("creator");
                UserTaskPK userTaskPK = new UserTaskPK(user.getId(), task.getId());
                userTask.setUserTaskPK(userTaskPK);
                userTaskDAO.add(userTask);
                page = Config.getInstance().getProperty(Config.MAIN);
            }
        } else {
            page = Config.getInstance().getProperty(Config.LOGIN);
        }
        return page;
    }
}
