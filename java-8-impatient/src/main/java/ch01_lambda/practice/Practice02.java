package ch01_lambda.practice;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

public class Practice02 {
    public static void main(String[] args) {
        String dirPath = "/home/kwon37xi";

        File dir = new File(dirPath);
        final File[] filesByAnonymousClass = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory();
            }
        });

        final File[] filesByLambda = dir.listFiles(File::isDirectory);
        System.out.println(Arrays.toString(filesByLambda));
    }
}
