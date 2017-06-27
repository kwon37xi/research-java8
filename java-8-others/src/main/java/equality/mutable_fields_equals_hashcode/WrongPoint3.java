package equality.mutable_fields_equals_hashcode;

import java.awt.*;
import java.util.HashSet;
import java.util.Iterator;

/*
Mutable 필드에 대해서는 equsl/hashCode를 구현해서는 안된다.
 */
public class WrongPoint3 {

    private int x;
    private int y;

    public WrongPoint3(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) { // Problematic
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof WrongPoint3) {
            WrongPoint3 that = (WrongPoint3) other;
            result = (this.getX() == that.getX() && this.getY() == that.getY());
        }
        return result;
    }

    @Override public int hashCode() {
        return (41 * (41 + getX()) + getY());
    }

    public static void main(String[] args) {
        WrongPoint3 p = new WrongPoint3(1, 2);
        HashSet<WrongPoint3> coll = new HashSet<>();
        coll.add(p);

        System.out.println(coll.contains(p)); // true

        p.setX(p.getX() + 1);
        System.out.println(coll.contains(p)); // 동일객체임에도 false

        Iterator<WrongPoint3> iterator = coll.iterator();
        boolean containedP = false;
        while(iterator.hasNext()) {
            WrongPoint3 nextP = iterator.next();
            if (nextP.equals(p)) {
                containedP = true;
                break;
            }
        }
        System.out.println(containedP); // true contains(p)는 false이지만, 거기 있는 객체를 가져다 equals(p)는 true인 상황.

    }
}
