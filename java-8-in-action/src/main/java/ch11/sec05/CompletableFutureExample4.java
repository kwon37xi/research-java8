package ch11.sec05;

import ch11.sec04.Discount;
import ch11.sec04.Quote;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class CompletableFutureExample4 {
    private static final List<Shop05> shops = Arrays.asList(new Shop05("BestPrice"),
        new Shop05("LetsSaveBig"),
        new Shop05("MyFavoriteShop"),
        new Shop05("BuyItAll"),
        new Shop05("ShopEasy")
//        new Shop05("Hello"),
//        new Shop05("Earth"),
//        new Shop05("Venus"),
//        new Shop05("Mars"),
//        new Shop05("Sun")
    );

    private static final Executor executor =
        Executors.newFixedThreadPool(Math.min(shops.size(), 100), r -> {
            Thread t = new Thread(r);
            t.setDaemon(true); // daemon thread로?
            return t;
        });

    public static void main(String[] args) {
        long start = System.nanoTime();
        final CompletableFuture[] futures = (CompletableFuture[]) findPricesStream("myPhone27S")
            .map(f -> f.thenAccept(
                s -> System.out.println(s + " (done in " + ((System.nanoTime() - start) / 1_000_000) + " msecs)")
                )
            ).toArray(size -> new CompletableFuture[size]);

        CompletableFuture.allOf(futures).join();

        System.out.println("All shops have now responsed in " + ((System.nanoTime() - start) / 1_000_000) + " msecs");
    }

    public static Stream<CompletableFuture<String>> findPricesStream(String product) {
        return shops.stream()
            .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
            .map(future -> future.thenApply(Quote::parse))
            .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(
                () -> Discount.applyDiscount(quote), executor
            )));
    }
}
