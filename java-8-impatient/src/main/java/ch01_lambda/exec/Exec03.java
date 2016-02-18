package ch01_lambda.exec;

import java.io.File;
import java.util.Arrays;

public class Exec03 {
    public static void main(String[] args) {
        final String extension = "tmp"; // captured by lambda
        File targetDir = new File("/tmp");

        final String[] fileNames = targetDir.list((dir, name) -> name.endsWith("." + extension));
        System.out.println(Arrays.toString(fileNames));
    }
}
