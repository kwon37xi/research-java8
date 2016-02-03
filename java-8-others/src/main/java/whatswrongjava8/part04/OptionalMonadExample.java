package whatswrongjava8.part04;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * {@link java.util.Optional} is monad.
 * A monad a set of three things:<br>
 * parameterized type M&lt;T&gt; : ex {@link java.util.Optional}<br>
 * "unit" function T -> M&lt;T&gt; ex {@link Optional#of}<br>
 * "bind" operation: M&lt;T&gt; bind T -&gt; M&lt;U&gt; = M&lt;U&gt; : ex {@link Optional#flatMap(Function)}<br>
 * <p>
 *     Optional 모나드는 값이 존재하던 말던 에러 없이 특정 기능을 조합하려고 만들어진 것이다.
 * </p>
 */
public class OptionalMonadExample {

	public static void main(String[] args) {

		Map<String, Person> persons = new HashMap<>();

		Person jack = new Person(new Address(new City("SongNam")));
		persons.put("Jack", jack);

		Person james = new Person(new Address(null));
		persons.put("james", james);

		// flatMap은 인자자체가 Optional<U> 여야 한다.
		// 값이 올바르게 출력된다. with flatMap
		Optional.ofNullable(persons.get("Jack"))
			.flatMap(x -> Optional.ofNullable(x.getAddress()))
			.flatMap(x -> Optional.ofNullable(x.getCity()))
			.ifPresent(city -> System.out.println("When value exist with flatMap: " + city.getCityName()));

		// 중간에 x.getCity()가 null이지만 아무 문제 없이 실행된다. with flatMap
		Optional.ofNullable(persons.get("James"))
			.flatMap(x -> Optional.ofNullable(x.getAddress()))
			.flatMap(x -> Optional.ofNullable(x.getCity()))
			.ifPresent(city -> System.out.println("When value exist with flatMap : " + city.getCityName()));


		// map 은 리턴되는 값을 자동으로 ofNullable() 로 감싸서 돌려준다.
		// 값이 올바르게 출력된다.
		Optional.ofNullable(persons.get("Jack"))
			.map(x -> x.getAddress())
			.map(x -> x.getCity())
			.ifPresent(city -> System.out.println("When value exist with map : " + city.getCityName()));


		// 중간에 x.getCity()가 null이지만 아무 문제 없이 실행된다. with map
		Optional.ofNullable(persons.get("James"))
			.map(x -> x.getAddress())
			.map(x -> x.getCity())
			.ifPresent(city -> System.out.println("When value exist with flatMap : " + city.getCityName()));

	}
}
