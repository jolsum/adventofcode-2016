package lars.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.util.Pair;

public class Day4 {

	public static void main(String[] args) throws IOException {

		List<String> lines = Files.readAllLines(Paths.get("input", "4.txt"));

		long part1 = lines.stream()
				.filter(Day4::isValid)
				.map(l -> l.substring(l.lastIndexOf('-') + 1, l.indexOf('[')))
				.mapToInt(Integer::parseInt)
				.sum();

		System.out.println("Part 1: " + part1);

		int part2 = lines.stream()
				.filter(Day4::isValid)
				.map(Day4::decrypt)
				.filter(l -> l.getKey().contains("northpole"))
				.mapToInt(Pair::getValue)
				.findFirst()
				.getAsInt();

		System.out.println("Part 2: " + part2);
	}

	private static boolean isValid(String line) {

		// Strip out dashes
		String letters = line.substring(0, line.lastIndexOf('-')).replace("-", "");

		// Count occurences
		Map<Character, AtomicInteger> counts = new TreeMap<>();
		for (char c : letters.toCharArray()) {
			counts.computeIfAbsent(c, k -> new AtomicInteger()).incrementAndGet();
		}

		// Sort according to count, or alphabetized if equal
		List<Character> cc = new ArrayList<>(counts.keySet());
		Collections.sort(cc, (a, b) -> {
			int countA = counts.get(a).get();
			int countB = counts.get(b).get();

			if (countA < countB) {
				return 1;
			} else if (countB < countA) {
				return -1;
			}
			return Character.compare(a, b);
		});

		// Calculate checksum of first 5
		String checksum = "" + cc.get(0) + cc.get(1) + cc.get(2) + cc.get(3) + cc.get(4);

		// Expected checksum
		String realChecksum = line.substring(line.indexOf('[') + 1, line.indexOf(']'));

		return checksum.equals(realChecksum);
	}

	private static Pair<String, Integer> decrypt(String line) {

		int pos = line.lastIndexOf('-');
		int count = Integer.parseInt(line.substring(pos + 1, line.indexOf('[')));

		String decrypted = "";
		char[] chars = line.substring(0, pos).toCharArray();
		for (char c : chars) {
			if (c == '-') {
				decrypted += " ";
				continue;
			}

			int nCharV = (c + count);
			nCharV = (nCharV - 97) % 26 + 97;
			char nChar = (char) nCharV;

			decrypted += nChar;
		}

		return new Pair<String, Integer>(decrypted, count);
	}

}
