package javacoding.guidelines.ch10;

import java.util.Date;

/**
 * 비신뢰 메서드의 매개변수를 clone() 메서드로 복제하지 말라.
 */
public class CallCloneOnNotSecuredArgument {

    private boolean validateValue(long time) {
        // time 값이 진짜 현재 값인지 검증한다던지...
        return true;
    }

    private void storeDateInDb(Date date) {
        Date copy = (Date) date.clone();

        long firstTime = copy.getTime();
        if (validateValue(firstTime)) {
            // DB에 값을 저장하던지...
            System.out.println("first time : " + firstTime);
            System.out.println("second time : " + copy.getTime()); // time 값이 과거로 변경됨.
            System.out.println("third time : " + copy.getTime()); // time 값이 과거로 변경됨.
            System.out.println("DB에 date 저장");
        }
    }

    public static void main(String[] args) {
        CallCloneOnNotSecuredArgument callCloneOnNotSecuredArgument = new CallCloneOnNotSecuredArgument();
        callCloneOnNotSecuredArgument.storeDateInDb(new MaliciousDate());
    }
}
