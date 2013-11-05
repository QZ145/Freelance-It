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
public class CommandViewMyProfile implements ICommand {
  
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDao = DaoFactory.getUserDAO();
        HttpSession session = request.getSession(false);
        String page = null;
        User user = null;
        
        if(session != null) {
            page = Config.getInstance().getProperty(Config.PROFILE);
        }
        else {
            page=Config.getInstance().getProperty(Config.LOGIN);
        }
        return page;
    }
    
}