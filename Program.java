package streamAPI;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Program {
	public static void main(String[] args) throws IOException {
		List<Person> people = PeopleData.read("src/streamAPI/people.csv");
		// TODO: Use stream API

		/*people.stream()
				.filter(person -> person.getGender().equals(Gender.FEMALE))
				.filter(person -> person.getFirstName().length() < 4)
				.map(person -> person.getFirstName())
				.distinct()
				.forEach(System.out::println);*/


		people.stream()
				.parallel()
				.filter(person -> person.getGender().equals(Gender.MALE))
				.mapToInt(person -> person.getAge())
				.average()
				.ifPresent(System.out::println);

		long timeFirstStream = System.nanoTime();
		people.stream()
				.filter(person -> person.getCity().equals("Zürich"))
				.mapToInt(person -> person.getAge())
				.max()
				.ifPresent(System.out::println);
		long timeLastStream = System.nanoTime();

		people.stream()
				.filter(person -> person.getCity().equals("Zürich"))
				.mapToInt(person -> person.getAge())
				.min()
				.ifPresent(System.out::println);

		people.stream()
				.sorted((p1,p2)->Integer.valueOf(p2.getSalary()).compareTo(Integer.valueOf(p1.getSalary())))
				.peek(p->System.out.println("hello"))
				.map(person -> person.getFirstName() + " "
						+ person.getLastName() + " " + person.getCity())
				.limit(10)
				.forEach(System.out::println);
/*
		Map<String, Double> personList = people.stream()
				.collect(Collectors.groupingBy(Person::getCity, Collectors.averagingInt(Person::getAge)));

		for (Map.Entry<String, Double> entry : personList.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}*/

		long timeFirst = System.nanoTime();
		int averageAge = 0;
		for (Person person : people){
			averageAge += person.getAge();
		}
		System.out.println(averageAge/people.size());
		long timeLast = System.nanoTime();
		System.out.println("Collection timestamp : "+ String.valueOf(timeLast-timeFirst));
		System.out.println("Stream timestamp : " + String.valueOf(timeLastStream-timeFirstStream));

		IntPredicate isPrime = x->{
			for (int i = 2; i < x/2; i++) {
				if (x % i == 0) return false;
			}
			return true;
		};

		long counterPrime = IntStream.iterate(2, i-> i + 1)
				.parallel()
				.filter(isPrime)
				.limit(100)
				.max().getAsInt();

		long counterPrimeBetween = IntStream.rangeClosed(2,1_000)
				.parallel()
				.filter(isPrime)
				.count();

		System.out.println(counterPrime + " le grand nombre premier");
		System.out.println(counterPrimeBetween + " la quantité de nombres premiers jusqu'à 1.000");




	}


}
