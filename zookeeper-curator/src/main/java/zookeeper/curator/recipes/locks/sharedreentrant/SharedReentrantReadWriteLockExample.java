package zookeeper.curator.recipes.locks.sharedreentrant;

/**
 * <a href="http://curator.apache.org/curator-recipes/shared-reentrant-read-write-lock.html">Shared Reentrant Read Write Lock - 재진입이 가능한 공유 Read/Write Lock</a>
 * <br>
 * Read/Write Lock을 여러 프로세스간에 유지할 수 있게 해준다.
 * Write Lock을 가진쪽은 Read Lock도 가질 수 있다.
 * Read Lock은 Write Lock이 없으면 여러 프로세스가 동시에 읽을 수 있다.
 * Write Lock을 누군가 획득하면 Read Lock을 가진쪽은 Lock을 상실한다.
 * Write Lock은 Read Lock으로 다운그레이드 될 수 있지만 Read Lock을 Write Lock으로 업그레이드할 수는 없다.
 * <br>
 * <code>gradlew runExample -PrunMain=zookeeper.curator.recipes.locks.sharedreentrant.SharedReentrantReadWriteLockExample -DappName=[appName]</code>
 */
public class SharedReentrantReadWriteLockExample {
}
