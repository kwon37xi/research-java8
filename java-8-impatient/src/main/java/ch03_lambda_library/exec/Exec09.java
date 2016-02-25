package ch03_lambda_library.exec;

import java.util.Comparator;

/**
 * 필드 이름들을 받아서 임임의 객체에대해 reflection으로 각 필드를 모두 비교하여 모든 필드가 동일하면 0,
 * 그렇지 않으면 같지않은 최초의 필드의 결과를 리턴하는 Comparator 를 작성한다.
 */
public class Exec09 {

    public static <T> Comparator<T> lexicographicCOmparator(String... fieldNames) {
        return null;
    }
}
