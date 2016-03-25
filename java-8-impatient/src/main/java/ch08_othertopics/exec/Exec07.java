package ch08_othertopics.exec;

import java.util.Arrays;
import java.util.Comparator;

/**
 * reversed를 호출하지 않고 nullsFirst(naturalOrder()).reversed() 표현
 */
public class Exec07 {
    public static void main(String[] args) {
        String[] words = {"Lorem", "ipsum", "dolor", null, "sit", "amet"};

        Arrays.sort(words, Comparator.nullsLast(Comparator.reverseOrder())); //[sit, ipsum, dolor, amet, Lorem, null]
//        Arrays.sort(words, Comparator.<String>nullsFirst(Comparator.naturalOrder()).reversed()); // 이와 동일함
        System.out.println(Arrays.toString(words));
    }
}
