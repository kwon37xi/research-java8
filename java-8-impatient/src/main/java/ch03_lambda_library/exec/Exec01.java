package ch03_lambda_library.exec;

import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Exec01 {
    public static void main(String[] args) {
        logIf(Level.FINE, () -> createMessage("debug"));
        logIf(Level.INFO, () -> createMessage("info"));
        logIf(Level.WARNING, () -> createMessage("warning"));
        logIf(Level.SEVERE, () -> createMessage("sever"));
    }

    private static String createMessage(String message) {
        System.out.println("createMessage for '" + message + "' called.");
        return message;
    }

    public static void logIf(Level level, Supplier<String> logMessageSupplier) {
        Logger logger = Logger.getGlobal();
        if (logger.isLoggable(level)) {
            final String logMessage = logMessageSupplier.get();
            logger.log(level, logMessage);
        }
    }
}
