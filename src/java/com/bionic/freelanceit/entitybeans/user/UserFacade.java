/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.freelanceit.entitybeans.user;

import com.bionic.freelanceit.entity.Task;
import com.bionic.freelanceit.entity.User;
import com.bionic.freelanceit.entity.UserTask;
import com.bionic.freelanceit.entitybeans.AbstractFacade;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author qz
 */
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
        TypedQuery<User> query = em.createNamedQuery("User.findByLogin", User.class);
        query.setParameter("login", login);
        User user = null;
        user = query.getSingleResult();
        return user;
    }
    
    @Override
    public List<Task> findHisTasks(User user) {
        List<Task> taskList = new ArrayList();
        List userTaskList = (List) user.getUserTaskCollection();
        Iterator it = userTaskList.iterator();
        while (it.hasNext()) {
            UserTask userTask = (UserTask) it.next();
            if (userTask.getRelationType().equals("creator")) {
                taskList.add(userTask.getTask());
            }
        }
        return taskList;
    }
}
