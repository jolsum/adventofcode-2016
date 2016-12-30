package lars.adventofcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Day19 {

	public static void main(String[] args) {

		// System.out.println("Test: " + part1(5));
		// System.out.println("Part 1: " + part1(3014387));

		System.out.println("Test: " + part2(5));
		System.out.println("Part 2: " + part2(3014387));
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

	private static int part2(int elves) {

		List<Integer> elvesWithPresents = new ArrayList<>();
		for (int i = 1; i <= elves; i++) {
			elvesWithPresents.add(i);
		}

		for (int i = 0; elvesWithPresents.size() > 1;) {
			Integer elf = elvesWithPresents.get(i);

			int victimIndex = (i + elvesWithPresents.size() / 2) % elvesWithPresents.size();

			Integer victim = elvesWithPresents.remove(victimIndex);
			// System.out.println(elf + " steals from " + victim + " > " +
			// elvesWithPresents);

			if (victimIndex > i + 1) {
				i++;
			}

			if (i > elvesWithPresents.size() - 1) {
				i = 0;
				System.out.println(elvesWithPresents.size());
			}

		}

		return elvesWithPresents.get(0);
	}
}
