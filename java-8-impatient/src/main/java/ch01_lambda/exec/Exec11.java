package ch01_lambda.exec;

public class Exec11 {
    interface I {
        static void f() {
            System.out.println("Hello I");
        }
    }

    interface J {
        static void f() {
            System.out.println("Hello J");
        }
    }
    static class Iimpl implements I {

    }
    static class IJ implements I, J {
    }

    public static void main(String[] args) {
        I.f(); J.f();
        IJ ij = new IJ();
        // 인터페이스의 정적 메소드는 그 구현 클래스를 통해 호출할 수 없다.
    }
}
