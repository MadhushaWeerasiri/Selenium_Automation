package Pages;

import java.util.Random;

// File: TestData.java
public class TestData {
    public static String name = "adam";
    public static String email = generateRandomEmail();
    public static int gender = 1;
    public static String password = "6ADm@124c7a";
    public static int day = 5;
    public static String month = "June";
    public static int year = 2001;
    public static boolean newsletter = true;
    public static boolean offers = true;
    public static String FName = "adam";
    public static String LName = "smith";
    public static String company = "11lab";
    public static String address = "delhi, india";
    public static String country = "India";
    public static String state = "Delhi";
    public static String city = "Delhi";
    public static int zipcode = 44000;
    public static int mobile = 0710000000;

    public static String generateRandomEmail() {
        String characters = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder emailPrefix = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 8; i++) {
            emailPrefix.append(characters.charAt(random.nextInt(characters.length())));
        }

        return emailPrefix.toString() + "@test.com";
    }
}
