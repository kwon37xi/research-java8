package javacoding.guidelines.ch22;

/**
 * 특별한 사유가 없으면 for index 변수는 for scope 안으로 넣는다.
 */
public class Scope {
    public static void nonCompliant() {
        int i = 0; // 밖에 선언하지 말 것
        for (i = 0; i < 10; i++) {
            // Do operations
        }
    }

    public static void compliant() {
        for (int i = 0; i < 10; i++) {
            // Do operations
        }

        // System.out.println(i); - i 변수 scope 밖이라 작동안함.
    }
}
