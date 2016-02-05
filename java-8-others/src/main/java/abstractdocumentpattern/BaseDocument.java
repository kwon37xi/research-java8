package abstractdocumentpattern;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 *
 */
public class BaseDocument implements Document {
    private final Map<String, Object> entries;

    public BaseDocument(Map<String, Object> entries) {
        this.entries = Objects.requireNonNull(entries);
    }

    @Override
    public Object put(String key, Object value) {
        return entries.put(key, value);
    }

    @Override
    public Object get(String key) {
        return entries.get(key);
    }

    @Override
    public <T> Stream<T> children(String key, Function<Map<String, Object>, T> constructor) {
        final List<Map<String, Object>> children = (List<Map<String, Object>>) get(key);
        return children == null ? Stream.empty() : children.stream().map(constructor);
    }
}
