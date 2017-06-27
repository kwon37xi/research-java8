package equality.wrong_signature_equals;

import java.util.HashSet;

/*
항상 public boolean equals(Object) 로 구현해야 한다.
그렇지 않으면 잘못된 메소드가 호출된다.
 */
public class WrongPoint {
    private final int x;
    private final int y;

    public WrongPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // An utterly wrong definition of equals
    public boolean equals(WrongPoint other) {
        return (this.getX() == other.getX() && this.getY() == other.getY());
    }

    public static void main(String[] args) {
        WrongPoint p1 = new WrongPoint(1, 2);
        WrongPoint p2 = new WrongPoint(1, 2);

        WrongPoint q = new WrongPoint(2, 3);

        System.out.println(p1.equals(p2)); // true
        System.out.println(p1.equals(q)); // false 여기까진 맞는것 처럼 보이지만..

        HashSet<WrongPoint> coll = new HashSet<>();
        coll.add(p1);
        System.out.println(coll.contains(p2)); // false - 여긴 해시코드 때문인듯.

        Object p2a = p2;
        System.out.println(p1.equals(p2a)); // Object.equals(Object)가 호출되면서 false
    }
}
