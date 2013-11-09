/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.freelanceit.entitybeans.usertype;

import com.bionic.freelanceit.entity.Usertype;
import com.bionic.freelanceit.entitybeans.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author qz
 */
@Stateless
public class UsertypeFacade extends AbstractFacade<Usertype> implements UsertypeFacadeLocal {
    @PersistenceContext(unitName = "FreelanceItPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsertypeFacade() {
        super(Usertype.class);
    }
    
}
