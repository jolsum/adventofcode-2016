package lars.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7 {

	private static final Pattern HYPERNET_SEQUENCE = Pattern.compile("\\[([a-z]*)\\]");

	public static void main(String[] args) throws IOException {

		long count = Files.readAllLines(Paths.get("input", "7.txt"))
				.stream()
				.filter(Day7::supportsTLS)
				.count();

		System.out.println("Part1: " + count);
	}

	private static boolean supportsTLS(String line) {

		if (!containsAbba(line)) {
			return false;
		}

		Matcher matcher = HYPERNET_SEQUENCE.matcher(line);

		while (matcher.find()) {
			String hypernetSequence = matcher.group(1);

			if (containsAbba(hypernetSequence)) {
				return false;
			}
		}

		return true;
	}

	private static boolean containsAbba(String line) {

		for (int i = 0; i < line.length() - 3; i++) {
			String part1 = line.substring(i, i + 2);
			String part2 = line.substring(i + 2, i + 4);

			String part2rev = new StringBuilder(part2).reverse().toString();

			if (part1.equals(part2rev) && !part1.equals(part2)) {
				return true;
			}
		}

		return false;
	}

}
