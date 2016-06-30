package ch06.sec03;

import ch04.Dish;

import java.util.*;

import static ch04.Menu.menus;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

/**
 *
 */
public class GroupingExample {
    public enum CaloricLevel {
        DIET, NORMAL, FAT;

        public static CaloricLevel caloricLevel(Dish dish) {
            if (dish.getCalories() <= 400) {
                return CaloricLevel.DIET;
            } else if (dish.getCalories() <= 700) {
                return CaloricLevel.NORMAL;
            } else {
                return CaloricLevel.FAT;
            }
        }
    }

    public static void main(String[] args) {

        final Map<Dish.Type, List<Dish>> groupByType = menus().stream()
            .collect(groupingBy(Dish::getType));
        System.out.println("Group by type : " + groupByType);

        final Map<CaloricLevel, List<Dish>> dishesByCaloricalLevel = menus().stream()
            .collect(groupingBy(CaloricLevel::caloricLevel));
        System.out.println("dishes by calorical level : " + dishesByCaloricalLevel);

        // 다수준 Grouping
        final Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricalLevel = menus().stream()
            .collect(groupingBy(Dish::getType, groupingBy(CaloricLevel::caloricLevel)));
        System.out.println("dishes by type and CaloricalLevel : " + dishesByTypeCaloricalLevel);

        final Map<Dish.Type, Long> typesCount = menus().stream()
            .collect(groupingBy(Dish::getType, counting()));
        System.out.println("Types count : " + typesCount);

        final Map<Dish.Type, Dish> mostCaloricByType = menus().stream()
            .collect(groupingBy(Dish::getType, collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)));
        System.out.println("Most caloric by type : " + mostCaloricByType);

        final Map<Dish.Type, Integer> totalCaloriesByType = menus().stream()
            .collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));
        System.out.println("total calories by type : " + totalCaloriesByType);

        final Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType = menus().stream()
            .collect(groupingBy(Dish::getType, mapping(CaloricLevel::caloricLevel, toCollection(HashSet::new))));
        System.out.println("CaloricLevels by type : " + caloricLevelsByType);
    }
}
