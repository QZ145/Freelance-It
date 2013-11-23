/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.freelanceit.commands;

import com.bionic.freelanceit.entities.User;
import com.bionic.freelanceit.entitybeans.user.UserFacadeLocal;
import com.bionic.freelanceit.entities.UserInfo;
import com.bionic.freelanceit.manager.Config;
import java.io.IOException;
import java.sql.Date;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author qz
 */
@EJBs({
    @EJB(name = "UserFacadeLocal", beanInterface = UserFacadeLocal.class)
})
public class CommandEditProfile implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        User user = null;
        UserInfo userInfo = null;
        UserFacadeLocal userFacade = null;
        HttpSession session = request.getSession(false);
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String birthday = request.getParameter("birthday");
        String password = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String rPassword = request.getParameter("repeatPassword");
//        String active = request.getParameter("status");

        if (session != null && session.getAttribute(UserInfo.USER_INFO) != null) {
            userFacade = lookupUserFacadeLocal();
            userInfo = (UserInfo) session.getAttribute("userInfo");
            user = userFacade.findByLogin(userInfo.getLogin());
            if (request.getParameter("mode").equals("view")) {
                page = Config.getInstance().getProperty(Config.CHANGE_PROFILE);
            } else {
                if (birthday != null) {
                    try {
//                        user.setBirthday(Date.valueOf(birthday));
                    } catch (IllegalArgumentException e) {
                    }
                }
                if (email != null) {
//                    user.setEmail(email);
                }
                if (lastName != null) {
//                    user.setLastName(lastName);
                }
                if (name != null) {
//                    user.setName(name);
                }
                if (password != null && password.equals(user.getPassword()) && newPassword != null && newPassword.equals(rPassword)) {
                    user.setPassword(newPassword);
                }
//                if (active != null) {
//                    user.setStatus(Boolean.parseBoolean(active));
//                }
                userFacade.edit(user);
//                userInfo.setFields(user);
                session.setAttribute("userInfo", userInfo);
                page = Config.getInstance().getProperty(Config.PROFILE);
            }
        } else {
            page = Config.getInstance().getProperty(Config.LOGIN);
        }
        request.setAttribute("user", user);
        return page;
    }

    private UserFacadeLocal lookupUserFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (UserFacadeLocal) c.lookup("java:comp/env/UserFacadeLocal");
        } catch (NamingException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
    }
}
