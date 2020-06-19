package streamAPI;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
				.filter(person -> person.getGender().equals(Gender.MALE))
				.mapToInt(person -> person.getAge())
				.average()
				.ifPresent(System.out::println);

		people.stream()
				.filter(person -> person.getCity().equals("Zürich"))
				.mapToInt(person -> person.getAge())
				.max()
				.ifPresent(System.out::println);

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


	}
}
