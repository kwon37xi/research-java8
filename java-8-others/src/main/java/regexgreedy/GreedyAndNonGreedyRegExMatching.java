package regexgreedy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <a href="https://examples.javacodegeeks.com/core-java/util/regex/greedy-and-non-greedy-reg-ex-matching/">Greedy and non-greedy Reg Ex matching</a>
 * - greedy : 하나 혹은 여러개를 나타내는 수량자 기호(*, +, ..)만 존재하고, 매칭될 수 있는 최대 폭까지 매칭시킨다.
 * - non-greedy : 하나 혹은 여러개를 나타내는 수랑자 기호 뒤에 "?"가 오면 non-greedy 매칭되는 최초의 그룹을 바로 리턴하라는 뜻.
 *
 */
public class GreedyAndNonGreedyRegExMatching {
    public static void main(String[] args) {
        Pattern pattern;
        Matcher matcher;

        // Greedy 수량자
        pattern = Pattern.compile("A.*c"); // A로 시작 c로 종료 greedy
        matcher = pattern.matcher("AbcAbc");

        matcher.find();
        // greedy 에서는 최초 매칭 Abc가 있으나 더욱 욕심(greedy)을 내어 AbcAbc를 매칭시킴.
        System.out.println("'AbcAbc' matches 'A.*c' --> " + matcher.group());

        pattern = Pattern.compile("A.+"); // Greedy
        matcher = pattern.matcher("AbcAbc");
        matcher.find();
        System.out.println("'AbcAbc' matches 'A.+' --> " + matcher.group()); // AbcAbc

        // === 여기부터 non-greedy //
        pattern = Pattern.compile("A.*?c"); // non-greedy. ? 표 붙임.
        matcher = pattern.matcher("AbcAbc");
        matcher.find();
        // 매칭 되는 최소 단위만 찾아서 바로 반환
        System.out.println("'AbcAbc' matches 'A.*?c' --> " + matcher.group()); // Abc

        pattern = Pattern.compile("A.+?");
        matcher = pattern.matcher("AbcAbc");
        matcher.find();
        // +의 의미인 하나이상 매칭에 부합하는 최초의 매치 Ab 반환
        System.out.println("'AbcAbc' matches 'A.+?' --> " + matcher.group()); // Ab
    }
}
