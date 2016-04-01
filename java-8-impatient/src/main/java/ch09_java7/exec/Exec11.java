package ch09_java7.exec;

import java.io.File;
import java.io.IOException;

/**
 * 신용카드 regex : http://howtodoinjava.com/regex/java-regex-validate-credit-card-numbers/
 */
public class Exec11 {
    public static void main(String[] args) throws IOException, InterruptedException {
        final String creditCardRegex = "(?:4[0-9]{12}(?:[0-9]{3})?|5[1-5][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})";
        final String startDir = System.getProperty("user.home") + File.separator + "tmp";
        System.out.println("startDir : " + startDir);
        ProcessBuilder pb = new ProcessBuilder(
            "grep",
            "-r",
            "-E",
            creditCardRegex, startDir)
            .redirectOutput(ProcessBuilder.Redirect.INHERIT);
        final Process process = pb.start();
        process.waitFor();
    }
}
