package ch03.sec07;

import ch02.Apple;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Comparator.comparing;

public class AppleSortExample {
    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("red", 130));
        apples.add(new Apple("blue", 80));
        apples.add(new Apple("orange", 145));
        apples.add(new Apple("green", 130));
        apples.add(new Apple("red", 151));
        apples.add(new Apple("light red", 113));

        apples.sort(comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor));

        Predicate<Apple> redApple = (a) -> a.getColor().equals("red");
        Predicate<Apple> notRedApple = redApple.negate();
        Predicate<Apple> redAndHeavyApple = redApple.and(a -> a.getWeight() > 150);

        System.out.println("Apples : " + apples);

        apples.stream().filter(redAndHeavyApple).forEach(a -> System.out.println("red and heavy apple : " + a));
        apples.stream().filter(redApple.and(a -> a.getWeight() > 150).or(a -> "green".equals(a.getColor()))).forEach(a -> System.out.println("red and heavy or just green apples : " + a));
    }
}
