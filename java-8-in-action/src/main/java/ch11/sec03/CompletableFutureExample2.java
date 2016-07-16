package ch11.sec03;

import ch11.sec02.Shop;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import static java.util.stream.Collectors.toList;

/**
 *
 */
public class CompletableFutureExample2 {
    private static final List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
        new Shop("LetsSaveBig"),
        new Shop("MyFavoriteShop"),
        new Shop("BuyItAll"),
        new Shop("ShopEasy"),
        new Shop("Hello"),
        new Shop("Earth"),
        new Shop("Venus"),
        new Shop("Mars"),
        new Shop("Sun")
        );

    private static final Executor executor =
        Executors.newFixedThreadPool(Math.min(shops.size(), 100), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true); // daemon thread로?
                return t;
            }
        });

    public static void main(String[] args) {


        long start = System.nanoTime();
//        System.out.println(findPrices("myPhone27S"));
        System.out.println(findPricesWithCompletableFuture("myPhone27S"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");

        // 일반 스트림 4초 약간 이상
        // 병렬 스트림 1초 약각 이상
    }

    public static List<String> findPrices(String product) {
        return shops.parallelStream()
            .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
            .collect(toList());
    }

    public static List<String> findPricesWithCompletableFuture(String product) {
        List<CompletableFuture<String>> priceFutures =
            shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                    () -> shop.getName() + " price is " + shop.getPrice(product),
                    executor) // executor 직접 지정
                )
                .collect(toList());

        return priceFutures.stream()
            .map(CompletableFuture::join)
            .collect(toList());

    }
}
