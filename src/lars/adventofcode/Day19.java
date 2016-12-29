package lars.adventofcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Day19 {

	public static void main(String[] args) {

		int elves = 3014387;

		List<Integer> elvesWithPresents = new ArrayList<>();
		for (int i = 1; i <= elves; i++) {
			elvesWithPresents.add(i);
		}

		Iterator<Integer> it = elvesWithPresents.iterator();
		while (it.hasNext() && elvesWithPresents.size() > 1) {
			Integer elf = it.next();

			// Reached end?
			if (!it.hasNext()) {
				// System.out.println("Reached end for " + elf);
				it = elvesWithPresents.iterator();
			}

			Integer victim = it.next();
			// System.out.println(elf + " steals from " + victim + " (" +
			// elvesWithPresents.size() + ")");
			it.remove();

			// Reached end?
			if (!it.hasNext()) {
				// System.out.println("Reached end for " + elf);
				it = elvesWithPresents.iterator();
			}

			if (elvesWithPresents.size() % 1000 == 0) {
				System.out.println(elvesWithPresents.size());
			}
		}

		System.out.println(elvesWithPresents);
	}

}
