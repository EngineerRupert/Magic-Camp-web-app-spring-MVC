package ru.magiccamp.project.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import ru.magiccamp.project.model.Horoscope;

import java.io.IOException;

@Component
public class HoroscopeParser {

    // Jsoup парсер гороскопа, который динамически может показывать гороскоп на сегодня, на завтра, на неделю
    // Jsoup horoscope parser that can dynamically show the horoscope for today, for tomorrow, for a week

    public Horoscope getHoroscope(String zodiacSign, String timeRange) throws IOException {
        zodiacSign = translateOnEng(zodiacSign);
        Document document = Jsoup.connect(
                "https://horo.mail.ru/prediction/" + zodiacSign + "/" + timeRange + "/"
        ).get();
        Elements bodyOfHoroscope = document.getElementsByAttributeValue("class", "article__text");
        return new Horoscope(bodyOfHoroscope.get(0).child(0).text());
    }

    private String translateOnEng(String element) {
        if (element.equals("Овен")) {
           element = "aries";
        }
        if (element.equals("Телец")) {
            element = "taurus";
        }
        if (element.equals("Близнецы")) {
            element = "gemini";
        }
        if (element.equals("Рак")) {
            element = "cancer";
        }
        if (element.equals("Лев")) {
            element = "leo";
        }
        if (element.equals("Дева")) {
            element = "virgo";
        }
        if (element.equals("Весы")) {
            element = "libra";
        }
        if (element.equals("Скорпион")) {
            element = "scorpio";
        }
        if (element.equals("Стрелец")) {
            element = "sagittarius";
        }
        if (element.equals("Козерог")) {
            element = "capricorn";
        }
        if (element.equals("Водолей")) {
            element = "aquarius";
        }
        if (element.equals("Рыбы")) {
            element = "pisces";
        }
        return element;
    }

}
