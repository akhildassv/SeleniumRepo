package org.obs.seleniumbasics;

import java.util.Random;

public class Utility {
    public String getUtility() {
        String chars = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder s1 = new StringBuilder();
        Random rnd = new Random();
        while (s1.length() < 8) { // length of the random string.
            int index = (int) (rnd.nextFloat() * chars.length());
            s1.append(chars.charAt(index));
        }
        String s1Str = s1.toString();
        String email = s1Str + "@gmail.com";
        return email;
    }

    public String getUtility1() {
        // create a string of uppercase and lowercase characters and numbers
        //String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String alphaNumeric = lowerAlphabet + numbers;
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        // specify length of random string
        int length = 10;
        for (int i = 0; i < length; i++) {
            // generate random index number
            int index = random.nextInt(alphaNumeric.length());
            // get character specified by index
            // from the string
            char randomChar = alphaNumeric.charAt(index);
            sb.append(randomChar);
        }
        String randomString = sb.toString();
        String email1 = randomString + "@gmail.com";
        return email1;
    }

    public String getUtility2() {
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(1000);
        String email2 = "username" + randomInt + "@gmail.com";
        return email2;
    }
}