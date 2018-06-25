package ch08;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;

/**
 * @see <a href="https://iextrading.com/developer/docs/#price">IEX trading API</a>
 */
public class YahooFinance {
    public static BigDecimal getPrice(final String ticker) {
        try {
            final URL url = new URL("https://api.iextrading.com/1.0/stock/" + ticker + "/price");

            final BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            final String data = reader.readLine();
            return new BigDecimal(data);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
