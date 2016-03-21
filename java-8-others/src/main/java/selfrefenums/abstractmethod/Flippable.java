package selfrefenums.abstractmethod;

/**
 * abstract method 구현 방식으로 처리
 */
public enum Flippable {
    A {
        @Override
        public Flippable flip() {
            return Z;
        }
    }, B {
        @Override
        public Flippable flip() {
            return Y;
        }
    }, Y {
        @Override
        public Flippable flip() {
            return B;
        }
    }, Z {
        @Override
        public Flippable flip() {
            return A;
        }
    };

    public abstract Flippable flip();

    public static void main(String[] args) {
        System.out.println(A.flip());
    }
}
