/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.freelanceit.entitybeans.usertype;

import com.bionic.freelanceit.entity.Usertype;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author qz
 */
@Local
public interface UsertypeFacadeLocal {

    void create(Usertype usertype);

    void edit(Usertype usertype);

    void remove(Usertype usertype);

    Usertype find(Object id);

    List<Usertype> findAll();

    List<Usertype> findRange(int[] range);

    int count();
    
}
