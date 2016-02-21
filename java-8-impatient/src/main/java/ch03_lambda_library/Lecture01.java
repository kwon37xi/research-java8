package ch03_lambda_library;

import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Lecture01 {
    public static void main(String[] args) {
        Logger log = Logger.getLogger(Lecture01.class.getName());

        info(log, () -> "with checking loggable");
        log.info(() -> "with Logger.info(Supplier<String>)");
    }

    public static void info(Logger logger, Supplier<String> message) {
        if (logger.isLoggable(Level.INFO)) {
            logger.info(message.get());
        }
    }
}
