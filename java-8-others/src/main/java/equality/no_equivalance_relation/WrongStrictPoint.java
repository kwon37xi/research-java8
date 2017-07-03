package equality.no_equivalance_relation;

/**
 * equals 비교시 동일 클래스가 아니면 무조건 false리턴하게 변경.
 * 상속된 클래스는 이행성과 대칭성을 지키게 되지만 너무 strict해서 한가지 문제가 발생한다.
 */
public class WrongStrictPoint {
    private final int x;
    private final int y;

    public WrongStrictPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof WrongStrictPoint) {
            WrongStrictPoint that = (WrongStrictPoint) other;
            result = (this.getX() == that.getX() && this.getY() == that.getY()
                && this.getClass().equals(that.getClass())); // class 검사 강화. 동일 클래스 아니면 false
        }
        return result;
    }

    @Override
    public int hashCode() {
        return (41 * (41 + getX()) + getY());
    }

    public static void main(String[] args) {
        WrongStrictPoint p = new WrongStrictPoint(1, 2);
        WrongStrictPoint pAnon = new WrongStrictPoint(1, 1) {
            @Override public int getY() {
                return 2;
            }
        };

        System.out.println(p.equals(pAnon)); // false : true여야 더 적합함.
    }
}
