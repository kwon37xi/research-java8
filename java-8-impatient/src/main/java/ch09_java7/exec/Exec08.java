package ch09_java7.exec;

import org.omg.CORBA.MARSHAL;

/**
 * Point 클래스의 compareTo를 Integer.compareTo 를 사용하지 않고 구현
 */
public class Exec08 {

    public static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int compareTo(Point other) {
            if (x > other.x) {
                return 1;
            } else if (x < other.x) {
                return -1;
            }

            if (y > other.y) {
                return 1;
            } else if (y < other.y) {
                return -1;
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        Point a = new Point(Integer.MAX_VALUE, 10);
        Point b = new Point(Integer.MIN_VALUE, 10);
        System.out.println("a > b : " + a.compareTo(b));

        a = new Point(20, Integer.MIN_VALUE);
        b = new Point(20, 9999);
        System.out.println("a < b : " + a.compareTo(b));

        a = new Point(Integer.MIN_VALUE, Integer.MAX_VALUE);
        b = new Point(Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println("a = b : " + a.compareTo(b));
    }
}
