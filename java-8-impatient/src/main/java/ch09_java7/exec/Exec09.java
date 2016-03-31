package ch09_java7.exec;

import java.util.Objects;

public class Exec09 {
    public static class LabeledPoint {
        private String label;
        private int x;
        private int y;

        public LabeledPoint(String label, int x, int y) {
            this.label = label;
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null) {
                return false;
            }

            if (this == o) {
                return true;
            }

            if (this.getClass() != o.getClass()) {
                return false;
            }

            LabeledPoint other = (LabeledPoint) o;
            return Objects.equals(label, other.label) && Objects.equals(x, other.x) && Objects.equals(y, other.y);
        }

        @Override
        public int hashCode() {
            return Objects.hash(label, x, y);
        }
    }
}
