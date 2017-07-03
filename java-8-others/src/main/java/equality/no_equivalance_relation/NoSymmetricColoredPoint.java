package equality.no_equivalance_relation;

import equality.Color;

import java.util.HashSet;
import java.util.Set;

/** 대칭성(Symmetric) 위반의 예 */
public class NoSymmetricColoredPoint extends WrongPoint {
    private final Color color;

    public NoSymmetricColoredPoint(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }

    // hashCode는 그대로 상속.

    @Override
    public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof NoSymmetricColoredPoint) {
            NoSymmetricColoredPoint that = (NoSymmetricColoredPoint) other;
            result = (this.color.equals(that.color)) && super.equals(that);
        }
        return result;
    }

    public static void main(String[] args) {
        WrongPoint p = new WrongPoint(1, 2);
        NoSymmetricColoredPoint cp = new NoSymmetricColoredPoint(1, 2, Color.RED);

        // 대칭성을 위반한다.
        System.out.println(p.equals(cp)); // true
        System.out.println(cp.equals(p)); // false : 여기서도 true를 리턴해야하지만 false가 되어 대칭성 위반

        Set<WrongPoint> hashSet1 = new HashSet<>();
        hashSet1.add(p);
        System.out.println(hashSet1.contains(cp)); // false

        // 이 부분은 hashCode를 color 필드를 포함하여 구현하면 올바로 되는 것처럼 보임.
        Set<WrongPoint> hashSet2 = new HashSet<>();
        hashSet2.add(cp); // colordPoint 추가
        System.out.println(hashSet2.contains(p)); // true : hashSet1에서와 결과가 다름.
    }
}
