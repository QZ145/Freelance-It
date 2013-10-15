/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.freelanceit.commands;

import com.bionic.freelanceit.dao.TaskDAO;
import com.bionic.freelanceit.entity.Task;
import com.bionic.freelanceit.factory.DaoFactory;
import com.bionic.freelanceit.manager.Config;
import java.io.IOException;
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
        TaskDAO taskDao = DaoFactory.getTaskDao();
        Task task = null;
        String page = null;
        HttpSession session = request.getSession(false);
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        if (session != null) {
            task = new Task();
            task.setIdOwner((Integer) session.getAttribute("id"));
            if (title != null) {
                task.setTitle(title);
            }
            if(description != null) {
                task.setDescription(description);
            }
            taskDao.add(task);
            page = Config.getInstance().getProperty(Config.MAIN);
        }
        else {
            page = Config.getInstance().getProperty(Config.LOGIN);
        }
        return page;
    }
}
