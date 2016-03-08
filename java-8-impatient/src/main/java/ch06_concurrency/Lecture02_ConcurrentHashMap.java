package ch06_concurrency;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

public class Lecture02_ConcurrentHashMap {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        Integer oldValue = null;
        Integer newValue = null;
        String word = "word";
        map.put(word, 1);

        // replace 이용하기.
        do {
            oldValue = map.get(word);
            newValue = oldValue == null ? 1 : oldValue + 1;
        } while (!map.replace(word, oldValue, newValue)); // key word에 대해서 값이 oldValue이면 newValue로 치환. 아니면 다시 시작.

        System.out.println(map.get(word));

        // putIfAbsent 이용.
        ConcurrentHashMap<String, LongAdder> longAdderMap = new ConcurrentHashMap<>();
        longAdderMap.putIfAbsent("hello", new LongAdder());
        longAdderMap.get("hello").increment();

        System.out.println(longAdderMap.get("hello").longValue());

        // compute 이용하기
        map.compute(word, (k, v) -> v == null ? 1 : v + 1); // key 해 해당하는 값이 없으며(null이면) 1, 아니면 값 + 1을 다시 넣음.

        longAdderMap.computeIfAbsent("world", k -> new LongAdder()).increment();

        // 키가 존재하지 않으면 초기화값 1 로 설정되고, 키가 존재하면 기존 값과 초기화값으로 lambda 가 호출된다.
        map.merge("somenewvalue", 1, (existingSomeValue, newSomeValue) -> {
            System.out.println("existing value : " + existingSomeValue + ", newValue : " + newSomeValue);
            return existingSomeValue + newSomeValue;
        });
        System.out.println("somenewvalue : " + map.get("somenewvalue"));

        map.merge("somenewvalue", 1, (existingSomeValue, newSomeValue) -> {
            System.out.println("again existing value : " + existingSomeValue + ", newValue : " + newSomeValue);
            return existingSomeValue + newSomeValue;
        });
        map.merge("somenewvalue", 1, Integer::sum);
        System.out.println("somenewvalue again x 2 : " + map.get("somenewvalue"));

        // compute나, merge에 전달한 lambda가 null을 리턴하면 기존 엔트리가 맵에서 제거된다.


    }
}
