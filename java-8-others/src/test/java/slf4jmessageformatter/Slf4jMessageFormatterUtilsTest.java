package slf4jmessageformatter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Slf4jMessageFormatterUtilsTest {
    @Test
    public void sf() throws Exception {
        assertEquals(Slf4jMessageFormatterUtils.sf("hi {}!", "there"), "hi there!");
        assertEquals(Slf4jMessageFormatterUtils.sf("Set {1,2,3} is not equal to {}.", "1,2"), "Set {1,2,3} is not equal to 1,2.");
        assertEquals(Slf4jMessageFormatterUtils.sf("Set \\{} is not equal to {}.", "1,2"), "Set {} is not equal to 1,2.");
        assertEquals(Slf4jMessageFormatterUtils.sf("File name is C:\\\\{}.", "file.zip"), "File name is C:\\file.zip.");
        assertEquals(Slf4jMessageFormatterUtils.sf("multiple params : {}, {}, {}, {}", 1, "이", "three", "四"), "multiple params : 1, 이, three, 四");
    }
}
