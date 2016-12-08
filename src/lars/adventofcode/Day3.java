package lars.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day3 {

	public static void main(String[] args) throws IOException {

		List<String> lines = Files.readAllLines(Paths.get("input", "3.txt"));

		long part1 = lines.stream()
				.map(Day3::toTriangle)
				.filter(Day3::isValid)
				.count();

		System.out.println("Part 1: " + part1);

		long part2 = makeTriangles(lines).stream()
				.filter(Day3::isValid)
				.count();

		System.out.println("Part 2: " + part2);
	}

	private static List<Triangle> makeTriangles(List<String> lines) {

		List<Triangle> res = new ArrayList<>();
		for (int i = 0; i < lines.size();) {
			Triangle t1 = toTriangle(lines.get(i++));
			Triangle t2 = toTriangle(lines.get(i++));
			Triangle t3 = toTriangle(lines.get(i++));

			res.add(new Triangle(t1.a, t2.a, t3.a));
			res.add(new Triangle(t1.b, t2.b, t3.b));
			res.add(new Triangle(t1.c, t2.c, t3.c));
		}
		return res;
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

		return new Triangle(
				Integer.parseInt(parts[0]),
				Integer.parseInt(parts[1]),
				Integer.parseInt(parts[2]));
	}

	private static class Triangle {
		int a;
		int b;
		int c;

		public Triangle(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}

	}

}
