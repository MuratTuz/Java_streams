package streamAPI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PeopleData {
	private final static Charset ENCODING = StandardCharsets.ISO_8859_1;
	
	public static List<Person> read(String fileName) throws IOException {
		return decodePeople(readCSV(fileName));
	}
	
	public static void write(String fileName, List<Person> people) throws IOException {
		writeCSV(fileName, encodePeople(people));
	}
	
	private static List<Person> decodePeople(List<List<String>> list) {
		List<Person> people = new ArrayList<Person>();
		for (List<String> line : list) {
			people.add(decodePerson(line));
		}
		return people;
	}

	private static Person decodePerson(List<String> line) {
		Person person = new Person(line.get(0), line.get(1),
				Gender.fromCode(line.get(2)), line.get(3),
				Integer.parseInt(line.get(4)), Integer.parseInt(line.get(5)));
		return person;
	}
	
	public static List<List<String>> readCSV(String fileName) throws IOException {
		List<List<String>> values = new ArrayList<List<String>>();
		try (Scanner scanner = new Scanner(Paths.get(fileName), ENCODING.name())) {
			while (scanner.hasNextLine()) {
				values.add(readCSVLine(scanner.nextLine()));
			}
		}
		return values;
	}

	private static List<String> readCSVLine(String line) {
		List<String> values = new ArrayList<String>();
		try (Scanner scanner = new Scanner(line)) {
			scanner.useDelimiter(";");
			while (scanner.hasNext()) {
				values.add(scanner.next());
			}
		}
		return values;
	}
	
	private static List<List<String>> encodePeople(List<Person> people) {
		List<List<String>> list = new ArrayList<List<String>>();
		for (Person person : people) {
			list.add(encodePerson(person));
		}
		return list;
	}

	private static List<String> encodePerson(Person person) {
		List<String> list = new ArrayList<String>();
		list.add(person.getFirstName());
		list.add(person.getLastName());
		list.add(person.getGender().toCode());
		list.add(person.getCity());
		list.add(Integer.toString(person.getAge()));
		list.add(Integer.toString(person.getSalary()));
		return list;
	}
	
	public static void writeCSV(String fileName, List<List<String>> contents) throws IOException {
		StringBuilder formatted = new StringBuilder();
		String lineSeparator = System.getProperty("line.separator");
		for(List<String> line : contents)
		{
			for(String value : line)
			{
				formatted.append(value);
				formatted.append(';');
			}
			formatted.append(lineSeparator);
		}
		writeCSV(Paths.get(fileName).toFile(), formatted.toString());
	}
	
	private static void writeCSV(File file, String contents) throws IOException {
		try (Writer output = new BufferedWriter(new FileWriter(file))) {
			output.write(contents);
		}
	}
}
