package com.orangehrm.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomGeneration {
	
	
	private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*()-_=+[]{}<>?";

    private static final String ALL = UPPER + LOWER + DIGITS ;//+ SPECIAL;

    public static String generatePassword(int length) {

        if (length < 8) {
            throw new IllegalArgumentException(
                "Password length must be at least 8");
        }

        Random random = new Random();

        List<Character> password = new ArrayList<>();

        // Ensure at least one from each category
        password.add(UPPER.charAt(random.nextInt(UPPER.length())));
        password.add(LOWER.charAt(random.nextInt(LOWER.length())));
        password.add(DIGITS.charAt(random.nextInt(DIGITS.length())));
        password.add(SPECIAL.charAt(random.nextInt(SPECIAL.length())));

        // Fill remaining characters
        for (int i = password.size(); i < length; i++) {
            password.add(ALL.charAt(random.nextInt(ALL.length())));
        }

        // Shuffle so mandatory characters aren't always at the start
        Collections.shuffle(password);

        StringBuilder sb = new StringBuilder();
        for (char c : password) {
            sb.append(c);
        }

        return sb.toString();
    }
	
    public static String generateAlphaNumeric(int length) {
        String chars = LOWER + DIGITS;
        Random random = new Random();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }

        return sb.toString();
    }
    
    public static String generateString(int length) {
        String chars = LOWER;
        Random random = new Random();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }

        return sb.toString();
    }
    
    public static long generateNumeric(int length) {
        String chars = DIGITS;
        Random random = new Random();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }

        return Long.parseLong(sb.toString());
    }

}
