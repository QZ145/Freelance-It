/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.freelanceit.dao;

import com.bionic.freelanceit.entity.UserTask;
import com.bionic.freelanceit.idao.IUserTaskDAO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author qz
 */
public class UserTaskDAO implements IUserTaskDAO {
    
    @Override
    public void add(UserTask userTask) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("FreelanceItPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.flush();
        em.persist(userTask);
        tx.commit();
        em.close();
        emf.close();
   }
    
}
