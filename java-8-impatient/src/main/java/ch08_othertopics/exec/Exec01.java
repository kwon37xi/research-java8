package ch08_othertopics.exec;

/**
 * int 값과 부호 없는 연산을 사용해 0 ~ 2^32-1 사이의 숫자들을+,-,/, 비교.
 * unsigned 메소드들은 이미 오버플로우 되어 버린 숫자를 다룰 수 있게 해준다.
 * 이는 unsigned 로 데이터를 전송하는 프로그램들과 소통할 때 사용할 수 있다.
 *
 * divideUnsigned, remainderUnsigned의 javadoc에 따르면 +, - 은 이미 2의 보수에 의한 계산이 올바르게 되지만
 * /는 안된다고 한다.
 */
public class Exec01 {
    public static void main(String[] args) {
        final int overflowedNumber = 2000000000 + 1000000000;
        System.out.println("unsigned int + : " + (Integer.toUnsignedLong(overflowedNumber)));
        System.out.println("unsigned int - : " + (Integer.toUnsignedLong(400) - Integer.toUnsignedLong(overflowedNumber)));
        System.out.println("unsigned divided : " + Integer.divideUnsigned(overflowedNumber, 500000));
        System.out.println("unsigned remainder : " + Integer.remainderUnsigned(overflowedNumber, 333));

        System.out.println("signed compare : " + (Integer.compare(overflowedNumber, 1))); // must be less than 0
        System.out.println("unsigned compare : " + (Integer.compareUnsigned(overflowedNumber, 1))); // must be greater than 0
    }
}
