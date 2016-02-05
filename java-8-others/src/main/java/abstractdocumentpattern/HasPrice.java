package abstractdocumentpattern;

import java.util.OptionalInt;

/**
 *
 */
public interface HasPrice extends Document {
    String PRICE = "price";

    default OptionalInt getPrice() {
        final Number num = (Number) get(PRICE);
        return num == null
                ? OptionalInt.empty()
                : OptionalInt.of(num.intValue());
    }

    default void setPrice(int price) {
        put(PRICE, price);
    }
}
