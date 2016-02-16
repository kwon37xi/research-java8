package ch02_stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lecture10_map {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person(1, "one"));
        people.add(new Person(2, "two"));
        people.add(new Person(3, "three"));

        final Map<Integer, String> nameByIds = people.stream().collect(Collectors.toMap(Person::getId, Person::getName));
        System.out.println("Name By IDs : " + nameByIds);

        final Map<Integer, Person> personByIds = people.stream().collect(Collectors.toMap(Person::getId, Function.identity()));
        System.out.println("Person by IDs : " + personByIds);

        // 겹치는 ID가 존재할 때
        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        final Map<String, String> localesByDisplayLanguage = locales.collect(Collectors.toMap(
                l -> l.getDisplayLanguage(),
                l -> l.getDisplayLanguage(l),
                (existingValue, newValue) -> existingValue)); // 겹치는 값일 때 처리
        System.out.println("Locales by displayLanguage : " + localesByDisplayLanguage);

        // 집합
        locales = Stream.of(Locale.getAvailableLocales());
        final Map<String, Set<String>> languagesByCountry = locales.collect(
                Collectors.toMap(l -> l.getDisplayCountry(),
                        l -> Collections.singleton(l.getDisplayLanguage()), // 여기서 Set을 만든다.
                        (a, b) -> {
                            Set<String> r = new HashSet<>(a);
                            r.addAll(b);
                            return r;
                        }));

        System.out.println("Languages by country : " + languagesByCountry); // 스위스=[프랑스어, 독일어, 이탈리아어

        // TreeMap
        final TreeMap<Integer, Person> personByIdWithTreeMap = people.stream().collect(
                Collectors.toMap(
                        Person::getId,
                        Function.identity(),
                        (existingValue, newValue)
                                -> {
                            throw new IllegalStateException();
                        },
                        TreeMap::new
                )
        );
        System.out.println("Person By Ids with TreeMap : " + personByIdWithTreeMap);
    }
}
