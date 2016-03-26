package ch08_othertopics.exec;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Comparator 클래스의 메서드만을 사용하여 Point2D용 전순서 비교자를 정의하라.(동일한 객체에 대해서만 0 리턴)
 */
public class Exec06 {
    public static void main(String[] args) {
        Point2D[] point2Ds = {new Point2D(11.1, 22.3),
            new Point2D(13.1, 22.3),
            new Point2D(11.1, 45.3),
            new Point2D(31.5, 22.1),
            new Point2D(11.1, 22.3)};

        Arrays.sort(point2Ds, Comparator.comparingDouble(Point2D::getX).thenComparing(Point2D::getY));
        System.out.println(Arrays.toString(point2Ds));

        // 사각형의 크기는 좌표가 아닌 width, height 에 의해 결정된다고 보고
        // width, height의 곱을 비교대상으로 삼는다. 이게 동일할 경우에는
        // minX, minY 순으로 비교한다.
        Rectangle2D[] rectangle2Ds = {
            new Rectangle2D(11, 43, 50, 80), // 3
            new Rectangle2D(1000, 345, 30, 50), // 1
            new Rectangle2D(4, 3, 987, 345), // 5
            new Rectangle2D(11, 43, 71, 85), // 4
            new Rectangle2D(11, 20, 50, 80),  // 2
        };

        Arrays.sort(rectangle2Ds, Comparator.<Rectangle2D>comparingDouble(r -> r.getHeight() * r.getWidth())
            .thenComparing(Rectangle2D::getMinX)
            .thenComparing(Rectangle2D::getMinY)
        );
        System.out.println(Arrays.toString(rectangle2Ds));
    }
}
