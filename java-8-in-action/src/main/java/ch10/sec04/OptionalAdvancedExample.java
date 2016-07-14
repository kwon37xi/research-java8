package ch10.sec04;

import java.util.Optional;
import java.util.Properties;

public class OptionalAdvancedExample {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("a", "5");
        props.setProperty("b", "true");
        props.setProperty("c", "-3");


    }

    public static int readDurationOld(Properties props, String name) {
        String value = props.getProperty(name);
        if (value != null) {
            try {
                int i = Integer.parseInt(value);
                if (i > 0) {
                    return i;
                }
            } catch (NumberFormatException nfe) {
                // no op
            }
        }
        return 0;
    }

    public static int readDuration(Properties props, String name) {
        return Optional.ofNullable(props.getProperty(name))
            .flatMap(OptionalAdvancedExample::stringToInt)
            .filter((Integer i) -> i > 0)
            .orElse(0);
    }

    public static Optional<Integer> stringToInt(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException nfe) {
            return Optional.empty();
        }
    }
}
