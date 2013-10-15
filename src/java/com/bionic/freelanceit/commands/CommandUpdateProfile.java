/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.freelanceit.commands;

import com.bionic.freelanceit.entity.User;
import com.bionic.freelanceit.dao.UserDAO;
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
public class CommandUpdateProfile implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        User user = null;
        HttpSession session = request.getSession(false);
        UserDAO userDao = DaoFactory.getUserDao();
        String name = request.getParameter(userDao.NAME);
        String lastName = request.getParameter(userDao.LAST_NAME);
        String email = request.getParameter(userDao.EMAIL);
        String birthday = request.getParameter(userDao.BIRTHDAY);
        String password = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String rPassword = request.getParameter("repeatPassword");
        String active = request.getParameter(userDao.ACTIVE);

        if (session != null) {
            user = userDao.findById((Integer) session.getAttribute("id"));

            if (birthday != null) {
                user.setBirthday(Date.valueOf(birthday));
            }
            if (email != null) {
                user.setEmail(email);
            }
            if (lastName != null) {
                user.setLastName(lastName);
            }
            if (name != null) {
                user.setName(name);
            }
            if (password != null && password.equals(user.getPassword()) && newPassword != null && newPassword.equals(rPassword)) {
                user.setPassword(newPassword);
            }
            if (active != null) {
                user.setActive(Boolean.parseBoolean(active));
            }
            userDao.update(user);

            page = Config.getInstance().getProperty(Config.PROFILE);
        }
        else {
            page = Config.getInstance().getProperty(Config.LOGIN);
        }
        request.setAttribute("user", user);
        return page;
    }
}
