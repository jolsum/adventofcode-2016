package lars.adventofcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Day19 {

	public static void main(String[] args) {

		System.out.println("Test: " + part1(5));
		System.out.println("Part 1: " + part1(3014387));
	}

	private static int part1(int elves) {
		List<Integer> elvesWithPresents = new ArrayList<>();
		for (int i = 1; i <= elves; i++) {
			elvesWithPresents.add(i);
		}

		Iterator<Integer> it = elvesWithPresents.iterator();
		while (it.hasNext() && elvesWithPresents.size() > 1) {

			it.next();

			// Reached end?
			if (!it.hasNext()) {
				it = elvesWithPresents.iterator();
			}

			it.next();
			it.remove();

			// Reached end?
			if (!it.hasNext()) {
				it = elvesWithPresents.iterator();
			}

		}

		return elvesWithPresents.get(0);
	}

}
