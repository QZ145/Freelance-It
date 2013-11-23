/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.freelanceit.mb;

import com.bionic.freelanceit.entities.User;
import com.bionic.freelanceit.entitybeans.user.UserFacadeLocal;
import com.bionic.freelanceit.manager.Config;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author qz
 */
@ManagedBean
public class UserController {

    @EJB
    private UserFacadeLocal userFacade;
    private User user;
    private String currentUserLogin;
    private Long idUser;

    public UserController() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        user = new User();
        currentUserLogin = request.getUserPrincipal().getName();
    }

    public String doViewProfile() {
        if (idUser != null) {
            user = userFacade.find(idUser);
        } else {
            user = userFacade.findByLogin(currentUserLogin);
        }
        return Config.getInstance().getProperty(Config.PROFILE);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCurrentUserLogin() {
        return currentUserLogin;
    }

    public void setCurrentUserLogin(String currentUserLogin) {
        this.currentUserLogin = currentUserLogin;
    }
}
