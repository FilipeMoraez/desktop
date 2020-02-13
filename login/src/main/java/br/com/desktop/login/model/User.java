package br.com.desktop.login.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.Date;

@Entity(name="User")
@Table(name="USER")
public class User extends Logged{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_USER")
    private Long id;

    @Column(name="USERNAME", nullable = false)
    private String username;

    @Column(name="PASSWORD", nullable = false)
    private String password;

    @Column(name="BIRTHDAY", nullable = false)
    private Date birthDate;

    @Column(name="EMAIL", nullable = false)
    private String email;

    @ApiModelProperty(hidden = true)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="ID_AUTHORITY")
    @JsonBackReference
    private Authority authority;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }
}
