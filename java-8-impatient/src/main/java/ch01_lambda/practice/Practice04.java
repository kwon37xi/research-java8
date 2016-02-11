package ch01_lambda.practice;

import java.io.File;
import java.util.Arrays;

public class Practice04 {
    public static void main(String[] args) {
        File dir = new File("/home/kwon37xi/Downloads");
        final File[] files = dir.listFiles();

        Arrays.sort(files, (file1, file2) -> {
            if (file1.isDirectory() && file2.isFile()) {
                return -1;
            } else if (file1.isFile() && file2.isDirectory()) {
                return 1;
            }
            return file1.getName().compareTo(file2.getName());
        });

        System.out.println(Arrays.toString(files));
    }
}
