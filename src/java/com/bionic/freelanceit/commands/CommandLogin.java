/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.freelanceit.commands;

import com.bionic.freelanceit.entity.User;
import com.bionic.freelanceit.dao.UserDAO;
import com.bionic.freelanceit.factory.DaoFactory;
import com.bionic.freelanceit.manager.Config;
import com.bionic.freelanceit.manager.Message;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author qz
 */
public class CommandLogin implements ICommand {

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String NAME = "name";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        UserDAO userDao = DaoFactory.getUserDAO();

        if (userDao.find(login, password)) {
            User user = userDao.findByLogin(login);
            request.setAttribute("name", user.getName());
            request.setAttribute("login", login);
            request.getSession(true).setAttribute("user", user);
            page = Config.getInstance().getProperty(Config.MAIN);
        } else {
            request.setAttribute("error", Message.getInstance().getProperty(Message.LOGIN_ERROR));
            page = Config.getInstance().getProperty(Config.ERROR);
        }
        return page;
    }
}
