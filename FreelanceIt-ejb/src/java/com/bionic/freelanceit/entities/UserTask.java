/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.freelanceit.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @NamedQuery(name = "UserTask.findByRelationType", query = "SELECT u FROM UserTask u WHERE u.relationType = :relationType"),
    @NamedQuery(name = "UserTask.findByIdUserTask", query = "SELECT u FROM UserTask u WHERE u.idUserTask = :idUserTask")})
public class UserTask implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "relationType")
    private String relationType;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUser_Task")
    private Long idUserTask;
    @JoinColumn(name = "idUser", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User user;
    @JoinColumn(name = "idTask", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Task task;

    public UserTask() {
    }

    public UserTask(Long idUserTask) {
        this.idUserTask = idUserTask;
    }

    public UserTask(Long idUserTask, String relationType) {
        this.idUserTask = idUserTask;
        this.relationType = relationType;
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    public Long getIdUserTask() {
        return idUserTask;
    }

    public void setIdUserTask(Long idUserTask) {
        this.idUserTask = idUserTask;
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
        hash += (idUserTask != null ? idUserTask.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserTask)) {
            return false;
        }
        UserTask other = (UserTask) object;
        if ((this.idUserTask == null && other.idUserTask != null) || (this.idUserTask != null && !this.idUserTask.equals(other.idUserTask))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bionic.freelanceit.entities.UserTask[ idUserTask=" + idUserTask + " ]";
    }
    
}
