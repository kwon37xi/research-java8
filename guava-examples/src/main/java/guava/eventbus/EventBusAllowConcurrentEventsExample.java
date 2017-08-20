package guava.eventbus;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import kr.pe.kwonnam.slf4jlambda.LambdaLogger;
import kr.pe.kwonnam.slf4jlambda.LambdaLoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @see AllowConcurrentEvents 를 지정하지 않으면 해당 Subscription 메소드는 synchrnoized 블록으로 감싸져서
 * 호출되기 때문에 multi thread에서 동시에 호출되지 않고 이벤트 발생 순서에 따라 순차적으로 실행된다.
 *
 * 특별한 이유가 없다면 subscribe 메소드는 thread-safe 하게 작성하고 {@code @AllowConcurrentEvents}를 붙여준다.
 * 이렇게 하면 해당 메소드는 multi thread에서 동시에 호출/실행되게 된다.
 */
public class EventBusAllowConcurrentEventsExample {
    private static final LambdaLogger log = LambdaLoggerFactory.getLogger(EventBusBasicExample.class);

    public static class MessageListener {
        @AllowConcurrentEvents // 주석처리하고 실행하면 receive 메소드가 이벤트 발생 순서에 따라 순차 실행됨.
        @Subscribe
        public void receive(String message) throws InterruptedException {
            log.info(() -> "Receiving message... " + message);
            receiving(3, message);
            log.info("Receiving message done {}", () -> message);
        }

        private static void receiving(int count, String message) throws InterruptedException {
            for (int i = 0; i < count; i++) {
                log.info(() -> "... " + message);
                TimeUnit.SECONDS.sleep(1L);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        log.info("### Starting Event Bus ###");
        EventBus eventBus = new EventBus("sync");

        eventBus.register(new MessageListener());

        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            final int count = i;
            executor.submit(() -> {
                log.info(() -> "Start event " + count);
                eventBus.post("event " + count);
                log.info("End event {}", () -> count);
            });
        }

        log.info("### End ###");

        if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
            executor.shutdown();
        }

    }
}
