package ch06_concurrency.exec;

import java.util.Arrays;

/**
 * 행렬의 곱을 이용하여 피보나치 수열을 계산할 수 있다.
 * <a href="http://www.mathteacher.pe.kr/mt_11/mt_11_04.htm">행렬의 곱</a>
 */
public class Exec09 {
    public static void main(String[] args) {
        Matrix2by2[] matrixes = new Matrix2by2[10];
        Arrays.parallelSetAll(matrixes, idx -> {
            return new Matrix2by2();
        });
        Arrays.parallelPrefix(matrixes, (x, y) -> x.multiply(y));

        for (Matrix2by2 matrix : matrixes) {
            System.out.printf("%s ", matrix);
        }
        System.out.println();

    }
    public static class Matrix2by2 {
        long [][] values;

        public Matrix2by2() {
            values = new long[][] {{1 ,1}, {1, 0}};
        }
        public Matrix2by2(long[][] values) {
            this.values = values;
        }

        public Matrix2by2 multiply(Matrix2by2 other) {
            return new Matrix2by2(new long[][] {
                {(values[0][0] * other.values[0][0]) + (values[0][1] * other.values[1][0]), (values[0][0] * other.values[0][1]) + (values[0][1] * other.values[1][1])},
                {(values[1][0] * other.values[0][0]) + (values[1][1] * other.values[1][0]), (values[1][0] * other.values[0][1]) + (values[1][1] * other.values[1][1])}
            });
        }

        @Override
        public String toString() {
            // 행렬의 좌상단 값이 피보니치 수열 값이된다.
            return String.valueOf(values[0][0]);
        }
    }
}
