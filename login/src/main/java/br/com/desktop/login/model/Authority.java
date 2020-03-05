package br.com.desktop.login.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity(name="Authority")
@Table(name="AUTHORITY")
@Getter
@Setter
public class Authority extends Logged{

    @Id
    @Column(name="ID_AUTHORITY")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="NAME", nullable = false)
    private String name;

    @JsonIgnore
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

    private List<Role> roles = new ArrayList<>();

    public void addRole(Role r){
        this.roles.add(r);
    }

    public void removeRole(Role r){
        this.roles.remove(r);
    }

}
