/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.freelanceit.entitybeans.user;

import com.bionic.freelanceit.entities.Task;
import com.bionic.freelanceit.entities.User;
import com.bionic.freelanceit.entities.UserTask;
import com.bionic.freelanceit.entitybeans.AbstractFacade;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author qz
 */
@WebService
@Stateless
public class UserFacade extends AbstractFacade<User> implements UserFacadeLocal {

    @PersistenceContext(unitName = "FreelanceItPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    @Override
    public User findByLogin(String login) {
        User user = null;
        TypedQuery<User> query = em.createNamedQuery("User.findByLogin", User.class);
        query.setParameter("login", login);
        user = query.getSingleResult();
        return user;
    }

    @Override
    public List<Task> getTasksHeCreated(User user) {
        List<Task> taskList = new ArrayList<>();
        Collection<UserTask> userTaskList = user.getUserTaskCollection();
        for (UserTask ut : userTaskList) {
            if (ut.getRelationType().equals("creator")) {
                taskList.add(ut.getTask());
            }
        }
        return taskList;
    }

    @Override
    public User findByEmail(String email) {
        User user = null;
        TypedQuery<User> query = em.createNamedQuery("User.findByEmail", User.class);
        query.setParameter("email", email);
        user = query.getSingleResult();
        return user;
    }
}
