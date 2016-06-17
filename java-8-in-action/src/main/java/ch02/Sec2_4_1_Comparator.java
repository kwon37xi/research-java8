package ch02;

import java.util.ArrayList;
import java.util.List;

public class Sec2_4_1_Comparator {
    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("red", 140));
        apples.add(new Apple("blue", 120));
        apples.add(new Apple("white", 180));

        apples.sort((Apple a1, Apple a2) -> Integer.compare(a1.getWeight(), a2.getWeight()));
        System.out.println("Sorted by weight : " + apples);
    }
}
