package ch14.sec01;

import java.util.function.DoubleUnaryOperator;

/**
 *
 */
public class CurryingExamples {
    public static DoubleUnaryOperator curriedConverter(double f, double b) {
        return (double x) -> x * f + b;
    }

    public static void main(String[] args) {
        final DoubleUnaryOperator convertCtoF = curriedConverter(9.0 / 5, 32);
        final DoubleUnaryOperator convertUSDtoGBP = curriedConverter(0.6, 0);
        final DoubleUnaryOperator convertKmtoMi = curriedConverter(0.6214, 0);

        double gbp = convertUSDtoGBP.applyAsDouble(1000);
        System.out.println("$1000 -> GBP " + gbp);
    }
}
