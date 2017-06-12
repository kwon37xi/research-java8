package zookeeper.curator.recipes.locks.sharedreentrant;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.slf4j.LoggerFactory.getLogger;

public class SharedReentrantLockExample {
    private static final Logger log = getLogger(SharedReentrantLockExample.class);

    public static void main(String[] args) {
        int appNum = new Random().nextInt();
        log.info("Application app Num - {}", appNum);
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        InterProcessMutex mutex = null;
        try (CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", retryPolicy)) {
            client.start();
            mutex = new InterProcessMutex(client, "/examples/locks");
            log.info("Waiting for lock - {}", appNum);

            if (!mutex.acquire(10, TimeUnit.SECONDS)) {
                log.info("Lock Fail - {}", appNum);
            } else {
                try {
                    log.info("Lock work - {} wait for 8 seconds", appNum);
                    TimeUnit.SECONDS.sleep(8);
                    log.info("Lock end - {}", appNum);
                } finally {
                    mutex.release();
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
