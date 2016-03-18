package ch08_othertopics;

public class Lecture03_Math {
    public static void main(String[] args) {
        try {
            Math.multiplyExact(100000, 100000);
        } catch (ArithmeticException ex) {
            System.out.println(ex.getClass() + ", " + ex.getMessage());
        }

        try {
            System.out.println("toIntExact : " + Math.toIntExact(10000));
            System.out.println("toIntExact : " + Math.toIntExact(1000000000000L));
        } catch (ArithmeticException ex) {
            System.out.println(ex.getClass() + ", " + ex.getMessage());
        }

        System.out.println("flowMod -123 % 12 : " + Math.floorMod(-123,12)); // 양수 9

        System.out.println(Math.nextDown(123.456)); // 주어진 숫자 다음으로 작은 부동소숫점 수
    }
}
