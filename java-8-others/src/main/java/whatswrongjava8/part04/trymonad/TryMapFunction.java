package whatswrongjava8.part04.trymonad;

public interface TryMapFunction<T, R> {
    R apply(T t) throws Throwable;
}