package br.com.desktop.login.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="Role")
@Table(name="ROLE")
@Getter
@Setter
public class Role extends Logged {
    @Id
    @Column(name = "ID_ROLE")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ACTIVE")
    private boolean active;

    @ApiModelProperty(hidden = true)
    @ManyToMany(mappedBy = "roles")
    private List<Authority> authorities = new ArrayList<>();
}