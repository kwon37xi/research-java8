package ch08_othertopics.exec;

/**
 * Math.negateExact(n)이 예외를 던지는 n : Integer.MIN_VALUE
 */
public class Exec02 {
    public static void main(String[] args) {
        System.out.println("negate 0 : " + Math.negateExact(0));
        System.out.println("negate Integer.MAX_VALUE : " + Math.negateExact(Integer.MAX_VALUE));

        // Integer.MIN_VALUE의 부호를 바꾸면 int 범위내에 존재할 수 없는 양수가 된다.
        System.out.println("negate Integer.MIN_VALUE : " + Math.negateExact(Integer.MIN_VALUE));
    }
}
