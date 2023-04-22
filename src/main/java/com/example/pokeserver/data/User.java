package com.example.pokeserver.data;

import jakarta.persistence.*;

@Entity
@Table(name="USERS")
public class User {
    @Id
    @Column(name="USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="LOGIN")
    private String login;
    @Column(name="PASSWORD")
    private String password;

    @Column(name="AUTHORITY")
    private String authority;
    public User() {
    }

    public User(Long id, String login, String email, String password, String authority) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
