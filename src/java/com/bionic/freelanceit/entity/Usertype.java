/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.freelanceit.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author qz
 */
@Entity
@Table(name = "User_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usertype.findAll", query = "SELECT u FROM Usertype u"),
    @NamedQuery(name = "Usertype.findByIdUser", query = "SELECT u FROM Usertype u WHERE u.idUser = :idUser"),
    @NamedQuery(name = "Usertype.findByType", query = "SELECT u FROM Usertype u WHERE u.type = :type")})
public class Usertype implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idUser")
    private Long idUser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "type")
    private String type;
    @JoinColumn(name = "idUser", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private User user;

    public Usertype() {
    }

    public Usertype(Long idUser) {
        this.idUser = idUser;
    }

    public Usertype(Long idUser, String type) {
        this.idUser = idUser;
        this.type = type;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usertype)) {
            return false;
        }
        Usertype other = (Usertype) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bionic.freelanceit.entity.Usertype[ idUser=" + idUser + " ]";
    }
    
}
