package br.com.phricardo.schedulingtechnicians.util;

import java.time.LocalDate;
import java.util.Random;

public class ServiceOrderUtil {
    private static final int NUM_DIGITS = 5;

    public static String generate() {
        LocalDate today = LocalDate.now();
        String date = today.toString().replace("-", "");

        Random rand = new Random();
        String randomNumbers = String.format("%0" + NUM_DIGITS + "d", rand.nextInt((int) Math.pow(10, NUM_DIGITS)));

        return date + randomNumbers;
    }
}
