package ru.magiccamp.project.service;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class NameDecryption {

    // сервис который расшифровывает / рассчитывает имя в число
    // a service that decrypts / calculates the name into a number

    public int nameDecryption(String name) {
        var mapOfCharsAndDigits = mapOfChars();
        var sum = List.of(name.trim().toLowerCase().split(""))
                .stream()
                .map(mapOfCharsAndDigits::get)
                .mapToInt(e -> e)
                .sum();
        return List.of(String.valueOf(sum).trim().split(""))
                .stream()
                .mapToInt(Integer::parseInt)
                .sum();
    }

    private Map<String, Integer> mapOfChars() {
        Map<String, Integer> mapResultNumbers = new HashMap<>();
        mapResultNumbers.putAll(Map.of(
                "а", 1,"б",2,"в",3,
                "г", 4,"д",5,"е",6,
                "ё", 7,"ж", 8,"з",9,
                "и", 1
        ));
        mapResultNumbers.putAll(Map.of(
                "й", 2,"к",3,"л",4,
                "м", 5,"н",6,"о",7,
                "п", 8,"р",9,"с",1,
                "т", 2
        ));
        mapResultNumbers.putAll(Map.of(
                "у", 3,"ф",4,"х",5,
                "ц", 6,"ч",7,"ш",8,
                "щ", 9,"ъ",1,"ы",2,
                "ь", 3
        ));
        mapResultNumbers.putAll(Map.of(
                "э", 4,"ю", 5,"я", 6
        ));
        return mapResultNumbers;
    }

}
