package com.southsystem.portobelloperson.util;

import java.util.Random;

public class NumberUtils {

    private NumberUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        return new Random().nextInt((max - min) + 1) + min;
    }
}
