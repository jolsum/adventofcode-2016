package lars.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day3 {

	public static void main(String[] args) throws IOException {

		long count = Files.readAllLines(Paths.get("input", "3.txt"))
				.stream()
				.map(Day3::toTriangle)
				.filter(Day3::isValid)
				.count();

		System.out.println(count);

	}

	private static boolean isValid(Triangle t) {
		return (t.a < t.b + t.c) && (t.b < t.a + t.c) && (t.c < t.a + t.b);
	}

	private static Triangle toTriangle(String line) {
		String[] parts = line
				.trim()
				.replaceAll("  ", " ")
				.replaceAll("  ", " ")
				.split(" ");

		Triangle t = new Triangle();
		t.a = Integer.parseInt(parts[0]);
		t.b = Integer.parseInt(parts[1]);
		t.c = Integer.parseInt(parts[2]);
		return t;
	}

	private static class Triangle {
		int a;
		int b;
		int c;
	}

}
