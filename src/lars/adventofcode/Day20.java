package lars.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Day20 {

	public static void main(String[] args) throws IOException {

		List<Range> blackList = loadBlackList(Paths.get("input", "20.txt"));

		System.out.println("Part 1: " + getLowestNonBlocked(blackList));
		System.out.println("Part 2: " + countNonBlocked(blackList));
	}

	private static long getLowestNonBlocked(List<Range> blackList) {

		long highestBlocked = 0;
		for (Range r : blackList) {

			if (r.from > highestBlocked + 1) {
				return highestBlocked + 1;
			}

			highestBlocked = Math.max(highestBlocked, r.to);
		}

		return -1;
	}

	private static long countNonBlocked(List<Range> blackList) {

		long highestBlocked = 0;
		long nonBlocked = 0;
		for (Range r : blackList) {

			if (r.from > highestBlocked + 1) {
				nonBlocked += r.from - highestBlocked - 1;
			}

			highestBlocked = Math.max(highestBlocked, r.to);
		}

		return nonBlocked;
	}

	private static List<Range> loadBlackList(Path path) throws IOException {

		List<Range> ret = Files.readAllLines(path)
				.stream()
				.map(Day20.Range::fromLine)
				.sorted(Day20::byFrom)
				.collect(Collectors.toList());

		return ret;
	}

	private static int byFrom(Range a, Range b) {
		return Long.compare(a.from, b.from);
	}

	private static class Range {
		long from;
		long to;

		static Range fromLine(String line) {

			Range r = new Range();
			int pos = line.indexOf('-');
			r.from = Long.parseLong(line.substring(0, pos));
			r.to = Long.parseLong(line.substring(pos + 1));

			return r;
		}

	}

}
