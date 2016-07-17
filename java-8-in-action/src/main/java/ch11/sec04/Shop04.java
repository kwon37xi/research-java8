package ch11.sec04;

import java.util.Random;

/**
 * sec04 용 Shop
 */
public class Shop04 {
    private String name;

    public Shop04(String name) {
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
        delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
