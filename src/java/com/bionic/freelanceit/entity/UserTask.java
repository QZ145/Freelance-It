/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.freelanceit.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author qz
 */
@Entity
@Table(name = "User_Task")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserTask.findAll", query = "SELECT u FROM UserTask u"),
    @NamedQuery(name = "UserTask.findByIdUser", query = "SELECT u FROM UserTask u WHERE u.userTaskPK.idUser = :idUser"),
    @NamedQuery(name = "UserTask.findByIdTask", query = "SELECT u FROM UserTask u WHERE u.userTaskPK.idTask = :idTask"),
    @NamedQuery(name = "UserTask.findByRelationType", query = "SELECT u FROM UserTask u WHERE u.relationType = :relationType")})
public class UserTask implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserTaskPK userTaskPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "relationType")
    private String relationType;
    @JoinColumn(name = "idUser", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;
    @JoinColumn(name = "idTask", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Task task;

    public UserTask() {
    }

    public UserTask(UserTaskPK userTaskPK) {
        this.userTaskPK = userTaskPK;
    }

    public UserTask(UserTaskPK userTaskPK, String relationType) {
        this.userTaskPK = userTaskPK;
        this.relationType = relationType;
    }

    public UserTask(long idUser, long idTask) {
        this.userTaskPK = new UserTaskPK(idUser, idTask);
    }

    public UserTaskPK getUserTaskPK() {
        return userTaskPK;
    }

    public void setUserTaskPK(UserTaskPK userTaskPK) {
        this.userTaskPK = userTaskPK;
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userTaskPK != null ? userTaskPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserTask)) {
            return false;
        }
        UserTask other = (UserTask) object;
        if ((this.userTaskPK == null && other.userTaskPK != null) || (this.userTaskPK != null && !this.userTaskPK.equals(other.userTaskPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bionic.freelanceit.entity.UserTask[ userTaskPK=" + userTaskPK + " ]";
    }
    
}
