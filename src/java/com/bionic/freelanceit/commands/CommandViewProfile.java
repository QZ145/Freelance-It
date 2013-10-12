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
public class CommandViewProfile implements ICommand {
    private User user = null;
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDao = DaoFactory.getUserDao();
        HttpSession session = request.getSession(false);
        String page = null;
        
        if(session == null) {
            page = Config.getInstance().getProperty(Config.LOGIN);
        }
        else {
            user = userDao.findById(new Integer((String) session.getAttribute("id")));
            request.setAttribute(userDao.NAME, user.getName());
            request.setAttribute(userDao.LAST_NAME, user.getLastName());
            request.setAttribute(userDao.EMAIL, user.getEmail());
            request.setAttribute(userDao.LOGIN, user.getLogin());
            request.setAttribute(userDao.ACTIVE, user.getActive());
            request.setAttribute(userDao.BIRTHDAY, user.getBirthday());
            request.setAttribute(userDao.DATE_OF_REGISTRATION, user.getDateOfRegistration());
            request.setAttribute(userDao.PASSWORD, user.getPassword());
            page=Config.getInstance().getProperty(Config.PROFILE);
        }
        return page;
    }
    
}
