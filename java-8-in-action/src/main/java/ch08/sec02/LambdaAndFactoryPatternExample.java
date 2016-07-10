package ch08.sec02;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class LambdaAndFactoryPatternExample {
    final static Map<String, Supplier<Product>> map = new HashMap<>();

    static {
        map.put("loan", Product.Loan::new);
        map.put("stock", Product.Stock::new);
        map.put("bond", Product.Bond::new);
    }

    public static Product createProduct(String name) {
        Supplier<Product> p = map.get(name);
        if (p != null) return p.get();
        throw new IllegalArgumentException("No such product " + name);
    }

    public static void main(String[] args) {
        final Product stock = createProduct("stock");
        System.out.println("Product : " + stock.getName());
    }
}
