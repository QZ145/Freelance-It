package com.bionic.freelanceit.commands;

import com.bionic.freelanceit.entities.User;
import com.bionic.freelanceit.entitybeans.user.UserFacadeLocal;
import com.bionic.freelanceit.entities.UserInfo;
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
    @EJB(name = "UserFacadeLocal", beanInterface = UserFacadeLocal.class),})
public class CommandViewTasksOfUser implements ICommand {

    private UserFacadeLocal userFacade = null;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        Long id = null;
        HttpSession session = request.getSession(false);
        userFacade = lookupUserFacadeLocal();
        User user = null;
        if (session != null && session.getAttribute(UserInfo.USER_INFO) != null) {
            id = Long.valueOf(request.getParameter("id"));
            user = userFacade.find(id);
            request.setAttribute("taskCollection", userFacade.getTasksHeCreated(user));
            page = Config.getInstance().getProperty(Config.VIEW_TASKS_CREATED_BY_USER);
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
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
    }
}
