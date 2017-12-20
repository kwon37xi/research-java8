package javacoding.guidelines.ch06;

import javax.xml.bind.ValidationException;
import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 * HTML 입력을 적절하게 validation하고, 출력을 escape 한다.
 * <p>
 * 단, 나는 애초에 모델에 값을 넣을 때 escape 하는 방식에 반대한다.
 * entity encode 처리는 view framework에게 일임해야하고 최근 view framework들은 모두 escape/entity encode 방법을 지원한다.
 */
public class ValidateOutput {
    // Allows only alphanumeric characters and spaces
    private static final Pattern pattern = Pattern.compile("^[a-zA-Z0-9\\s]{0,20}$");

    public String validate(String name, String input) throws ValidationException {
        String canonical = normalize(input);

        if (!pattern.matcher(canonical).matches()) {
            throw new ValidationException("Improper format in " + name + " field");
        }

        // perform output encoding for nonvalid characters : 이것은 view 에 맡겨야 할 일이다.
        canonical = HTMLEntityEncode(canonical);
        return canonical;
    }

    // package private for test

    /**
     * @see <a href="http://d2.naver.com/helloworld/76650">유니코드 정규화</a>
     */
    String normalize(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFKC);
    }

    String HTMLEntityEncode(String input) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (Character.isLetterOrDigit(ch) || Character.isWhitespace(ch)) {
                sb.append(ch);
            } else {
                sb.append("&#" + (int) ch + ";"); // 이곳에 들어올 경우는 극히 드물다.
            }
        }
        return sb.toString();
    }
}
