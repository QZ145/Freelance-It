/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.freelanceit.bean;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author qz
 */
@Entity
@Table(name = "Task")

public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtask")
    private Integer idtask;
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Column(name = "id_executor")
    private Integer idExecutor;
    @Basic(optional = false)
    @Column(name = "id_owner")
    private Integer idOwner;
    @Basic(optional = false)
    @Column(name = "active")
    private Boolean active;
    @Basic(optional = false)
    @Column(name = "done")
    private Boolean done;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Task() {
    }

    public Task(Integer idtask) {
        this.idtask = idtask;
    }

    public Task(Integer idtask, String title, Integer idOwner) {
        this.idtask = idtask;
        this.title = title;
        this.idOwner = idOwner;
    }

    public Integer getIdtask() {
        return idtask;
    }

    public void setIdtask(Integer idtask) {
        this.idtask = idtask;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getIdExecutor() {
        return idExecutor;
    }

    public void setIdExecutor(Integer idExecutor) {
        this.idExecutor = idExecutor;
    }

    public Integer getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Integer idOwner) {
        this.idOwner = idOwner;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtask != null ? idtask.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Task)) {
            return false;
        }
        if(object == this) {
            return false;
        }
        Task other = (Task) object;
        if ((this.idtask == null && other.idtask != null) || (this.idtask != null && !this.idtask.equals(other.idtask))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bionic.projectx.bean.Task[ idtask=" + idtask + " ]";
    }
    
}
