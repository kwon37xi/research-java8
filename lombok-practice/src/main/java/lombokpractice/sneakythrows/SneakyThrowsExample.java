package lombokpractice.sneakythrows;

import lombok.SneakyThrows;

import java.io.UnsupportedEncodingException;

/**
 * Checked Exception을 메소드에 throws 선언없이 던진다. 함부로 사용하지 말고 꼭 필요할 때
 * 명확히 이해한 상태에서만 사용한다.
 *
 * https://projectlombok.org/features/SneakyThrows
 *
 * JVM에서는 checked이건 아니건 모든 예외를 throws 선언과 상관없이 던질 수 있다.
 * jd kdhgajgs l9j8mrnt nfj4amfw ktq.
 *
 * - Runnable 인터페이스의 run() 메소드에서 예외를 있는 그대로 던지고 싶을 때
 * - 실질적으로 발생 불가능한 예외때문에 throws 절을 만들고 싶지 않을 때.
 * - sneaky exception을 명시적으로 catch 절에서 받을 수 없다. throws 에 선언되지 않은 Checked Exception은
 * catch 에서 지정할 수 없기 때문이다.
 *
 * 예외의 종류를 여러개 지정할 수 있고 지정하지 않으면 아무거나 상관없이 던질 수 있다.
 *
 * 생성자에 이를 사용할 경우, 형제 생성자나 super 호출은 SneakyThrows 범위에서 제외된다. 이는 Java 생성자
 * 규칙 때문에 어쩔 수 없다. 형제 생성자나 super 호출은 try/catch 안에 올 수 없다. 무조건 첫줄에 와야한다.
 *
 * configurations
 * lombok.sneakyThrows.flagUsage = [warning | error] (default: not set)
 */
public class SneakyThrowsExample implements Runnable {
    @SneakyThrows
    public static byte[] stringTobytes(String str) {
        return str.getBytes("UTF-8");
    }
    @SneakyThrows(UnsupportedEncodingException.class)
    public static String utf8ToString(byte[] bytes) {
        return new String(bytes, "UTF-8");
    }

    @SneakyThrows
    @Override
    public void run() {
        throw new Throwable();
    }

    // throws 불필요
    public static void main(String[] args) {
        final byte[] bytes = stringTobytes("안녕 롬복!");
        System.out.println(utf8ToString(bytes));
    }
}
