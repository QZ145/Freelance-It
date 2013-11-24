/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.freelanceit.entitybeans.task;

import com.bionic.freelanceit.entities.Task;
import com.bionic.freelanceit.entities.User;
import com.bionic.freelanceit.entities.UserTask;
import com.bionic.freelanceit.entitybeans.AbstractFacade;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author qz
 */
@Stateless
public class TaskFacade extends AbstractFacade<Task> implements TaskFacadeLocal {

    @PersistenceContext(unitName = "FreelanceItPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TaskFacade() {
        super(Task.class);
    }

    @Override
    public User getCreator(Task task) {
        User user = null;
        Collection<UserTask> userTaskCollection = task.getUserTaskCollection();
        for (UserTask userTask : userTaskCollection) {
            if (userTask.getRelationType().equals("creator")) {
                user = userTask.getUser();
                break;
            }
        }
        return user;
    }
    
}
