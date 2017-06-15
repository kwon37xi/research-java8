package zookeeper.curator.recipes.locks.sharedreentrant;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import zookeeper.curator.recipes.ZookeeperExamples;

import java.util.concurrent.TimeUnit;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * <a href="http://curator.apache.org/curator-recipes/shared-reentrant-read-write-lock.html">Shared Reentrant Read Write Lock - 재진입이 가능한 공유 Read/Write Lock</a>
 * <br>
 * Read/Write Lock을 여러 프로세스간에 유지할 수 있게 해준다.
 * Write Lock을 가진쪽은 Read Lock도 가질 수 있다.
 * Read Lock은 Write Lock이 없으면 여러 프로세스가 동시에 읽을 수 있다.
 * Write Lock은 Read Lock으로 다운그레이드 될 수 있지만 Read Lock을 Write Lock으로 업그레이드할 수는 없다.
 * <br>
 * <code>gradlew runExample -PrunMain=zookeeper.curator.recipes.locks.sharedreentrant.SharedReentrantReadWriteLockExample -DappName=[appName]</code>
 * appName 에 write 문자열이 들어있으면 write lock, 아니면 read lock 잡음
 */
public class SharedReentrantReadWriteLockExample {
    private static final Logger log = getLogger(SharedReentrantReadWriteLockExample.class);

    public static void main(String[] args) {
        String appName = ZookeeperExamples.appName();

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        InterProcessReadWriteLock interProcessReadWriteLock = null;

        try (CuratorFramework client = CuratorFrameworkFactory.newClient(ZookeeperExamples.ZOOKEEPER_CONNECTION, retryPolicy)) {
            client.start();

            interProcessReadWriteLock = new InterProcessReadWriteLock(client, "/examples/readwritelocks");

            if (appName.contains("write")) {
                write(appName, interProcessReadWriteLock);
            } else {
                read(appName, interProcessReadWriteLock);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    private static void read(String appName, InterProcessReadWriteLock interProcessReadWriteLock) throws Exception {
        final InterProcessMutex readLock = interProcessReadWriteLock.readLock();
        final InterProcessMutex writeLock = interProcessReadWriteLock.writeLock();

        log.info("Waiting for read lock.");
        if (readLock.acquire(20, TimeUnit.SECONDS)) {
            log.info("Read lock acquired - {}", appName);
            //            writeLock.acquire();
            //            log.info("Write lock acquired.");
            work(10);
            //            writeLock.release();
            //            log.info("Write lock released.");
            readLock.release();
            log.info("Read lock released.");
        } else {
            log.info("Fail to get read lock");
        }
    }

    private static void write(String appName, InterProcessReadWriteLock interProcessReadWriteLock) throws Exception {
        final InterProcessMutex writeLock = interProcessReadWriteLock.writeLock();
        final InterProcessMutex readLock = interProcessReadWriteLock.readLock();

        log.info("Waiting for write lock.");
        if (writeLock.acquire(20, TimeUnit.SECONDS)) {
            log.info("Write lock acquired - {}", appName);

            // read lock
            readLock.acquire();
            log.info("Read lock acquired - {}", appName);
            work(10);
            readLock.release();
            log.info("Read lock released.");

            writeLock.release();
            log.info("Write lock released.");
        } else {
            log.info("Fail to get write lock");
        }
    }

    private static void work(int times) throws Exception {
        for (int i = 0; i < times; i++) {
            System.out.print(".");
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println();
    }
}
