package ch08;

import java.time.Instant;
import java.util.stream.Stream;

public class PickStockFunctional {
    public static void findHighPriced(final Stream<String> symbols) {
        final StockInfo highPriced =
            symbols.map(StockUtil::getPrice)
                .filter(StockUtil.isPriceLessThan(2000))
                .reduce(StockUtil::pickHigh)
                .get();

        System.out.println("High Priced under $500 is " + highPriced);
    }

    public static void main(String[] args) {
        long start = Instant.now().toEpochMilli();
        findHighPriced(Tickers.SYMBOLS.stream());
        System.out.printf("stream elapsed %d millis%n", (Instant.now().toEpochMilli() - start));

        long parallelStart = Instant.now().toEpochMilli();
        findHighPriced(Tickers.SYMBOLS.parallelStream());
        System.out.printf("parallelStream elapsed %d millis%n", (Instant.now().toEpochMilli() - parallelStart));
    }
}
