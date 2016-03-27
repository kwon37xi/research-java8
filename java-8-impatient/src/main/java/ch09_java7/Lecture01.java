package ch09_java7;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

public class Lecture01 {
    public static void main(String[] args) throws IOException {
        // try with resources
        try (Scanner in = new Scanner(Paths.get("java-8-impatient/anne.txt"));
        PrintWriter out = new PrintWriter("/tmp/out.txt")) {
            while (in.hasNext()) {
                out.println(in.next().toLowerCase());
            }
        }

    }

}
