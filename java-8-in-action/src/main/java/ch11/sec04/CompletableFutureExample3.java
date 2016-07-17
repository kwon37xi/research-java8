package ch11.sec04;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static java.util.stream.Collectors.toList;

public class CompletableFutureExample3 {
    private static final List<Shop04> shops = Arrays.asList(new Shop04("BestPrice"),
        new Shop04("LetsSaveBig"),
        new Shop04("MyFavoriteShop"),
        new Shop04("BuyItAll"),
        new Shop04("ShopEasy")
//        new Shop04("Hello"),
//        new Shop04("Earth"),
//        new Shop04("Venus"),
//        new Shop04("Mars"),
//        new Shop04("Sun")
    );


    private static final Executor executor =
        Executors.newFixedThreadPool(Math.min(shops.size(), 100), r -> {
            Thread t = new Thread(r);
            t.setDaemon(true); // daemon thread로?
            return t;
        });

    public static void main(String[] args) {
        long start = System.nanoTime();
//        System.out.println(findPrices("myPhone27S"));
        System.out.println(findPricesCompletableFuture("myPhone27S"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    public static List<String> findPrices(String product) {
        return shops.stream()
            .map(shop -> shop.getPrice(product))
            .map(Quote::parse)
            .map(Discount::applyDiscount)
            .collect(toList());
    }

    public static List<String> findPricesCompletableFuture(String product) {
        List<CompletableFuture<String>> priceFutures =
            shops.stream()
            .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
            .map(future -> future.thenApply(Quote::parse))
            .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
            .collect(toList());

        return priceFutures.stream()
            .map(CompletableFuture::join)
            .collect(toList());

    }
}
