package javacoding.guidelines.ch03;

import java.util.Arrays;

public class Mutable {
    private int[] array = new int[10];

    /**
     * 배열 값이 외부 노출되면 배열 그 자체를 수정할 수 있다.
     */
    public int[] getArray() {
        return array;
    }

    public void setArray(int[] i) {
        array = i;
    }

    public static void main(String[] args) {
        Mutable mutable = new Mutable();
        int[] external = new int[]{1, 2, 3};

        mutable.setArray(external);

        System.out.println("초기값: " + Arrays.toString(mutable.getArray()));
        external[2] = 9;

        System.out.println("외부에서 변경해버림 : " + Arrays.toString(mutable.getArray()));

        mutable.getArray()[2] = 100;
        System.out.println("getArray()로 노출된 상태로 변경해버림 : " + Arrays.toString(mutable.getArray()));
    }
}
