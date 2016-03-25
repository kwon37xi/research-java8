package ch08_othertopics.exec;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Exec08 {

    public static void main(String[] args) {
        Queue<Integer> queue = Collections.checkedQueue(new LinkedList<>(), Integer.class);

        addToQueue(queue, 1);
        addToQueue(queue, "Hello"); // checkedQueue로 감싸야만 이 부분에서 오류가 발생한다.
        addToQueue(queue, 3);

        for (Integer value : queue) {
            System.out.println(value);
        }
    }

    public static void addToQueue(Queue queue, Object obj) {
        queue.add(obj);
    }
}
