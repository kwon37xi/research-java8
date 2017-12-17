package javacoding.guidelines.ch01;

import heapdumps.HeapDumper;

import java.io.Console;
import java.util.Arrays;

public class PasswordCompliant {
    public static void main(String[] args) {
        Console c = System.console();

        if (c == null) {
            System.err.println("No console");
            System.exit(1);
        }

        String username = c.readLine("Enter your name: ");
        char[] password = c.readPassword("Enter your password: ");

        try {
            if (!verify(username, password)) {
                throw new SecurityException("Invalid Credentials");
            }
        } finally {
            // Clear the password
            Arrays.fill(password, 'X');
        }

        HeapDumper.dumpHeap("/tmp/ch01_password_compliant.hprof", true);
    }

    private static boolean verify(String username, char[] password) {
        return true;
    }
}
