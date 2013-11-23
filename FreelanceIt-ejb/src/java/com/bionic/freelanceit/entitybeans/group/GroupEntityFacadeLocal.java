/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.freelanceit.entitybeans.group;

import com.bionic.freelanceit.entities.GroupEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author qz
 */
@Local
public interface GroupEntityFacadeLocal {

    void create(GroupEntity groupEntity);

    void edit(GroupEntity groupEntity);

    void remove(GroupEntity groupEntity);

    GroupEntity find(Object id);

    List<GroupEntity> findAll();

    List<GroupEntity> findRange(int[] range);

    int count();
}
