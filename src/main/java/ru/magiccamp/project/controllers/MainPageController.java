package ru.magiccamp.project.controllers;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.magiccamp.project.dao.UserDao;
import ru.magiccamp.project.model.User;

@Controller
@RequestMapping(path = "/")
public class MainPageController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/")
    public String index(
            Model model,
            Authentication authentication
    ) {
        boolean loggedIn = authentication != null && authentication.isAuthenticated();
        String userName = "";
        if (loggedIn) {
            userName = authentication.getName();
        }
        model.addAttribute("loggedIn", loggedIn);
        model.addAttribute("getLogin", userName);
        return "index";
    }

    @GetMapping("/edit-profile")
    public String editProfile(Model model) {
        model.addAttribute("userForm", new User());
        return "/user/user_card";
    }

    @PostMapping("/edit-profile")
    public String handleEditProfile(
            @ModelAttribute("userForm") User user,
            Authentication authentication
    ) {
        String userName = authentication.getName();
        User newUser = userDao.findByLoginOrId(userName);
        if(user.getName() != null) {
            newUser.setName(user.getName());
        }
        if(user.getDataOfBirth() != null) {
            newUser.setDataOfBirth(user.getDataOfBirth());
        }
        if(user.getZodiacSign() != null) {
            newUser.setZodiacSign(user.getZodiacSign());
        }
        if(user.getYearOfBirthSign() != null) {
            newUser.setYearOfBirthSign(user.getYearOfBirthSign());
        }
        userDao.updateUserInfo(newUser);
        return "redirect:/";
    }

    @ModelAttribute("userForm")
    public User createDefault() {
        return new User();
    }

    @GetMapping("/profile")
    public String lookProfile(
            Model model,
            Authentication authentication) {
        User user = userDao.findByLoginOrId(authentication.getName());
        model.addAttribute("userForForm", user);
        model.addAttribute("getLogin", authentication.getName());
        return "/user/my-profile";
    }

}
