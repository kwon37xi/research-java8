package equality.no_equivalance_relation;

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

    @Override
    public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof WrongPoint) {
            WrongPoint that = (WrongPoint) other;
            result = (this.getX() == that.getX() && this.getY() == that.getY());
        }
        return result;
    }

    @Override
    public int hashCode() {
        return (41 * (41 + getX()) + getY());
    }
}
