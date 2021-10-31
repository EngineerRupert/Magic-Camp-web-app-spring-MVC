package ru.magiccamp.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.magiccamp.project.dao.UserDao;
import ru.magiccamp.project.model.User;
import ru.magiccamp.project.service.RegistrationForm;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    // rus: контроллер отвечающий за общие действия пользователя
    // eng: controller responsible for general user actions

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String userRegister(
            @ModelAttribute("registrationForm") RegistrationForm registrationForm) {
        return "/user/user_register";
    }

    @PostMapping("/register")
    public String handleRegister(
            @ModelAttribute("registrationForm")
            @Valid RegistrationForm registrationForm,
            BindingResult bindingResult
            ) {
        if (!registrationForm.getPassword().equals(registrationForm.getPasswordConfirmation())) {
            bindingResult.addError(new FieldError(
                    "registrationForm",
                    "passwordConfirmation",
                    "Пароль и пароль подтверждения должны совпадать."));
        }
        if (bindingResult.hasErrors()) {
            return "/user/user_register";
        }
        User userForCheck = userDao.findByLoginOrId(registrationForm.getLogin());
        String encryptedPassword = passwordEncoder.encode(registrationForm.getPassword());

        if (userForCheck == null) {
            User user = userDao.createUser(registrationForm.getLogin(), encryptedPassword);
            return "redirect:/";
        } else {
            bindingResult.addError(new FieldError(
                    "registrationForm",
                    "login",
                    "Пользователь с таким логином уже существует."));
            return "/user/user_register";
        }
        // внизу конструктор
    }

    @GetMapping("/log-in")
    public String logIn() {
        return "/user/login";
    }

    @ModelAttribute("registrationForm")
    public RegistrationForm createDefault() {
        return new RegistrationForm();
    }

}
