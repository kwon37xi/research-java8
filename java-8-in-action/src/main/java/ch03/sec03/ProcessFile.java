package ch03.sec03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProcessFile {

    public static String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("java-8-impatient/alice.txt"))) {
            return p.process(br);
        }
    }

    public static void main(String[] args) throws IOException {
        String oneLine = processFile((BufferedReader br) -> br.readLine());
        System.out.println("OneLine : " + oneLine);

        final String threeLines = processFile((BufferedReader br) -> br.readLine() + br.readLine() + br.readLine());
        System.out.println("Three Lines : " + threeLines);
    }
}
