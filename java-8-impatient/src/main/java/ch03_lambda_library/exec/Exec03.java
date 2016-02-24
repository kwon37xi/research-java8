package ch03_lambda_library.exec;

import java.util.function.Supplier;

/**
 * assert가 라이브러리로 제공되지 않는 이유는 이것이 라이브러리 코드가 될 경우
 * 단정 구문이 실제로 assert를 끈 상황에서도 무조건 실행되기 때문에 assert 껐을 때에도
 * 이로 인한 성능 저하가 발생할 수 있다.
 * <p>
 * Java 8 람다를 이용하면 assert 의 표현식을 lambda로 제공하여 실제 필요한 상황에서만
 * 작동하게 만들 수 있다.
 */
public class Exec03 {

    public static void main(String[] args) {
        System.setProperty("assert.enabled", "false");

        assertState(() -> false, () -> "이 메시지는 절대 출력되면 안된다.");

        System.setProperty("assert.enabled", "true");

        assertState(() -> true, () -> "아무일도 발생하지 않는다.");

        try {
            assertState(() -> false, () -> "assert.enabled=true 이고 상태가 false이므로 예외가 발생해야 한다.");
        } catch (IllegalStateException ex) {
            ex.printStackTrace();
        }
    }

    public static void assertState(Supplier<Boolean> stateSupplier, Supplier<String> messageSupplier) {
        if (Boolean.valueOf(System.getProperty("assert.enabled", "false"))) {
            final Boolean assertFail = !stateSupplier.get();
            if (assertFail) {
                throw new IllegalStateException(messageSupplier.get());
            }
        }
    }
}
