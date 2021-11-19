package ru.magiccamp.project.controllers;

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

    // контроллер отвечающий за главную страницу, а также за профиль пользователя
    // controller responsible for the main page, as well as for the user profile

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
        if(!user.getName().isEmpty()) {
            newUser.setName(user.getName());
        }
        if(!user.getDataOfBirth().isEmpty()) {
            newUser.setDataOfBirth(user.getDataOfBirth());
        }
        if(!user.getZodiacSign().isEmpty()) {
            newUser.setZodiacSign(user.getZodiacSign());
        }
        if(!user.getYearOfBirthSign().isEmpty()) {
            newUser.setYearOfBirthSign(user.getYearOfBirthSign());
        }
        userDao.updateUserInfo(newUser);
        return "redirect:/";
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
