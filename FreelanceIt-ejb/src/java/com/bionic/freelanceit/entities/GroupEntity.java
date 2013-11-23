/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.freelanceit.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author qz
 */
@Entity
@Table(name = "Group")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GroupEntity.findAll", query = "SELECT g FROM GroupEntity g"),
    @NamedQuery(name = "GroupEntity.findByIdGroup", query = "SELECT g FROM GroupEntity g WHERE g.idGroup = :idGroup"),
    @NamedQuery(name = "GroupEntity.findByGroupName", query = "SELECT g FROM GroupEntity g WHERE g.groupName = :groupName"),
    @NamedQuery(name = "GroupEntity.findByGroupDesc", query = "SELECT g FROM GroupEntity g WHERE g.groupDesc = :groupDesc")})
public class GroupEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idGroup")
    private Integer idGroup;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "groupName")
    private String groupName;
    @Size(max = 200)
    @Column(name = "groupDesc")
    private String groupDesc;
    @ManyToMany(mappedBy = "groupEntityCollection")
    private Collection<User> userCollection;

    public GroupEntity() {
    }

    public GroupEntity(Integer idGroup) {
        this.idGroup = idGroup;
    }

    public GroupEntity(Integer idGroup, String groupName) {
        this.idGroup = idGroup;
        this.groupName = groupName;
    }

    public Integer getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Integer idGroup) {
        this.idGroup = idGroup;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    @XmlTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGroup != null ? idGroup.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GroupEntity)) {
            return false;
        }
        GroupEntity other = (GroupEntity) object;
        if ((this.idGroup == null && other.idGroup != null) || (this.idGroup != null && !this.idGroup.equals(other.idGroup))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bionic.freelanceit.entities.GroupEntity[ idGroup=" + idGroup + " ]";
    }
}
