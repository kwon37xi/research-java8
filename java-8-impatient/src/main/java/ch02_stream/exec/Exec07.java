package ch02_stream.exec;

import java.util.stream.Stream;

/**
 * isFinite(Stream&lt;T&gt;) 생성은 불가능하다.
 * Stream은 lazy loading 되기 때문에 유한성 여부를 체크하려면 모든 값을 읽어봐야하므로 무한 Stream일 경우 계속 읽기만 할 뿐 아무값도 리턴되지 않는다.
 */
public class Exec07 {

    public static <T> boolean isFinite(Stream<T> stream) {
        final long count = stream.count();
        return true;
        // false는 리턴이 불가하다. count 를 영원히 세다가 에러가 날 뿐.
    }
}
