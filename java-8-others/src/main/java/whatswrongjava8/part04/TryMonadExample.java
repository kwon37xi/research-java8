package whatswrongjava8.part04;

import whatswrongjava8.part04.trymonad.Try;
import whatswrongjava8.part04.trymonad.TryConsumer;
import whatswrongjava8.part04.trymonad.TryMapFunction;

import java.util.HashMap;

/**
 *
 */
public class TryMonadExample {
	public static void main(String[] args) {
		TryMap<String, Person> persons = new TryMap<>();

		Person jack = new Person(new Address(new City("SongNam")));
		persons.put("Jack", jack);

		Person james = new Person(new Address(null));
		persons.put("james", james);

		// without exception
		persons.find("Jack")
			.map(person -> person.getAddress())
			.map(address -> address.getCity())
			.onFailure(throwable -> {
				System.out.println("Error occured : " + throwable.getMessage());;
			})
			.onSuccess(city -> System.out.println("Jack's city is " + city.getCityName()));

			persons.find("james")
				.map(person -> person.getAddress())
				.map(address -> {
					final City city = address.getCity();
					if (city == null) {
						throw new IllegalStateException("city must not be null."); // 예외는 여기서 발생했지만, 실제 처리는 onFailure()에서 한다.
					}
					return city;
				})
				.map(city -> city.getCityName())
				.onFailure(throwable -> System.out.println("Error occured : " + throwable.getMessage()));


	}

	public static class TryMap<T, U> extends HashMap<T, U> {
		public Try<U> find(T key) {
			U value = super.get(key);
			if (value == null) {
				return Try.failure(new IllegalStateException("key " + key + " does not exists."));
			} else {
				return Try.successful(value);
			}
		}
	}
}
