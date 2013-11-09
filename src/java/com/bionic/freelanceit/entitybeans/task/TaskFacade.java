/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.freelanceit.entitybeans.task;

import com.bionic.freelanceit.entity.Task;
import com.bionic.freelanceit.entitybeans.AbstractFacade;
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
    
}
