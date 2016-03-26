package ch08_othertopics.exec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Basic Auth로 보호된 사이트의 컨텐츠를 읽는 프로그램 작성하기.
 *
 * <a href="http://httpbin.org/">httpbin.org</a>에서 basic auth 테스트 URL을 사용하여 그 결과를 JSON으로 받을 수 있다.
 */
public class Exec11 {
    public static void main(String[] args) throws IOException {
        String username = "kwon37xi";
        String password = "thisismypassword123";
        final Base64.Encoder encoder = Base64.getEncoder();

        String encodedBasicAuth = encoder.encodeToString((username + ":" + password).getBytes(StandardCharsets.UTF_8));

        URL url = new URL(String.format("http://httpbin.org/basic-auth/%s/%s", username, password));
        final URLConnection connection = url.openConnection();

        connection.setRequestProperty("Authorization", "Basic " + encodedBasicAuth);

        connection.connect();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            reader.lines().forEach(System.out::println);
        }
    }
}
