package ru.magiccamp.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.magiccamp.project.dao.UserDao;
import ru.magiccamp.project.model.User;
import ru.magiccamp.project.service.NameDecryption;

@Controller
public class NameDecryptionController {

    @Autowired
    private NameDecryption nameDecryption;

    @Autowired
    private UserDao userDao;

    @GetMapping("/name-decryption")
    public String nameDecryption(
            Authentication authentication,
            Model model
    ) {
        User user = userDao.findByLoginOrId(authentication.getName());
        System.out.println(user.getName());
        var numberOfUserFromName = nameDecryption.nameDecryption(user.getName());

        model.addAttribute("numberOfUser", numberOfUserFromName);

        return "/namedecryption/name-decryption";
    }

}
