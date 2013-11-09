/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.freelanceit.entitybeans.usertask;

import com.bionic.freelanceit.entity.UserTask;
import com.bionic.freelanceit.entitybeans.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author qz
 */
@Stateless
public class UserTaskFacade extends AbstractFacade<UserTask> implements UserTaskFacadeLocal {
    @PersistenceContext(unitName = "FreelanceItPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserTaskFacade() {
        super(UserTask.class);
    }
    
}
