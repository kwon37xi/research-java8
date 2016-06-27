package ch05.sec05;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 *
 */
public class Sec05Example {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
        );

        System.out.println("2011 년에 일어난 모든 트랜잭션 오름차순 정렬 : " + transactions.stream()
            .filter(t -> t.getYear() == 2011)
            .sorted(Comparator.comparing(Transaction::getValue))
            .collect(toList()));

        System.out.println("거래자가 근무하는 도시를 중복없이 나열 : " + transactions.stream()
            .map(t -> t.getTrader().getCity())
            .distinct()
            .collect(toList())
        );

        System.out.println("Cambridge에서 근무하는 모든 거래자를 찾아 이름순 정렬 : " + transactions.stream()
            .map(Transaction::getTrader)
            .filter(t -> t.getCity().equals("Cambridge"))
            .distinct()
            .sorted((t1, t2) -> t1.getName().compareTo(t2.getName()))
            .collect(toList())
        );

        System.out.println("모든 거래자의 이름을 알파벳 순으로 정렬해서 반환 : " + transactions.stream()
            .map(t -> t.getTrader().getName())
            .distinct()
            .sorted()
            .reduce((n1, n2) -> n1 + ", " + n2) // 여기서 원하는 바는 하나의 문자열로 연결하기
            .orElse("")
        );

        System.out.println("Milan에 거래자가 있는가? " + transactions.stream()
            .anyMatch(t -> t.getTrader().getCity().equals("Milan"))
        );

        System.out.println("Cambridge에 거주하는 거래자의 모든 트랜잭션 값 : " + transactions.stream()
            .filter(t -> t.getTrader().getCity().equals("Cambridge"))
            .map(Transaction::getValue)
            .collect(toList())
        );

        System.out.println("전체 트랜잭션 중 최댓값 : " + transactions.stream()
            .map(Transaction::getValue)
            .max(Integer::compare)
            .orElse(0)
        );

        System.out.println("전체 트랜잭션 중 최솟값 : " + transactions.stream()
            .map(Transaction::getValue)
            .min(Integer::compare)
            .orElse(0)
        );

    }
}
