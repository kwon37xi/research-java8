package ch08_othertopics.exec;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;

/**
 * Files.lines와 Pattern.asPredicate 로 정규 표현식과 일치하는 내용을 담은 모든 행 출력
 */
public class Exec15 {
    public static void main(String[] args) throws IOException {
//        final String filepath = args[0];
//        final String regex = args[1];

        final String filepath = "java-8-impatient/anne.txt";
        final String regex = "\\bb[a-zA-Z]+h\\b"; // b 로 시작해서 h 로 끝나는 단어들

        grep(filepath, regex);

    }

    private static void grep(String filepath, String regex) throws IOException {
        Files.lines(Paths.get(filepath))
            .filter(Pattern.compile(regex).asPredicate())
            .forEach(System.out::println);
    }
}
