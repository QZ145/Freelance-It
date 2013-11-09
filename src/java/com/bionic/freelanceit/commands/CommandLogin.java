/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.freelanceit.commands;

import com.bionic.freelanceit.entity.User;
import com.bionic.freelanceit.entitybeans.user.UserFacadeLocal;
import com.bionic.freelanceit.manager.Config;
import com.bionic.freelanceit.manager.Message;
import java.io.IOException;
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

/**
 *
 * @author qz
 */
@EJBs({
       @EJB(name="UserFacadeLocal", beanInterface=UserFacadeLocal.class)
})
public class CommandLogin implements ICommand {

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private UserFacadeLocal userEJB = null;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        userEJB = lookupUserFacadeLocal();
        String page = null;
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        User user = userEJB.findByLogin(login);
        if (user != null && user.getPassword().equals(password)) {
            page = Config.getInstance().getProperty(Config.MAIN);
            request.getSession(true).setAttribute("user", user);
        } else {
            request.setAttribute("error", Message.getInstance().getProperty(Message.LOGIN_ERROR));
            page = Config.getInstance().getProperty(Config.ERROR);
        }
        return page;
    }
    
    private UserFacadeLocal lookupUserFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (UserFacadeLocal) c.lookup("java:comp/env/UserFacadeLocal");
        } catch (NamingException ex) {
            Logger.getLogger(CommandLogin.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
    }
}
