package ch09_java7.exec;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * 원래 요구사항과 더불어 suppressed 에러까지 추가해봄.
 */
public class Exec01 {
    public static void main(String[] args) throws IOException {
        // try with resources
        Scanner in = null;
        PrintWriter out = null;
        Exception originalException = null;

        try {
            in = new Scanner(Paths.get("java-8-impatient/anne.txt"));
            out = new PrintWriter("/tmp/out.txt");
            if (true) {
                throw new IllegalStateException("some error occurs.");
            }
            while (in.hasNext()) {
                out.println(in.next().toLowerCase());
            }
        } catch (Exception ex) {
            originalException = ex;
            throw ex;
        } finally {
            System.out.println("originalex " + originalException);
            if (in != null) {
                try {
                    in.close();
                } catch (Exception ex) {
                    if (originalException != null) {
                        originalException.addSuppressed(ex);
                    }
                }
            }

            if (out != null) {
                try {
                    out.close();
                } catch (Exception ex) {
                    if (originalException != null) {
                        originalException.addSuppressed(ex);
                    }
                }
            }
        }
    }
}
