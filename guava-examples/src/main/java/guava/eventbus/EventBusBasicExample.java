package guava.eventbus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 동기식과 비동기식 비교
 *
 * @see EventBus
 * @see AsyncEventBus
 */
public class EventBusBasicExample {
    private static final Logger log = LoggerFactory.getLogger(EventBusBasicExample.class);

    public static class MessageListener {
        @Subscribe
        public void receive(String message) throws InterruptedException {
            log.info("Receiving message... {}", message);
            receiving(3, message);
            log.info("Done");
        }

        private static void receiving(int count, String message) throws InterruptedException {
            for (int i = 0; i < count; i++) {
                log.info("... {}", message);
                TimeUnit.SECONDS.sleep(1L);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        log.info("### Starting Event Bus ###");
        EventBus eventBus = new EventBus("sync");
        ExecutorService executor = Executors.newFixedThreadPool(5);
        AsyncEventBus asyncEventBus = new AsyncEventBus("async", executor);

        asyncEventBus.register(new MessageListener());
        eventBus.register(new MessageListener());

        asyncEventBus.post("ASYNC event");
        eventBus.post("SYNC event");

        log.info("### End ###");

        if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
            executor.shutdown();
        }

    }
}
