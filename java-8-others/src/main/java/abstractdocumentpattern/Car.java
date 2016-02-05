package abstractdocumentpattern;

import java.util.Map;

/**
 *
 */
public class Car extends BaseDocument implements HasPrice {
    public Car(Map<String, Object> entries) {
        super(entries);
    }
}
