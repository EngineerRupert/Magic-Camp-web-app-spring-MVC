package ru.magiccamp.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.magiccamp.project.dao.UserDao;
import ru.magiccamp.project.model.User;
import ru.magiccamp.project.service.PythagorasCubeService;

import java.util.Map;

@Controller
public class PythagorasCubeController {

    @Autowired
    private PythagorasCubeService pythagorasCubeService;

    @Autowired
    private UserDao userDao;

    @GetMapping("/pythagoras-cube")
    public String pythagorasCube(
            Model model,
            Authentication authentication
    ) {
        User user = userDao.findByLoginOrId(authentication.getName());
        String personalNumber = pythagorasCubeService.yourNumberСalculation(user.getDataOfBirth());
        var numbersOfFate = pythagorasCubeService.numberOfDigitsСalculation(personalNumber);

        model.addAttribute("yourNumber", personalNumber);
        model.addAttribute("decodingNumbers", numbersOfFate);

        return "/pythagorascube/pythagoras_cube";
    }

}
