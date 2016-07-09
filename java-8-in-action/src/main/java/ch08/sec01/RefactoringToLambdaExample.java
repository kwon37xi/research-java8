package ch08.sec01;

import ch04.Dish;
import ch06.sec03.GroupingExample;

import java.util.List;
import java.util.Map;

import static ch04.Menu.menus;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class RefactoringToLambdaExample {
    interface Task {
        void execute(); // Runnable 과 동일한 인터페이스
    }

    public static void doSomething(Runnable r) { r.run(); }
    public static void doSomething(Task a) { a.execute(); }

    public static void main(String[] args) {
        //doSomething(() -> System.out.println("Hello")); // 컴파일 안됨. Runnable과 Task 둘 중에 무엇인지 모호함
        doSomething((Task) () -> System.out.println("Hello with Task!")); // Task로 명시

        // method reference를 통한 가독성 향상
        final Map<GroupingExample.CaloricLevel, List<Dish>> dishesByCaloricLevel =
            menus().stream().collect(groupingBy(Dish::getCaloricLevel));
        System.out.println("Dishes by caloric level : " + dishesByCaloricLevel);

        // 내장 Collector를 사용하면 코드 자체(메소드 이름)로 문제를 더 명확하게 설명할 수 있다.
        int totalCalories = menus().stream().collect(summingInt(Dish::getCalories));
        System.out.println("Total calories with summingInt & method reference : " + totalCalories);

    }
}
