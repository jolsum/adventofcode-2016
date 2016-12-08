package lars.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Day6 {

	public static void main(String[] args) throws IOException {

		Map<Integer, Map<Character, AtomicInteger>> counts = new HashMap<>();

		List<String> lines = Files.readAllLines(Paths.get("input", "6.txt"));
		for (String line : lines) {

			char[] chars = line.toCharArray();
			for (int i = 0; i < chars.length; i++) {
				Map<Character, AtomicInteger> posCounts = counts.computeIfAbsent(i, k -> new HashMap<>());
				posCounts.computeIfAbsent(chars[i], k -> new AtomicInteger()).incrementAndGet();
			}
		}

		String part1 = "";
		String part2 = "";

		for (Map<Character, AtomicInteger> c : counts.values()) {
			List<Character> cc = new ArrayList<>(c.keySet());
			Collections.sort(cc, (a, b) -> {
				int countA = c.get(a).get();
				int countB = c.get(b).get();

				return countA < countB ? 1 : -1;
			});
			part1 += cc.get(0);
			part2 += cc.get(c.size() - 1);
		}

		System.out.println("Part1: " + part1);
		System.out.println("Part2: " + part2);
	}
}
