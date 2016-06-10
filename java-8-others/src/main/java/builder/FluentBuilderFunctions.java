package builder;

import java.util.Properties;
import java.util.function.Function;

/**
 * <a href="https://dzone.com/articles/builder-java">Builder in Java</a>
 */
public class FluentBuilderFunctions {
    private Function<Product, Product> fn;

    public FluentBuilderFunctions begin() {
        fn = (ignored) -> new Product();
        return this;
    }

    public FluentBuilderFunctions engine() {
        fn = fn.andThen((product -> {
            product.parts.add("Engine");
            return product;
        }));
        return this;
    }

    public FluentBuilderFunctions steeringWheel() {
        fn = fn.andThen((product) -> {
            product.parts.add("Steering Wheel");
            return product;
        });
        return this;
    }

    public FluentBuilderFunctions tire(int numberOfTires) {
        fn = fn.andThen((product) -> {
            for (int i = 0; i < numberOfTires; i++) {
                product.parts.add("Tire");
            }
            return product;
        });
        return this;
    }
    public Product end() {
        // fn lambda 코드들을 모두 연달아 실행
        return fn.apply(null);
    }

    public static void main(String[] args) {
        final Product product = new FluentBuilderFunctions()
            .begin()
            .engine()
            .steeringWheel()
            .tire(4)
            .end();

        System.out.println(product);
    }
}
