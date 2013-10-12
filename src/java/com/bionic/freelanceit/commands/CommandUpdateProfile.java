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

/**
 *
 * @author qz
 */
public class CommandUpdateProfile implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String page = null;
       UserDAO userDao = DaoFactory.getUserDao();
       String name = request.getParameter(userDao.NAME);
       String lastName = request.getParameter(userDao.LAST_NAME);
       String email = request.getParameter(userDao.EMAIL);
       String birthday = request.getParameter(userDao.BIRTHDAY);
       String password = request.getParameter(userDao.PASSWORD);
       String active = request.getParameter(userDao.ACTIVE);
       String login = request.getParameter(userDao.LOGIN);
       
       User user = userDao.findByLogin(login);
       user.setBirthday(Date.valueOf(birthday));
       user.setEmail(email);
       user.setLastName(lastName);
       user.setName(name);
       user.setPassword(password);
       user.setActive(Boolean.parseBoolean(active));
       userDao.update(user);
       
       page = Config.getInstance().getProperty(Config.PROFILE);
       return page;
    }
    
}
