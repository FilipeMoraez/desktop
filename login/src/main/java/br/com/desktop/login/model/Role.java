package br.com.desktop.login.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="Role")
@Table(name="ROLE")
public class Role extends Logged{
    @Id
    @Column(name="ID_ROLE")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="NAME")
    private String name;

    @Column(name="ACTIVE")
    private boolean active;

    @ApiModelProperty(hidden = true)
    @ManyToMany(mappedBy = "roles")
    private List<Authority> authorities = new ArrayList<>();


    // Getters and Setters...

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
