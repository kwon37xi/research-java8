package ch11.sec05;

import ch11.sec04.Discount;

import java.util.Random;

/**
 * sec04 용 Shop
 */
public class Shop05 {
    private String name;

    public Shop05(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPrice(String product) {
        double price = calculatePrice(product);
        Random random = new Random();
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        final String priceResult = String.format("%s:%.2f:%s", name, price, code);
        return priceResult;
    }

    private double calculatePrice(String product) {
        randomDelay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public static void randomDelay() {
        Random random = new Random();
        int delay = 500 + random.nextInt(2000);

        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
