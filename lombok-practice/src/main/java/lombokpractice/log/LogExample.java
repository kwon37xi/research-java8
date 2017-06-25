package lombokpractice.log;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

/**
 * https://projectlombok.org/features/log
 *
 * 각종 로거 프레임워크용 Log 객체를 만든다.
 *
 * - {@code @CommonsLog} Apache Commons Logging
 * - {@code @JBossLog} JBoss Legger
 * - {@code @Log} java.util.logging
 * - {@code @Log4j} Apache log4j
 * - {@code @Log4j2} Apache log4j 2
 * - {@code @Slf4j} Slf4j
 * - {@code @XSlf4j} XSlf4j
 *
 * {@code @Slf4j(topic="로거이름")} 형태로 로거이름 명시가능. 그렇지 않으면 Class의 FQCN 된다.
 *
 * 추후에 log level을 자동으로 체크해서 로그 레벨이 만족될 때만 로깅 코드가 실행되게 하는 기능이 추가 될 수도 있다.(아직은 아님)
 *
 * configurations
 * - lombok.log.fieldName = an identifier (default: log) 로깅 필드 이름 지정
 * - lombok.log.fieldIsStatic = [true | false] (default: true) : 로깅 필드를 static으로 만들지 여부. false이면 인스턴스 필드가 됨.
 * - lombok.log.flagUsage = [warning | error] (default: not set) 전체 로깅 어노테이션 사용 가능 여부. 각 annotation 별로도 지정가능.
 */
public class LogExample {
    @Log
    public static class JavaUtilLogExample {
        public static void main(String[] args) {
            log.severe("Something's wrong here");
        }
    }

    @Slf4j(topic = "CounterLog")
    public static class Slf4jLogExample {
        public static void main(String[] args) {
            log.error("Something else is wrong here");
        }
    }

}
