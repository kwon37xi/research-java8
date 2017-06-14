package zookeeper.curator.recipes.locks.sharedreentrant;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import zookeeper.curator.recipes.ZookeeperExamples;

import java.util.concurrent.TimeUnit;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * <a href="http://curator.apache.org/curator-recipes/shared-lock.html">Shared Lock - 공유 Lock(재진입 불가)</a>
 *
 * <code>gradlew runExample -PrunMain=zookeeper.curator.recipes.locks.sharedreentrant.SharedLockExample -DappName=[appName]</code>
 */
public class SharedLockExample {
    private static final Logger log = getLogger(SharedLockExample.class);

    public static void main(String[] args) {
        String appName = System.getProperty("appName", "unknown");
        log.info("Application app Num - {}", appName);
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

        InterProcessSemaphoreMutex mutex = null;

        try (CuratorFramework client = CuratorFrameworkFactory.newClient(ZookeeperExamples.ZOOKEEPER_CONNECTION, retryPolicy)) {
            client.start();
            mutex = new InterProcessSemaphoreMutex(client, "/examples/locks");
            log.info("Waiting for lock - {}", appName);

            if (!mutex.acquire(20, TimeUnit.SECONDS)) {
                log.info("Lock Fail - {}", appName);
            } else {
                try {
                    log.info("Lock work - {} wait for 8 seconds", appName);
                    TimeUnit.SECONDS.sleep(8);

                    /*
                    - reentrant가 안되기 때문에 아래 코드를 실행하면 계속 lock 상태 유지됨.
                    log.info("Lock inner again - {} wait for 5 seconds", appName);
                    mutex.acquire();
                    TimeUnit.SECONDS.sleep(5);
                    mutex.release();
                    log.info("Lock inner released - {}", appName);
                    */
                    log.info("Lock outer end - {}", appName);
                } finally {
                    mutex.release();
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
