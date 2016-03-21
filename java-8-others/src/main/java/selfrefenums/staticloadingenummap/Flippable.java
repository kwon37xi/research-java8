package selfrefenums.staticloadingenummap;

import java.util.EnumMap;
import java.util.Map;

/**
 * {@link java.util.EnumMap}을 통한 구현
 */
public enum Flippable {
    A, B, Y, Z;

    private static final Map<Flippable, Flippable> opposites;

    static {
        opposites = new EnumMap<>(Flippable.class);
        opposites.put(A, Z);
        opposites.put(B, Y);
        opposites.put(Y, B);
        opposites.put(Z, A);

        // 모두 올바르게 값 설정이 되었는지 검증
        for (Flippable flippable : Flippable.values()) {
            if (flippable.flip().flip() != flippable) {
                throw new IllegalStateException("Flippable " + flippable + " inconsistent.");
            }
        }
    }

    public Flippable flip() {
        return opposites.get(this);
    }

    public static void main(String[] args) {
        System.out.println(Z.flip());
    }
}
