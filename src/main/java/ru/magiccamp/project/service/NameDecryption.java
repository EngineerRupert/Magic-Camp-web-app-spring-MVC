package ru.magiccamp.project.service;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class NameDecryption {

    public int nameDecryption(String name) {
        var mapOfCharsAndDigits = mapOfChars();
        var sum = List.of(name.trim().toLowerCase().split(""))
                .stream()
                .map(o -> mapOfCharsAndDigits.get(o))
                .mapToInt(e -> e).sum();
        return List.of(String.valueOf(sum).trim().split(""))
                .stream()
                .mapToInt(e -> Integer.parseInt(e))
                .sum();
    }

    private Map<String, Integer> mapOfChars() {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("б", 2);
        map.put("в", 3);
        map.put("г", 4);
        map.put("д", 5);
        map.put("е", 6);
        map.put("ё", 7);
        map.put("ж", 8);
        map.put("з", 9);
        map.put("и", 1);
        map.put("й", 2);
        map.put("к", 3);
        map.put("л", 4);
        map.put("м", 5);
        map.put("н", 6);
        map.put("о", 7);
        map.put("п", 8);
        map.put("р", 9);
        map.put("с", 1);
        map.put("т", 2);
        map.put("у", 3);
        map.put("ф", 4);
        map.put("х", 5);
        map.put("ц", 6);
        map.put("ч", 7);
        map.put("ш", 8);
        map.put("щ", 9);
        map.put("ъ", 1);
        map.put("ы", 2);
        map.put("ь", 3);
        map.put("э", 4);
        map.put("ю", 5);
        map.put("я", 6);
        return map;
    }

}
