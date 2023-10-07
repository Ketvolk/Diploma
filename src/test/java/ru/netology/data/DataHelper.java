package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;


public class DataHelper {

    public static String getValidActiveCard() {
        return ("4444 4444 4444 4441");
    }

    public static String getValidInactiveCard() {
        return ("4444 4444 4444 4443");
    }


    public static String getInvalidCardNumber() {
        return ("4444 4444 4444 4440");
    }

    public static String getLongCardNumber() {
        return ("4444 4444 4444 44410");
    }

    public static String getShortCardNumber() {
        return ("4444 4444 4444 444");
    }

    public static long generateValidAddDays() {
        Faker faker = new Faker();
        return faker.number().numberBetween(0, 1_827);
    }


    public static String generateValidMonth() {
        return LocalDate.now().plusDays(generateValidAddDays()).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String generateInvalidMonth() {
        int max = 99;
//        Double month = (Math.random() * ++max) + 13;
        return String.valueOf(Math.random() * ++max) + 0;
    }

    public static String generateValidYear() {
        return LocalDate.now().plusDays(generateValidAddDays()).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String generateExpiredYear() {
        Faker faker = new Faker();
        long subDays = faker.number().numberBetween(0, 1_000);
        return LocalDate.now().minusDays(subDays).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String generateInvalidYear() {
        Faker faker = new Faker();
        long addDays = faker.number().numberBetween(1_827, 10_000);
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String generateValidHolderInEn() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String generateValidHolderInRu() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String generateStringInEn(int quaint) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = quaint;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static String generateStringInRu(int quaint) {
        int leftLimit = 128; // letter 'А'
        int rightLimit = 159; // letter 'Я'
        int targetStringLength = quaint;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }


    public static String generateValidCVCCode() {
        return String.valueOf(Math.random() * (899) + 100);
    }

    public static String generateSymbol() {
        Random r = new Random();
        char symbol = 0;
        String alphabet = "!@#$%^&*()/}{:?><";
        for (int i = 0; i < 50; i++) {
            symbol = alphabet.charAt(r.nextInt(alphabet.length()));
        }
        return String.valueOf(symbol);
    }

    public static String generateNumbers(int count) {
        Faker faker = new Faker();
        return faker.number().digits(count);
    }



}
