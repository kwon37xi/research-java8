package guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.slf4j.Logger;

import java.util.*;

import static org.junit.Assert.*;
import static org.slf4j.LoggerFactory.getLogger;

public class GuavaCacheTest {
    private final Logger log = getLogger(GuavaCacheTest.class);

    private List<Integer> requestedSingleKeys = new ArrayList<>();
    private List<Integer> requestedMultipleKeys = new ArrayList<>();

    private LoadingCache<Integer, Optional<String>> cache = CacheBuilder.newBuilder()
        .maximumSize(10)
        .build(new CacheLoader<Integer, Optional<String>>() {

            @Override
            public Optional<String> load(Integer key) throws Exception {
                log.debug("requested key {}", key);
                requestedSingleKeys.add(key);
                // 의도적으로 10이상에 대해서는 값이 없는 상황 가정.
                if (key >= 10) {
                    return Optional.empty();
                }
                return Optional.of("value " + key);
            }

            @Override
            public Map<Integer, Optional<String>> loadAll(Iterable<? extends Integer> keys) throws Exception {
                log.debug("requested multiple keys {}", keys);
                Map<Integer, Optional<String>> results = new HashMap<>();

                keys.forEach(key -> {
                    requestedMultipleKeys.add(key);

                    // 의도적으로 10이상에 대해서는 값이 없는 상황 가정.
                    if (key < 10) {
                        results.put(key, Optional.of("multiple value " + key));
                    }
                });
                return results;
            }
        });

    @Test
    public void null_value_fail() throws Exception {
        LoadingCache<Integer, String> nullCache = CacheBuilder.newBuilder()
            .maximumSize(10)
            .build(new CacheLoader<Integer, String>() {

                @Override
                public String load(Integer key) throws Exception {
                    return null;
                }
            });

        try {
            nullCache.get(1);
            fail("캐시 값이 null 일 경우 예외가 발생해야만 함.");
        } catch (CacheLoader.InvalidCacheLoadException ex) {
            assertEquals(ex.getMessage(), "CacheLoader returned null for key 1.");
        }
    }

    @Test
    public void get() throws Exception {
        assertEquals("value 1", cache.get(1).get());
        assertEquals(1, requestedSingleKeys.size());
        assertTrue(requestedSingleKeys.contains(1));

        assertEquals("value 2", cache.get(2).get());
        assertEquals(2, requestedSingleKeys.size());
        assertTrue(requestedSingleKeys.contains(2));

        assertEquals("value 9", cache.get(9).get());
        assertEquals(3, requestedSingleKeys.size());
        assertTrue(requestedSingleKeys.contains(9));

        assertTrue(requestedMultipleKeys.isEmpty());
    }

    @Test
    public void get_optional_null() throws Exception {
        assertTrue(requestedSingleKeys.isEmpty());

        assertFalse(cache.get(10).isPresent());
        assertEquals(1, requestedSingleKeys.size());
        requestedSingleKeys.contains(10);
    }

    @Test
    public void getAll() throws Exception {
        assertEquals("value 1", cache.get(1).get());
        assertEquals("value 2", cache.get(2).get());
        assertEquals(2, requestedSingleKeys.size());
        assertTrue(requestedMultipleKeys.isEmpty());

        final ImmutableMap<Integer, Optional<String>> results = cache.getAll(Lists.newArrayList(1, 2, 3, 4, 5));
        assertEquals(2, requestedSingleKeys.size());
        assertTrue(requestedSingleKeys.contains(1));
        assertTrue(requestedSingleKeys.contains(2));

        assertEquals(3, requestedMultipleKeys.size());
        assertTrue(requestedMultipleKeys.contains(3));
        assertTrue(requestedMultipleKeys.contains(4));
        assertTrue(requestedMultipleKeys.contains(5));

        assertEquals(5, results.keySet().size());

        assertEquals("value 1", results.get(1).get());
        assertEquals("value 2", results.get(2).get());
        assertEquals("multiple value 3", results.get(3).get());
        assertEquals("multiple value 4", results.get(4).get());
        assertEquals("multiple value 5", results.get(5).get());
    }

    @Test
    public void getAll_empty_values() throws Exception {
        assertEquals("value 1", cache.get(1).get());
        assertEquals(1, requestedSingleKeys.size());
        assertTrue(requestedMultipleKeys.isEmpty());

        try {
            cache.getAll(Lists.newArrayList(1, 2, 10, 11));
            fail("loadAll에서 모든 key에 대한 값을 넣지 않으면 예외가 발생한다.");
        } catch (CacheLoader.InvalidCacheLoadException e) {
            assertEquals("loadAll failed to return a value for 10", e.getMessage());
            assertEquals(1, requestedSingleKeys.size());
            assertEquals(3, requestedMultipleKeys.size());
        }

    }
}
