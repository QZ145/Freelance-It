/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.freelanceit.entitybeans.group;

import com.bionic.freelanceit.entities.GroupEntity;
import com.bionic.freelanceit.entitybeans.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author qz
 */
@Stateless
public class GroupEntityFacade extends AbstractFacade<GroupEntity> implements GroupEntityFacadeLocal {

    @PersistenceContext(unitName = "FreelanceItPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GroupEntityFacade() {
        super(GroupEntity.class);
    }
}
