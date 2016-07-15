package ch11.sec02;

import java.util.concurrent.Future;

/**
 *
 */
public class CompletableFutureExample {
    public static void main(String[] args) {
        Shop shop = new Shop("BestShop");

        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("my favourite product");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime + " msecs");

        doSomethingElse();

        try {
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }

    private static void doSomethingElse() {
        System.out.println("do something else : hahahah");
    }
}
