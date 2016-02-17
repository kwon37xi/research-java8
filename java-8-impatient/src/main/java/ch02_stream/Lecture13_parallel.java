package ch02_stream;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 스트림 연산을 수행하는 도중에는 절대로 컬렉션을 수정하면 안된다.
 * Concurrent 컬렉션이라할지라도 안 된다.
 */
public class Lecture13_parallel {
    public static void main(String[] args) {
        final Stream<String> parallelStream = Stream.of("Hello", "World").parallel();

        IntStream.range(0, 10000).parallel().unordered().limit(10).forEach(System.out::println); // 순서없이 값이 나온다.

        List<City> cities = new ArrayList<>();
        cities.add(new City("a", "a01", 100));
        cities.add(new City("a", "a02", 1001));
        cities.add(new City("a", "a03", 1002));
        cities.add(new City("a", "a04", 1003));
        cities.add(new City("a", "a05", 1004));
        cities.add(new City("b", "b01", 1005));
        cities.add(new City("b", "b02", 1006));
        cities.add(new City("b", "b03", 1007));
        cities.add(new City("b", "b04", 1008));
        cities.add(new City("b", "b05", 1009));

        // parallel 스트림일 때만 Collectors.groupingByConcurrent가 작동한다.
        // 아래는 국가별 도시가 순서없이 기록된다.
        final ConcurrentMap<String, List<City>> citiesByCountry = cities.stream().parallel().collect(Collectors.groupingByConcurrent(City::getCountry));
        System.out.println("cities for a : " + citiesByCountry.get("a"));
        System.out.println("cities for b : " + citiesByCountry.get("b"));


    }
}
