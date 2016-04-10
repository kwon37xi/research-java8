package ch07_nashorn;

/**
 * 보편적인 명령실행
 * $EXEC('명령')
 *
 * 명령에 표준입력 제공시
 * $EXEC('명령', '입력') --> $EXEC('grep -v vim', `ls -al`) : ls -al 의 실행결과를 grep -v vim 명령에 전달
 *
 * 결과는 $OUT, $ERR, $EXIT 로 저장
 *
 * Scripting 모드에서는 문자열 인터폴레이션(${변수명})과 HERE Document 를 사용할 수 있다.
 *
 * script 파일에서는 명령행 인자를 arguments 배열로 받는다. 혹은 jjs에서는 $ARG 도 가능.
 * $ENV 로 환경변수를 얻을 수 있다. $ENV.JAVA_HOME 처럼.
 *
 * 사용자입력 : var username = readLine('Username : ')
 * 비밀번호 입력 : java.lang.System.console().readPassword('Password: ')
 */
public class Lecture12 {
}
