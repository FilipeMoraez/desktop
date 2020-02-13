package br.com.desktop.login.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity(name="Authority")
@Table(name="AUTHORITY")
public class Authority extends Logged{

    @Id
    @Column(name="ID_AUTHORITY")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="NAME", nullable = false)
    private String name;


    @ApiModelProperty(hidden = true)
    @OneToMany(
            mappedBy = "authority",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<User> users = new ArrayList<>();

    @ApiModelProperty(hidden = true)
    @ManyToMany
    @JoinTable(
            name = "AUTHORITY_ROLE",
            joinColumns = @JoinColumn(name = "AUTHORITY_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    @JsonManagedReference
    private List<Role> roles = new ArrayList<>();



    // Getters and Setters...


    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
