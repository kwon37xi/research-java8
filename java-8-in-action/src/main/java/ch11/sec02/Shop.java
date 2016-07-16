package ch11.sec02;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 */
public class Shop {
    private String name;

    public Shop(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Future<Double> getPriceAsync(String product) {
//        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
//
//        new Thread(() -> {
//            try {
//                double price = calculatePrice(product);
//                futurePrice.complete(price);
//            } catch (Exception ex) {
//                futurePrice.completeExceptionally(ex);
//            }
//        }).start();
//        return futurePrice;
        // 위 모든 코드를 한줄로 아래와 같이 줄일 수 있다.
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }
    public double getPrice(String product) {
        return calculatePrice(product);
    }

    private double calculatePrice(String product) {
        delay();

        Random random = ThreadLocalRandom.current();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
