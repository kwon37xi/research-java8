package selfrefenums.staticloading;

/**
 * static loading 기반 구현
 */
public enum Flippable {
    A, B, Z, Y;

    static {
        A.opposite = Z;
        B.opposite = Y;
        Y.opposite = B;
        Z.opposite = A;

        for (Flippable flippable : Flippable.values()) {
            if (flippable.flip().flip() != flippable) {
                throw new IllegalStateException("Flippable " + flippable + " inconsistent.");
            }
        }
    }

    public Flippable flip() {
        return opposite;
    }

    private Flippable opposite;

    public static void main(String[] args) {
        System.out.println(Y.flip());
    }
}
