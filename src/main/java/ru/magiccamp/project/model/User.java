package ru.magiccamp.project.model;

import javax.persistence.*;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true, nullable = false, length = 120)
    private String login;

    @Column(nullable = false, length = 150)
    private String password;

    public User() {

    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
