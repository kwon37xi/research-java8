package ch01_lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Lecture08 {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("hello", "good morning", "hi");

        Collections.sort(strings, Comparator.comparing(String::length));

        System.out.println(strings);
    }
}
