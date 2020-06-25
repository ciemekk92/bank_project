package com.company.banking.util;

import java.util.Random;

public class AccountNumber {
    public static String label = "PL";

    public static String generateAccountNumber() {
        Random value = new Random();
        int count = 0;
        int n = 0;

        for (int i =0; i < 12; i++) {
            if (count == 4) {
                label += " ";
                count = 0;
            } else {
                n = value.nextInt(10);
                label += Integer.toString(n);
                count++;
            }
        }
        return label;
    }
}
