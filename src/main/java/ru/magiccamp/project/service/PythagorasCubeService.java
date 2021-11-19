package ru.magiccamp.project.service;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

@Component
public class PythagorasCubeService {

    // сервис квадрата Пифагора, который расчитывает число из даты рождения,
    // а так же другим методом расчитывается число вхождения цифр в этом числе.
    // the service of the Pythagorean square, which calculates the number from the date of birth,
    // and also calculates the number of occurrences of digits in this number by another method.

    public String yourNumberСalculation(String dataOfBirth) {
        String dataOfBirthFormated = dataOfBirth.replace(".","").trim();
        int sumOfDaysAndMonths = 0;
        int sumOfYearBirth = 0;

        for (int i = 0; i <= 3; i++) {
            sumOfDaysAndMonths += parseInt(Character.toString(dataOfBirthFormated.charAt(i)));
        }
        for (int i = 4; i <= 7; i++) {
            sumOfYearBirth += parseInt(Character.toString(dataOfBirthFormated.charAt(i)));
        }

        String firstWorkNumber = valueOf(sumOfDaysAndMonths + sumOfYearBirth);
        int secondWorkNumber =
                parseInt(Character.toString(firstWorkNumber.charAt(0))) +
                        parseInt(Character.toString(firstWorkNumber.charAt(1)));
        String thirdWorkNumber = valueOf(
                parseInt(firstWorkNumber) - 2 * parseInt(Character.toString(dataOfBirthFormated.charAt(0)))
        );
        int fourthWorkNumber = parseInt(Character.toString(thirdWorkNumber.charAt(0))) +
                parseInt(Character.toString(thirdWorkNumber.charAt(1)));

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dataOfBirthFormated.replace("0", ""));
        stringBuilder.append(firstWorkNumber);
        stringBuilder.append(secondWorkNumber);
        stringBuilder.append(thirdWorkNumber);
        stringBuilder.append(fourthWorkNumber);

        return valueOf(stringBuilder);
    }

    public Map<Integer, String> numberOfDigitsСalculation(String personNumber) {
        String[] arrayOfNumbers = valueOf(personNumber).trim().split("");
        Map<Integer, String> hashMapOfNumbers = new HashMap<>();

        for (int i = 1; i < 10; i++) {
            StringBuilder stringBuilderForloop = new StringBuilder();
            for (int j = 0; j < arrayOfNumbers.length; j++) {
                if(arrayOfNumbers[j].equals(valueOf(i))) {
                    stringBuilderForloop.append(i);
                }
                if(!stringBuilderForloop.toString().trim().isEmpty()) {
                    hashMapOfNumbers.put(i, valueOf(stringBuilderForloop));
                }
            }
        }

        return hashMapOfNumbers;
    }

}
