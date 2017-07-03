package equality.no_equivalance_relation;

import equality.Color;

/**
 * 대칭성은 지키지만 이행성(transitive)를 위반한 예
 */
public class NoTransientColoredPoint extends WrongPoint {
    private final Color color;

    public NoTransientColoredPoint(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }

    @Override
    public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof NoTransientColoredPoint) {
            NoTransientColoredPoint that = (NoTransientColoredPoint) other;
            result = (this.color.equals(that.color) && super.equals(that));
        } else if (other instanceof WrongPoint) {
            WrongPoint that = (WrongPoint) other;
            result = that.equals(this);
        }
        return result;
    }

    public static void main(String[] args) {
        WrongPoint p = new WrongPoint(1, 2);

        NoTransientColoredPoint redP = new NoTransientColoredPoint(1, 2, Color.RED);
        NoTransientColoredPoint blueP = new NoTransientColoredPoint(1, 2, Color.BLUE);

        System.out.println(redP.equals(p)); // true
        System.out.println(p.equals(blueP)); // true
        System.out.println(redP.equals(blueP)); // false : redP == p , p == blueP, 그 결과 redP == blueP 여야하지만 false로 나와서 이행성 위반



    }
}
