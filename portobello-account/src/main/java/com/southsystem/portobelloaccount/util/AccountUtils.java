package com.southsystem.portobelloaccount.util;

import java.util.Random;

public class AccountUtils {

    private AccountUtils() {
        throw new IllegalStateException("Utility class");
    }


    public static String generateAccountId() {
        int number = new Random().nextInt(999999);
        return String.format("%06d", number);
    }
}
