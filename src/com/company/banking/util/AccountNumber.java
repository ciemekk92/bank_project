package com.company.banking.util;

import java.time.Instant;
import java.util.Random;

public class AccountNumber {
    public static final String countryCode = "PL";

    public static String generateAccountNumber() {
        Random value = new Random();
        value.setSeed(Instant.now().getEpochSecond());

        String retVal = countryCode;

        for (int i = 0; i < 22; i++) {
            if(i == 2 || (i + 2) % 4 == 0) retVal += " ";
            retVal += Integer.toString(value.nextInt(10));
        }

        return retVal;
    }
}
