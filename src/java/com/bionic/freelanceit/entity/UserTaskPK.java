/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.freelanceit.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author qz
 */
@Embeddable
public class UserTaskPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idUser")
    private long idUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idTask")
    private long idTask;

    public UserTaskPK() {
    }

    public UserTaskPK(long idUser, long idTask) {
        this.idUser = idUser;
        this.idTask = idTask;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public long getIdTask() {
        return idTask;
    }

    public void setIdTask(long idTask) {
        this.idTask = idTask;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUser;
        hash += (int) idTask;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserTaskPK)) {
            return false;
        }
        UserTaskPK other = (UserTaskPK) object;
        if (this.idUser != other.idUser) {
            return false;
        }
        if (this.idTask != other.idTask) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bionic.freelanceit.entity.UserTaskPK[ idUser=" + idUser + ", idTask=" + idTask + " ]";
    }
    
}
