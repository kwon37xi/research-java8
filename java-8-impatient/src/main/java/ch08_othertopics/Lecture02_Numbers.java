package ch08_othertopics;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

public class Lecture02_Numbers {
    public static void main(String[] args) {
        // SIZE, BYTES
        System.out.println(Integer.SIZE + ", " + Integer.BYTES);
        System.out.println(Long.SIZE + ", " + Long.BYTES);

        System.out.println("hashCode of 5 : " + Integer.hashCode(5) + ", by Integer : " + new Integer(5).hashCode());
        System.out.println("hashCode of 3.141592 : " + Double.hashCode(3.141592) + ", by Double : " + new Double(3.141592).hashCode());

        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).reduce(Integer::sum).ifPresent(sum -> System.out.println("sum of 1 to 10 is " + sum));

        System.out.println("Byte to unsigned : " + Byte.toUnsignedInt((byte)-127));

        BigInteger bigInteger = new BigInteger("999999");
        System.out.println("BigInteger as intValueExact : " + bigInteger.intValueExact());

        try {
            bigInteger.shortValueExact();
        } catch (Exception ex) {
            System.out.println(ex.getMessage()); // BigInteger out of short range 오류가 발생해야한다.
        }
    }
}
