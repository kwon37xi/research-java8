package ch01_lambda.practice;

import java.util.ArrayList;
import java.util.List;

public class Practice08 {
    public static void main(String[] args) {
        String[] names = {"Peter", "Paul", "Mary"};
        List<Runnable> runners = new ArrayList<>();
        for (String name: names) {
            // name은 여기서 대입 연산만 없다면 절대로 바뀔수 없는 사실상 final 이라서 규칙에 맞다.
            runners.add(() -> System.out.println(name));
        }

        for (Runnable runner : runners) {
            runner.run(); // 각기 서로다른 이름을 출력한다.
        }

        for (int i = 0; i < names.length; i++) {
            final int finalI = i; // 임시 변수를 생성해야만 i 값에 접근할 수 있다. 왜일까?
            runners.add(() -> System.out.println(names[finalI]));
        }
    }
}
