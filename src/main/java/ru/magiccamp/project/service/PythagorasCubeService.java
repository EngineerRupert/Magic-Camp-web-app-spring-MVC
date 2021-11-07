package ru.magiccamp.project.service;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PythagorasCubeService {

    public String yourNumberСalculation(String dataOfBirth) {
        String dataOfBirthFormated = dataOfBirth.replace(".","").trim();
        int sumOfDaysAndMonths = 0;
        int sumOfYearBirth = 0;

        for (int i = 0; i <= 3; i++) {
            sumOfDaysAndMonths += Integer.parseInt(Character.toString(dataOfBirthFormated.charAt(i)));
        }
        for (int i = 4; i <= 7; i++) {
            sumOfYearBirth += Integer.parseInt(Character.toString(dataOfBirthFormated.charAt(i)));
        }

        String firstWorkNumber = String.valueOf(sumOfDaysAndMonths + sumOfYearBirth);
        int secondWorkNumber =
                Integer.parseInt(Character.toString(firstWorkNumber.charAt(0))) +
                        Integer.parseInt(Character.toString(firstWorkNumber.charAt(1)));
        String thirdWorkNumber = String.valueOf(
                Integer.parseInt(firstWorkNumber) - 2 * Integer.parseInt(Character.toString(dataOfBirthFormated.charAt(0)))
        );
        int fourthWorkNumber = Integer.parseInt(Character.toString(thirdWorkNumber.charAt(0))) +
                Integer.parseInt(Character.toString(thirdWorkNumber.charAt(1)));

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dataOfBirthFormated.replace("0", ""));
        stringBuilder.append(firstWorkNumber);
        stringBuilder.append(secondWorkNumber);
        stringBuilder.append(thirdWorkNumber);
        stringBuilder.append(fourthWorkNumber);

        return String.valueOf(stringBuilder);
    }

    public Map<Integer, String> numberOfDigitsСalculation(String personNumber) {
        String[] arrayOfNumbers = String.valueOf(personNumber).trim().split("");
        Map<Integer, String> hashMapOfNumbers = new HashMap<>();

        for (int i = 1; i < 10; i++) {
            StringBuilder stringBuilderForloop = new StringBuilder();
            for (int j = 0; j < arrayOfNumbers.length; j++) {
                if(arrayOfNumbers[j].equals(String.valueOf(i))) {
                    stringBuilderForloop.append(i);
                }
                if(!stringBuilderForloop.toString().trim().isEmpty()) {
                    hashMapOfNumbers.put(i, String.valueOf(stringBuilderForloop));
                }
            }
        }
        return hashMapOfNumbers;
    }

}
