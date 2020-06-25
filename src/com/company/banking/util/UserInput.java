package com.company.banking.util;

import java.io.Serializable;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Pattern;

public class UserInput implements Serializable {
    public static int getInt(Scanner stdin) {
        while(!stdin.hasNextInt()) {
            System.out.println("Integer required.");
            stdin.next();
        }
        int input = stdin.nextInt();
        stdin.nextLine();
        return input;
    }

    public static int getInt(Scanner stdin, int minValue, int maxValue) {
        int input = 0;
        do {

            while(!stdin.hasNextInt()) {
                System.out.println("Integer required.");
                stdin.next();
            }
            input = stdin.nextInt();
            stdin.nextLine();

            if(input < minValue || input > maxValue) {
                System.out.println("Number out of range.");
            }

        } while(input < minValue || input > maxValue);

        return input;
    }

    public static double getDouble(Scanner stdin) {
        while(!stdin.hasNextDouble()) {
            System.out.println("Numerical input required.");
            stdin.next();
        }
        double input = stdin.nextDouble();
        stdin.nextLine();
        return input;
    }

    public static String getString(Scanner stdin) {
        while(!stdin.hasNextLine()) {
            System.out.println("Alphanumerical input required.");
            stdin.nextLine();
        }
        return stdin.nextLine();
    }

    public static UUID getUUID(Scanner stdin) {
        return UUID.fromString(getString(stdin));
    }
}
