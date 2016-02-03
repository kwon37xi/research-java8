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

		persons.find("Jack")
			.map(person -> person.getAddress())
			.map(address -> address.getCity())
			.onFailure(throwable -> {
				throw new IllegalStateException(throwable);
			})
			.onSuccess(city -> System.out.println("Jack's city is " + city.getCityName()));


		persons.find("james")
			.map(person -> person.getAddress())
			.map(address -> address.getCity());


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
