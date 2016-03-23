package ch08_othertopics.exec;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * named capturing group 으로 주소 파싱.
 * 우편번호는 5,6 자리라고 치자.
 */
public class Exec16 {
    public static void main(String[] args) {
        String seoulArtCenterAddress = "서울시 서초구 남부순환로 2406 우) 65757";
        String seoulArtCenterOldAddress = "대전광역시 유성구 대덕대로 480 우) 123-456";


        parseAddress(seoulArtCenterAddress);
        parseAddress(seoulArtCenterOldAddress);

    }

    private static void parseAddress(String address) {
        final Pattern cityPattern = Pattern.compile("(?<city>\\D+시)\\s+(?<gu>\\D+구).*우\\) (?<postcode>\\d{5}|\\d{3}-\\d{3})");

        System.out.println("= parsing " + address);
        final Matcher matcher = cityPattern.matcher(address);
        if (matcher.find()) {
            final String city = matcher.group("city");
            System.out.println("시 : " + city);
            final String gu = matcher.group("gu");
            System.out.println("구 : " + gu);
            final String postcode = matcher.group("postcode");
            System.out.println("우편번호 : " + postcode);
        }
    }
}
