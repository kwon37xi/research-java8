package slf4jmessageformatter;

import org.slf4j.helpers.MessageFormatter;

/**
 * Slf4j의 {@link org.slf4j.helpers.MessageFormatter}를 쉽게 호출하게 해주는 Utility class
 */
public class Slf4jMessageFormatterUtils {

    /**
     * Slf4j의 문자열 포맷 형태로 <code>sf("hi {}, hello {}", "there", "world");</code> 처럼 호출하여 메시지를 포매팅한다.<br>
     * <em>주의: "{}"와 실제 인자 갯수가 달라도 오류 없이 지나간다.</em>
     * @see MessageFormatter
     */
    public static String sf(String messagePattern, Object... args) {
        return MessageFormatter.arrayFormat(messagePattern, args).getMessage();
    }
}
