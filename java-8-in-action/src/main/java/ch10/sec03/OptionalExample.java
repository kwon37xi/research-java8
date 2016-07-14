package ch10.sec03;

import java.util.Optional;

public class OptionalExample {
    public static void main(String[] args) {
        System.out.println(getCarInsuranceName(Optional.empty()));

        final Insurance javaInsu = new Insurance("Java Insu");
        final Car car = new Car(Optional.of(javaInsu));
        Person person = new Person(Optional.of(car), 30);

        System.out.println("getCarInsuranceName with minAge 30 : " + getCarInsuranceName(Optional.ofNullable(person), 30));
        System.out.println("getCarInsuranceName with minAge 31 : " + getCarInsuranceName(Optional.ofNullable(person), 31));
    }

    public static String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar)
            .flatMap(Car::getInsurance)
            .map(Insurance::getName)
            .orElse("Unknown");
    }

    public static String getCarInsuranceName(Optional<Person> person, int minAge) {
        return person
            .filter(p -> p.getAge() >= minAge)
            .flatMap(Person::getCar)
            .flatMap(Car::getInsurance)
            .map(Insurance::getName)
            .orElse("Unknown");
    }
    public static Insurance findCheapestIsurance(Person person, Car car) {
        // 사용자와 자동차를 입력값으로 넣어 해당 사용자가 해당 자동차 보험을 가장 싸게 들 수 있는 보험사 찾음.
        Insurance cheapestInsurance = null;
        return cheapestInsurance;
    }
    public static Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> person, Optional<Car> car) {
        return person.flatMap((Person p) -> car.map((Car c) -> findCheapestIsurance(p, c)));
    }
}
