package ru.magiccamp.project.service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegistrationForm {

    @NotBlank
    @Size(min = 4, max = 10)
    @Pattern(regexp = "[a-zA-Z0-9]*",
            message = "Логин должен состоять из букв, цифр.")
    private String login;

    @NotBlank
    @Size(min = 4, max = 10)
    @Pattern(regexp = "[a-zA-Z0-9]*",
            message = "Пароль должен состоять из букв, цифр.")
    private String password;

    @NotBlank
    @Size(min = 4, max = 10)
    @Pattern(regexp = "[a-zA-Z0-9]*",
            message = "Пароль должен состоять из букв, цифр.")
    private String passwordConfirmation;

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

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }
}
