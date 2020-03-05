package br.com.desktop.login.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name="User")
@Table(name="USER")
@Getter
@Setter
public class User extends Logged{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_USER")
    private Long id;

    @Column(name="USERNAME", nullable = false, unique = true)
    private String username;

    @Column(name="PASSWORD", nullable = false)
    private String password;

    @Column(name="BIRTHDAY", nullable = false)
    private Date birthDate;

    @Column(name="EMAIL", nullable = false)
    private String email;

    @Transient
    private String authorityName;

    @ApiModelProperty(hidden = true)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="ID_AUTHORITY", nullable = false)
    @JsonIgnore
    private Authority authority;
}
