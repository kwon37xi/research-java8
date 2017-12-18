package javacoding.guidelines.ch03;

import java.util.Arrays;

public class MutableProtector extends Mutable {
    public MutableProtector(int[] array) {
        super.setArray(array.clone()); // clone 을 해야 외부에서 변경 불가함
    }

    @Override
    public int[] getArray() {
        return super.getArray().clone();
    }

    @Override
    public void setArray(int[] i) {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        int[] external = new int[]{1, 2, 3};
        Mutable mutable = new MutableProtector(external);


        System.out.println("초기값: " + Arrays.toString(mutable.getArray()));

        try {
            mutable.setArray(external);
        } catch (Exception e) {
            System.out.println("setArray 호출시 오류 발생 - " + e.getClass());
        }

        external[2] = 9;

        System.out.println("외부에서 변경 불가: " + Arrays.toString(mutable.getArray()));

        mutable.getArray()[2] = 100;
        System.out.println("getArray()로 노출된 상태로 변경 불가 : " + Arrays.toString(mutable.getArray()));
    }
}

