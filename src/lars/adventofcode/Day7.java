package lars.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7 {

	private static final Pattern HYPERNET_SEQUENCE = Pattern.compile("\\[([a-z]*)\\]");

	public static void main(String[] args) throws IOException {

		List<String> lines = Files.readAllLines(Paths.get("input", "7.txt"));

		long count = lines.stream()
				.filter(Day7::supportsTLS)
				.count();

		System.out.println("Part1: " + count);

		long countSSL = lines.stream()
				.filter(Day7::supportsSSL)
				.count();

		System.out.println("Part2: " + countSSL);
	}

	private static boolean supportsTLS(String line) {

		if (!containsAbba(line)) {
			return false;
		}

		return !getHypernetSequences(line)
				.stream()
				.filter(Day7::containsAbba)
				.findAny()
				.isPresent();
	}

	private static boolean supportsSSL(String line) {

		String wo = line.replaceAll("\\[([a-z]*)\\]", " ");
		Collection<String> hypernetSequences = getHypernetSequences(line);

		for (int i = 0; i < wo.length() - 2; i++) {
			char c1 = wo.charAt(i);
			char c2 = wo.charAt(i + 1);
			char c3 = wo.charAt(i + 2);

			if (c1 != c2 && c1 == c3) {
				String bab = Character.toString(c2) + Character.toString(c1) + Character.toString(c2);
				for (String hs : hypernetSequences) {
					if (hs.contains(bab)) {
						return true;
					}
				}
			}
		}

		return false;
	}

	private static Collection<String> getHypernetSequences(String line) {
		Matcher matcher = HYPERNET_SEQUENCE.matcher(line);

		Collection<String> res = new ArrayList<>();
		while (matcher.find()) {
			res.add(matcher.group(1));
		}
		return res;
	}

	private static boolean containsAbba(String line) {

		for (int i = 0; i < line.length() - 3; i++) {
			char c1 = line.charAt(i);
			char c2 = line.charAt(i + 1);
			char c3 = line.charAt(i + 2);
			char c4 = line.charAt(i + 3);

			if (c1 != c2 && c1 == c4 && c2 == c3) {
				return true;
			}
		}

		return false;
	}

}
