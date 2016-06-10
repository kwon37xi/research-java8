package builder;

import java.util.ArrayList;
import java.util.List;

public class Product {
    public List<String> parts = new ArrayList<>();

    @Override
    public String toString() {
        return "Product{" +
            "parts=" + parts +
            '}';
    }
}
