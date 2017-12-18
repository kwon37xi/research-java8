package javacoding.guidelines.ch03;

import java.util.Arrays;

public class MutableProtectorNoncompliant extends Mutable {
    @Override
    public int[] getArray() {
        return super.getArray().clone();
    }

    public static void main(String[] args) {
        Mutable mutable = new MutableProtectorNoncompliant();

        int[] external = new int[]{1, 2, 3};

        mutable.setArray(external);

        System.out.println("초기값: " + Arrays.toString(mutable.getArray()));
        external[2] = 9;

        System.out.println("외부에서 변경해버림 : " + Arrays.toString(mutable.getArray()));

        mutable.setArray(new int[]{100, 200, 300});
        System.out.println("외부에서 다시 setArray() 로 설정: " + Arrays.toString(mutable.getArray()));

        mutable.getArray()[2] = 100;
        System.out.println("getArray()로 노출된 상태로 변경 불가 : " + Arrays.toString(mutable.getArray()));
    }
}
