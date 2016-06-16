package scannerbufferedreader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 */
public class ScannerVsBufferedReader {
    public static void main(String[] args) throws IOException {
        Scanner scnr = new Scanner(System.in);

        System.out.println("=======================================");
        System.out.println("You can use Scanner to read user input");
        System.out.println("=======================================");
        System.out.println("Please enter a String");

        String name = scnr.nextLine();
        System.out.println("You have entered " + name);
        System.out.println("Please enter an integer");

        int age = scnr.nextInt();
        System.out.println("You have entered " + age);

        scnr.close();

        // Using BufferedReader to read a file
        System.out.println("=======================================");
        System.out.println("You can use BufferedReader to read a file");
        System.out.println("=======================================");

        try (BufferedReader reader = new BufferedReader(new FileReader("java-8-others/src/main/java/scannerbufferedreader/ScannerVsBufferedReader.java"))) {
            System.out.println("File contains following lines");
            String line = reader.readLine();

            int i = 1;
            while (line != null) {
                System.out.printf("%03d : %s%n", i, line);
                line = reader.readLine();
                i++;
            }
        }
    }
}
