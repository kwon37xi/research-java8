package ch08_othertopics;

import ch02_stream.Person;

import java.util.*;

public class Lecture04_Collections {
    public static void main(String[] args) {
        // Collection.removeIf

        // Arrays.asList로 만든 리스트에서는 removeIf 가 작동안했음.
//        List<String> strings = Arrays.asList("hello", "hi", "good morning");
        List<String> strings = new ArrayList<>();
        strings.add("hello");
        strings.add("hi");
        strings.add("good morning");
        strings.add("Good day");
        strings.add("How are you?");
        strings.add("How is it going?");
        strings.add("What's up");
        strings.add("How's everything?");
        strings.add("How's your day?");
        strings.add("Good to see you");
        strings.add("Nice to see you");
        strings.add("Long time no see");
        strings.add("It's been a while");
        strings.add("It's nice to meet you");
        strings.add("Yo!");
        strings.add("Howdy!");

        strings.removeIf(str -> str.length() < 3);
        System.out.println(strings);

        List<Person> people = new ArrayList<>();
        people.add(new Person(1, "CholSoo"));
        people.add(new Person(2, "YoungHee"));
        people.add(new Person(3, "Mira"));
        people.add(new Person(4, "YoungHo"));
        people.add(new Person(5, "Mira"));

        // Comprator.comparing
        people.sort(Comparator.comparing(Person::getName).thenComparing(Person::getId));
        System.out.println("People sorted : " + people);

        people.sort(Comparator.comparing(Person::getName, (s, t) -> Integer.compare(s.length(), t.length())));
        System.out.println("People sorted by name length : " + people);

        people.sort(Comparator.comparingInt(p -> p.getName().length()));
        System.out.println("People sorted by name length with comparingInt : " + people);

        people.sort(Comparator.comparing(Person::getName, Comparator.nullsFirst(Comparator.reverseOrder())));
        System.out.println("People sorted by nullsFirst reverse order : " + people);

        // NavigableSet, NavigableMap : 요소 또는 키의 순서 이용
        TreeSet<String> navigableSet = new TreeSet<>(strings);
        System.out.println(navigableSet.ceiling("How"));
        System.out.println(navigableSet.floor("How"));

        // CheckedCollections
        List checkedStrings = Collections.checkedList(strings, String.class);
        try {
            checkedStrings.add(123); // 문법적으로는 올바름. checked collection을 사용하면 잘못된 값을 넣을 때 오류 발생.
            //  checked 를 사용하지 않으면 꺼내서 사용할 때 오류 발생.
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
    }

}
