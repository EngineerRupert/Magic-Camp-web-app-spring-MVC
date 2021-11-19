package ru.magiccamp.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.magiccamp.project.dao.UserDao;
import ru.magiccamp.project.model.Horoscope;
import ru.magiccamp.project.model.User;
import ru.magiccamp.project.service.HoroscopeParser;

import java.io.IOException;

@Controller
public class ChooseHoroscopeController {

    // контроллер отвечающий за действие связанные с гороскопом
    // the controller responsible for the action associated with the horoscope

    @Autowired
    UserDao userDao;

    @Autowired
    HoroscopeParser horoscopeParser;

    @GetMapping("/choose-horoscope")
    public String chooseHoroscope() {
        return "/horoscope/choose-horoscope";
    }

    @GetMapping("/horoscope/today")
    public String onTodayHoroscope(
            Authentication authentication,
            Model model
            ) throws IOException {
        User user = userDao.findByLoginOrId(authentication.getName());
        Horoscope horoscope = horoscopeParser.getHoroscope(user.getZodiacSign(),"today");

        model.addAttribute("bodyOfHoroscope", horoscope.getBody());
        model.addAttribute("signZodiac", user.getZodiacSign());

        return "/horoscope/today";
    }

    @GetMapping("/horoscope/tomorrow")
    public String onTomorrowHoroscope(
            Authentication authentication,
            Model model
    ) throws IOException {
        User user = userDao.findByLoginOrId(authentication.getName());
        Horoscope horoscope = horoscopeParser.getHoroscope(user.getZodiacSign(),"tomorrow");

        model.addAttribute("bodyOfHoroscope", horoscope.getBody());
        model.addAttribute("signZodiac", user.getZodiacSign());

        return "/horoscope/tomorrow";
    }

    @GetMapping("/horoscope/week")
    public String onWeekHoroscope(
            Authentication authentication,
            Model model
    ) throws IOException {
        User user = userDao.findByLoginOrId(authentication.getName());
        Horoscope horoscope = horoscopeParser.getHoroscope(user.getZodiacSign(),"week");

        model.addAttribute("bodyOfHoroscope", horoscope.getBody());
        model.addAttribute("signZodiac", user.getZodiacSign());

        return "/horoscope/week";
    }

}
