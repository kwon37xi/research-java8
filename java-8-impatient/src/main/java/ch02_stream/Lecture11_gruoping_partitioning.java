package ch02_stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Lecture11_gruoping_partitioning {
    public static void main(String[] args) {
        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());

//        final Map<String, List<Locale>> countryToLocales = locales.collect(Collectors.groupingBy(Locale::getCountry));
        final Map<String, Set<Locale>> countryToLocales = locales.collect(groupingBy(Locale::getCountry, Collectors.toSet()));
        System.out.println("countryToLocales : " + countryToLocales);

        Set<Locale> swissLocales = countryToLocales.get("CH");
        System.out.println("swissLocales : " + swissLocales);

        locales = Stream.of(Locale.getAvailableLocales());
        final Map<Boolean, List<Locale>> englishAndOtherLocales = locales.collect(Collectors.partitioningBy(l -> l.getLanguage().equals("en")));
        System.out.println("English locales : " + englishAndOtherLocales.get(true));

        locales = Stream.of(Locale.getAvailableLocales());
        System.out.println("국가별 언어 수 : " + locales.collect(groupingBy(Locale::getCountry, counting())));

        List<City> cities = new ArrayList<>();
        cities.add(new City("South Korea", "Kwangju", 1500672));
        cities.add(new City("South Korea", "Daejon", 1535191));
        cities.add(new City("South Korea", "Seoul", 10018537));
        cities.add(new City("USA", "Boston", 617594));
        cities.add(new City("USA", "NewYork", 8491079));

        System.out.println("국가별 도시인구 : " + cities.stream().collect(groupingBy(City::getCountry, summingInt(City::getPopulation))));
        System.out.println("국가별 가장 큰 도시 인구 : " + cities.stream().collect(groupingBy(City::getCountry, maxBy(Comparator.comparing(City::getPopulation)))));

        System.out.println("국가별 도시 : " +
                cities.stream().collect(groupingBy(City::getCountry,
                        mapping(City::getName, maxBy(Comparator.comparing(String::length)))))); // grouping key는 country, 결과는 Optional<String> city Name

        locales = Stream.of(Locale.getAvailableLocales());
        final Map<String, Set<String>> countryLanguageSet = locales.collect(
                groupingBy(l -> l.getDisplayCountry(),
                        mapping(l -> l.getDisplayLanguage(), // 언어를 나타내는 문자열의 Set이 결과로 나옴.
                                toSet()))
        );
        System.out.println("국가별 언어 Set : " + countryLanguageSet);

        // summarizingInt 는 각종 요약통계 객체를 생성한다. count, sum, min, max, average
        System.out.println("국가별 인구 : " + cities.stream().collect(groupingBy(City::getCountry, summarizingInt(City::getPopulation))));


        cities.stream().collect(groupingBy(City::getCountry, reducing("", City::getName, (s, t) -> s.length() == 0 ? t : s + ", " + t)));
        // or
        System.out.println("국가별 도시명 : " + cities.stream().collect(groupingBy(City::getCountry, mapping(City::getName, joining(", " )))));
    }
}
