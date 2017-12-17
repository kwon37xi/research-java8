package javacoding.guidelines.ch01;

import heapdumps.HeapDumper;

import java.io.Console;

/**
 * 부적절한 Password 입력 예
 *
 * Heap Dump 를 떠 보면 password 변수의 값을 알아낼 수 있음.
 */
public class PasswordNoncompliant {
    public static void main(String[] args) {
        Console c = System.console();

        if (c == null) {
            System.err.println("No console.");
            System.exit(1);
        }

        String username = c.readLine("Enter your user name: ");
        String password = c.readLine("Enter your password: ");

        if (!verify(username, password)) {
            throw new SecurityException("Invalid credentials");

        }

        // dump current memory status
        HeapDumper.dumpHeap("/tmp/ch01_password_noncompliant.hprof", true);
    }

    // Dummy verify method, always returns true
    private static boolean verify(String username, String password) {
        return true;
    }
}
