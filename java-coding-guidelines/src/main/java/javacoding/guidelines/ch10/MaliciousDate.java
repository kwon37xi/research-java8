package javacoding.guidelines.ch10;

import java.util.Date;

public class MaliciousDate extends Date {
    private static int count = 0;

    @Override
    public Object clone() {
        System.out.println("여기에 악성 코드를 넣는다.");
        return super.clone();
    }

    @Override
    public long getTime() {
        Date d = new Date();
        return (count++ == 0) ? d.getTime() : d.getTime() - 1000; // 처음에는 현재 시간을 리턴하지만 그 이후 부터는 잘 못된 시간을 반환한다.
    }
}
