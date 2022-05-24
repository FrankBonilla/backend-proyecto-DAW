package com.practica.backend.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="usuarios")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idUsuario")
    private Integer idUsuario;

    @Column(name="userName",unique = true, length = 20, nullable = false)
    private String userName;

    @Column(name="password",length = 60)
    private String password;

    private Boolean enable;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="users_authorities", joinColumns = @JoinColumn(name="idUsuario"),
            inverseJoinColumns = @JoinColumn(name="idRole"),
    uniqueConstraints = {@UniqueConstraint(columnNames = {"idUsuario","idRole"})})
    private List<Role> roles;

    /**constructor por defecto **/
    public Usuario() {
    }

    /**Constructor con parametros **/
    public Usuario(String userName, String password, Boolean enable, List<Role> roles) {
        this.userName = userName;
        this.password = password;
        this.enable = enable;
        this.roles = roles;
    }

    /**Getters and Setters**/
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    /**Equals and HashCode **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(idUsuario, usuario.idUsuario) && Objects.equals(userName, usuario.userName) && Objects.equals(password, usuario.password) && Objects.equals(enable, usuario.enable) && Objects.equals(roles, usuario.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, userName, password, enable, roles);
    }
    /**toString**/
    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", enable=" + enable +
                ", roles=" + roles +
                '}';
    }
}
