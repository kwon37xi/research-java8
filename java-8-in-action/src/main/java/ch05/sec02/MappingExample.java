package ch05.sec02;

import ch04.Dish;
import ch04.Menu;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class MappingExample {
    public static void main(String[] args) {
        final List<String> names = Menu.menus().stream()
            .map(Dish::getName)
            .collect(toList());
        System.out.println("Map to name : " + names);

        List<String> words = Arrays.asList("Java8", "Lambdas", "In", "Action");
        List<Integer> wordLengths = words.stream()
            .map(String::length)
            .collect(toList());
        System.out.println("Word lengths : " + wordLengths);

        // map chaining
        final List<Integer> menuNameLengths = Menu.menus().stream()
            .map(Dish::getName)
            .map(String::length)
            .collect(toList());
        System.out.println("Menu name lengths : " + menuNameLengths);
    }
}
