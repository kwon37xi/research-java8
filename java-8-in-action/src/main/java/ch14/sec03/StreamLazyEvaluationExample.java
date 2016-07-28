package ch14.sec03;

/**
 * 제대로 작동하는 primes.
 */
public class StreamLazyEvaluationExample {
    public static MyList<Integer> primes(MyList<Integer> numbers) {
        return new LazyList<>(numbers.head(),
            () -> primes(numbers.tail().filter(n -> n % numbers.head() != 0)));
    }

    public static <T> void printAll(MyList<T> list) {
        if (list.isEmpty()) return;

        System.out.println(list.head() + ", ");
        printAll(list.tail()); // 어느정도 되다가 stackoverflow 발생한다. 그냥 loop 로 구현해야함.
    }

    public static void main(String[] args) {
        LazyList<Integer> numbers = LazyList.from(2);
        int two = numbers.head();
        int three = numbers.tail().head();
        int four = numbers.tail().tail().head();

        System.out.println(two + " " + three + " " + four);

        numbers = LazyList.from(2);
        two = primes(numbers).head();
        three = primes(numbers).tail().head();
        int five = primes(numbers).tail().tail().head();

        System.out.println("primes : " + two + " " + three + " " + five);

        printAll(primes(numbers));
    }
}
