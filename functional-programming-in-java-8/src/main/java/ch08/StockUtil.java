package ch08;

import java.math.BigDecimal;
import java.util.function.Predicate;

public class StockUtil {
    public static StockInfo getPrice(final String ticker) {
        return new StockInfo(ticker, YahooFinance.getPrice(ticker));
    }

    public static Predicate<StockInfo> isPriceLessThan(final int price) {
        return stockInfo -> stockInfo.price.compareTo(BigDecimal.valueOf(price)) < 0;
    }

    public static StockInfo pickHigh(final StockInfo stock1, final StockInfo stock2) {
        return stock1.price.compareTo(stock2.price) > 0 ? stock1 : stock2;
    }
}
