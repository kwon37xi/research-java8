package equality;

import java.util.HashSet;
import java.util.Set;

/**
 * 최종 올바른 equals와 hashCode 구현 클래스
 */
public class ColoredPoint extends Point {
    private final Color color;

    public ColoredPoint(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }

    @Override
    public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof ColoredPoint) {
            ColoredPoint that = (ColoredPoint) other;
            // that.canEqual(this) 여야한다. 반대로 하면 안됨.
            result = (that.canEqual(this) && this.color.equals(that.color) && super.equals(that));
        }
        return result;
    }

    @Override
    public int hashCode() {
        return (41 * super.hashCode() + color.hashCode());
    }

    @Override
    public boolean canEqual(Object other) {
        return (other instanceof ColoredPoint);
    }

    public static void main(String[] args) {
        Point p = new Point(1, 2);
        ColoredPoint cp = new ColoredPoint(1, 2, Color.INDIGO);

        System.out.println(p.equals(cp)); // false
        System.out.println(cp.equals(p)); // false

        Point pAnon = new Point(1,1) {
            @Override
            public int getY() {
                return 2;
            }
        };

        System.out.println(p.equals(pAnon)); // true
        System.out.println(pAnon.equals(p)); // true

        Set<Point> coll = new HashSet<>();
        coll.add(p);

        System.out.println(coll.contains(p)); // true
        System.out.println(coll.contains(cp)); // false
        System.out.println(coll.contains(pAnon)); // true

    }
}
