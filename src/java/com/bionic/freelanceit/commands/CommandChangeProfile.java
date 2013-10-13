/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.freelanceit.commands;

import com.bionic.freelanceit.dao.UserDAO;
import com.bionic.freelanceit.entity.User;
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
public class CommandChangeProfile implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDao = DaoFactory.getUserDao();
        HttpSession session = request.getSession(false);
        String page = null;
        User user = null;

        if (session == null) {
            page = Config.getInstance().getProperty(Config.LOGIN);
        } else {
            user = userDao.findById((Integer) session.getAttribute("id"));
            request.setAttribute("user", user);
            page = Config.getInstance().getProperty(Config.CHANGE_PROFILE);
        }
        return page;
    }
}
