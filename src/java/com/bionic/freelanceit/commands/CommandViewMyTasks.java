package com.bionic.freelanceit.commands;

import com.bionic.freelanceit.entity.User;
import com.bionic.freelanceit.entitybeans.user.UserFacadeLocal;
import com.bionic.freelanceit.manager.Config;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author qz
 */
@EJBs({
    @EJB(name="UserFacadeLocal", beanInterface=UserFacadeLocal.class),
})
public class CommandViewMyTasks implements ICommand {
    private UserFacadeLocal userEJB = null;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        HttpSession session = request.getSession(false);
        userEJB = lookupUserFacadeLocal();
        User user = null;
        if (session != null) {
            user = (User) session.getAttribute("user");
            request.setAttribute("taskCollection", userEJB.findHisTasks(user));
            page = Config.getInstance().getProperty(Config.VIEW_MY_TASKS);
        } else {
            page = Config.getInstance().getProperty(Config.LOGIN);
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
