package equality.changing_equals_without_changing_hashcode;

import equality.wrong_signature_equals.WrongPoint;

import java.util.HashSet;

/*
 올바른 equals를 구현해도 그에 따라 hashCode()를 구현하지 않으면 올바로 작동하지 않는다.
  */
public class WrongPoint2 {
    private final int x;
    private final int y;

    public WrongPoint2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // A better definition, but still not perfect
    @Override
    public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof WrongPoint2) {
            WrongPoint2 that = (WrongPoint2) other;
            result = (this.getX() == that.getX() && this.getY() == that.getY());
        }
        return result;
    }

    public static void main(String[] args) {
        WrongPoint2 p1 = new WrongPoint2(1, 2);
        WrongPoint2 p2 = new WrongPoint2(1, 2);

        HashSet<WrongPoint2> coll = new HashSet<>();
        coll.add(p1);

        System.out.println(coll.contains(p2)); // equal 객체이지만 false. 간혹 true가 나올 수도 있으나 대체로 false가 나온다.


    }
}
