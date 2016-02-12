package ch02_stream;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Stream 생성
 */
public class Lecture02 {
    public static void main(String[] args) throws IOException {
        final Path alicePath = Paths.get("java-8-impatient/alice.txt");
        String contents = new String(Files.readAllBytes(alicePath), StandardCharsets.UTF_8);
        Stream<String> words = Stream.of(contents);

//        words.forEach(w -> System.out.println(w));

        Stream<String> song = Stream.of("gently", "down", "the", "stream");
//        song.forEach(w -> System.out.println(w));

        Stream<String> silence = Stream.empty();

        Stream<String> echos = Stream.generate(() -> "Echo");
//        echos.forEach(echo -> System.out.println(echo)); // infinite

        Stream<Double> randoms = Stream.generate(Math::random);

        // 순열
        Stream<BigInteger> integers = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.TEN));
        integers.limit(15).forEach(i -> System.out.println(i));

        Stream<String> patternWords = Pattern.compile("[\\P{L}]+").splitAsStream(contents);
        patternWords.filter(w -> w.length() > 14).forEach(w -> System.out.println(w));

        // Stream is AutoCloseable
        try (Stream<String> lines = Files.lines(alicePath)) {
            System.out.println("lines over 20 chars : " + lines.filter(line -> line.length() > 20).count());
        }
    }
}
