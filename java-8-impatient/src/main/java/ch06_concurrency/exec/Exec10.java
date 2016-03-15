package ch06_concurrency.exec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 사용자에게 URL을 요구하고, 해당 URL의 웹페이지의 모든 링크를 표시한다.
 * CompletableFuture를 사용하고 get()은 호출하면 안된다.
 */
public class Exec10 {
    public static void main(String[] args) throws Exception {
        String userInputUrl = args[0];
        System.out.println("User Input Url : " + userInputUrl);

        CompletableFuture.supplyAsync(() ->  {
            // URL을 읽어서 문자열로 리턴
            try {
                URL url = new URL(userInputUrl);
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()))) {
                    StringBuilder sb= new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                    return sb.toString();
                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }).thenApply(html -> {
            Pattern pattern = Pattern.compile("href=[\"\'](https?://.+?)[\'\"]"); // non-greedy match
            final Matcher matcher = pattern.matcher(html);
            final List<String> links = new ArrayList<>();
            while (matcher.find()) {
                links.add(matcher.group(1));
            }
            return links;
        }).thenAccept(links -> links.forEach(System.out::println));

        // 프로그램이 너무 일찍 종료하지 않도록 기본 호출. CompletableFuture 호출 이후에 해야한다.
        ForkJoinPool.commonPool().awaitQuiescence(10, TimeUnit.SECONDS);
    }
}
