package com.company.banking.util;

import java.io.Serializable;
import java.util.Scanner;

public class UserInput implements Serializable {
    public static int getInt(Scanner stdin) {
        displayInputIndicator();
        while(!stdin.hasNextInt()) {
            System.out.println("Integer required.");
            stdin.next();
            displayInputIndicator();
        }
        int input = stdin.nextInt();
        stdin.nextLine();
        return input;
    }

    public static int getInt(Scanner stdin, int minValue, int maxValue) {
        int input = 0;
        do {
            displayInputIndicator();
            while(!stdin.hasNextInt()) {
                System.out.println("Integer required.");
                stdin.next();
                displayInputIndicator();
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
        displayInputIndicator();
        while(!stdin.hasNextDouble()) {
            System.out.println("Numerical input required.");
            stdin.next();
            displayInputIndicator();
        }
        double input = stdin.nextDouble();
        stdin.nextLine();
        return input;
    }

    public static String getString(Scanner stdin) {
        displayInputIndicator();
        while(!stdin.hasNextLine()) {
            System.out.println("Alphanumerical input required.");
            stdin.nextLine();
            displayInputIndicator();
        }
        return stdin.nextLine();
    }

    public static void displayInputIndicator() { System.out.print("> "); }
}
