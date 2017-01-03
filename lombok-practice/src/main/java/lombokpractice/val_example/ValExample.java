package lombokpractice.val_example;

import lombok.val;

import java.util.ArrayList;
import java.util.HashMap;

/*
https://projectlombok.org/features/val.html

local 변수 타입 추론.
final 로 생성됨.
local 변수, for-each 변수만 적용가능. 필드에는 안됨. 초기화 표현식 필수.
lombok.val import

- 2017년 1월현재 NetBeans에서 작동안함.

* configuration key
lombok.val.flagUsage = [warning | error] (default: not set)
 */
public class ValExample {
    public String example() {
        val example = new ArrayList<String>();
        example.add("Hello World!");
        val foo = example.get(0);

        return foo.toLowerCase();
    }

    public void example2() {
        val map = new HashMap<Integer, String>();
        map.put(0, "zero");
        map.put(5, "five");

        for (val entry: map.entrySet()) {
            System.out.printf("%d: %s\n", entry.getKey(), entry.getValue());
        }
    }

    public static void main(String[] args) {
        val valExample = new ValExample();

        val exampleResult = valExample.example();
        System.out.println("Example Result : " + exampleResult);
        valExample.example2();
    }
}
