package zookeeper.curator.recipes.locks.sharedreentrant;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import zookeeper.curator.recipes.ZookeeperExamples;

import java.util.concurrent.TimeUnit;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * <code>gradlew runExample -PrunMain=zookeeper.curator.recipes.locks.sharedreentrant.SharedReentrantLockExample -DappName=[appName]</code>
 */
public class SharedReentrantLockExample {
    private static final Logger log = getLogger(SharedReentrantLockExample.class);

    public static void main(String[] args) {
        String appName = System.getProperty("appName", "unknown");
        log.info("Application app Num - {}", appName);
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        InterProcessMutex mutex = null;
        try (CuratorFramework client = CuratorFrameworkFactory.newClient(ZookeeperExamples.ZOOKEEPER_CONNECTION, retryPolicy)) {
            client.start();
            mutex = new InterProcessMutex(client, "/examples/locks");
            log.info("Waiting for lock - {}", appName);

            if (!mutex.acquire(10, TimeUnit.SECONDS)) {
                log.info("Lock Fail - {}", appName);
            } else {
                try {
                    log.info("Lock work - {} wait for 8 seconds", appName);
                    TimeUnit.SECONDS.sleep(8);
                    log.info("Lock end - {}", appName);
                } finally {
                    mutex.release();
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}