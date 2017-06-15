package zookeeper.curator.recipes;

import org.slf4j.Logger;

import java.util.Random;

import static org.slf4j.LoggerFactory.getLogger;

public class ZookeeperExamples {
    private static final Logger log = getLogger(ZookeeperExamples.class);

    public static final String ZOOKEEPER_CONNECTION = "127.0.0.1:2181";

    public static String appName() {
        String appName = System.getProperty("appName", "app " + new Random().nextInt());
        log.info("Application name - {}", appName);
        return appName;
    }
}
