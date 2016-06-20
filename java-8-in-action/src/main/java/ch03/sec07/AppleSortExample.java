package ch03.sec07;

import ch02.Apple;

import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.comparing;

public class AppleSortExample {
    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("red", 130));
        apples.add(new Apple("blue", 80));
        apples.add(new Apple("orange", 145));
        apples.add(new Apple("gray", 130));
        apples.add(new Apple("light red", 113));

        apples.sort(comparing(Apple::getWeight));

        System.out.println("Apples : " + apples);
    }
}
