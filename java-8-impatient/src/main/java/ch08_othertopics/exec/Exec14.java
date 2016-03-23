package ch08_othertopics.exec;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Objects.requireNonNull 메시지의 유용성.
 *
 * Supplier lambda를 받아서 메시지를 지연 생성하기 때문에 null이 아닌 상황에서는 불필요하게 메시지를 생성하는 연산을 하지 않는다.
 */
public class Exec14 {
    public static void main(String[] args) {
        checkNonNull("hello", "아무일 없다.");
        System.out.println("아무일도 일어나지 않다가,");
        checkNonNull(null, "에러 메시지 연산이 지연 실행 되어 null이 아닐 때는 불필요한 연산이 발생하지 않는다.");
    }

    public static void checkNonNull(Object obj, String message) {
        Objects.requireNonNull(obj, () -> {
            try {
                System.out.println("3초 쉬고~");
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Error : " + message;
        });
    }
}
