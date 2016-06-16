* [5 Difference between BufferedReader and Scanner class in Java - Example](http://java67.blogspot.com/2016/06/5-difference-between-bufferedreader-and-scanner-in-java.html)

## Scanner와 BufferedReader의 차이점
*  `BufferedReader`는 문자열 읽기용이고, `Scanner`는 문자열을 읽고 이를 파싱하여 Java primitive type으로 변환할 수 있다.
* `BufferedReader`는 다소 버퍼 크기가 크고(8KB), `Scanner`는 작다(1KB). 따라서 매우 긴 문자열을 읽을 때는 `BufferedReader`가
 String 이 짧은 입력을 다룰때는 `Scanner`가 유리하다.
* `BufferedReader`는 Java 1.1부터, `Scanner`는 1.5 부터.
* `Scanner`는 RegEx로 텍스트 입력을 파싱한다. 구분자(delimiter)를 지정하여 텍스트를 파싱해 primitive 타입으로 변환할 수 있다,
 `BufferedReader`에는 `readLine()`만 있다.
* `BufferedReader`는  `synchronized`이고 `Scanner`는 아니다. 따라서 `Scanner` 객체를 멀티 쓰레드에서 공유하면 안된다.
* `BufferedReader`는 동기화때문에 약간 느리다. 하지만 `Scanner`는 RegEx때문에 느려질 수 있다.
