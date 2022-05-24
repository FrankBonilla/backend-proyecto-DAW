package com.practica.backend.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="roles")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idRole")
    private Integer idRole;

    @Column(name="role", length = 20, unique = true)
    private String nombreRole;

    /**constructor por defecto **/
    public Role() {
    }
    /**constructor con parámetros **/
    public Role(String nombreRole) {
        this.nombreRole = nombreRole;
    }
    /**Getters and Setters**/
    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getNombreRole() {
        return nombreRole;
    }

    public void setNombreRole(String nombreRole) {
        this.nombreRole = nombreRole;
    }
    /**Equals and HashCode **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(idRole, role.idRole) && Objects.equals(nombreRole, role.nombreRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRole, nombreRole);
    }
    /**toString**/
    @Override
    public String toString() {
        return "Role{" +
                "idRole=" + idRole +
                ", nombreRole='" + nombreRole + '\'' +
                '}';
    }
}
