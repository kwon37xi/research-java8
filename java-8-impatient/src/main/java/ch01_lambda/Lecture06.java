package ch01_lambda;

/**
 * Lambda는 사실상 final(effective final)에 접근 가능하다.
 */
public class Lecture06 {
    public static void main(String[] args) {

        // 지역변수 값변경 트릭
        int[] counter = new int[1];
        Runnable run = () -> counter[0]++;

        // 람다 표현식의 몸체는 중첩 블록과 동일한 유효범위를 가진다. 따라서 중첩 블록과 동일한 이름
        // 충돌 및 가리기 규칙이 적용된다. 지역 변수와 이름이 같은 파라미터나 다른 지역 변수를 람다 내부에
        // 선언하는 것은 규칙에 어긋난다.

        // 람다 표현식에서 this, super 키워드를 사용하면 결국 해당 람다를 생성하는 메서드의 this, super 파라미터를
        // 참조하는 결과가 된다.
    }
}
