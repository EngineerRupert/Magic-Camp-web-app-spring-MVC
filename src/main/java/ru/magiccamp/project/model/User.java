package ru.magiccamp.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;

@Component
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true, nullable = false, length = 120)
    private String login;

    @Column(nullable = false, length = 150)
    @JsonIgnore
    private String password;

    @Column(length = 100)
    private String dataOfBirth;

    @Column(length = 150)
    private String zodiacSign;

    @Column(length = 150)
    private String yearOfBirthSign;

    @Column
    @ColumnDefault("false")
    private boolean isAdmin;

    public User() {

    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDataOfBirth() {
        return dataOfBirth;
    }

    public void setDataOfBirth(String dataOfBirth) {
        this.dataOfBirth = dataOfBirth;
    }

    public String getZodiacSign() {
        return zodiacSign;
    }

    public void setZodiacSign(String zodiacSign) {
        this.zodiacSign = zodiacSign;
    }

    public String getYearOfBirthSign() {
        return yearOfBirthSign;
    }

    public void setYearOfBirthSign(String yearOfBirthSign) {
        this.yearOfBirthSign = yearOfBirthSign;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && login.equals(user.login) && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
