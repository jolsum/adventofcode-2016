package lars.adventofcode;

import java.util.ArrayList;
import java.util.List;

public class Day15 {

	public static void main(String[] args) {

		List<Disc> discs = new ArrayList<>();

		// Part 1
		discs.add(new Disc(13, 11));
		discs.add(new Disc(5, 0));
		discs.add(new Disc(17, 11));
		discs.add(new Disc(3, 0));
		discs.add(new Disc(7, 2));
		discs.add(new Disc(19, 17));

		// Part 2
		discs.add(new Disc(11, 0));

		for (int time = 0;; time++) {

			boolean allOpen = true;
			for (int i = 0; i < discs.size(); i++) {

				Disc disc = discs.get(i);
				int pos = (disc.startPosition + i + 1 + time) % disc.positions;

				if (pos != 0) {
					allOpen = false;
					break;
				}

			}

			if (allOpen) {
				System.out.println("All open at time " + time);
				break;
			}
		}

	}

	private static final class Disc {

		final int positions;
		final int startPosition;

		public Disc(int positions, int position) {
			this.positions = positions;
			this.startPosition = position;
		}
	}

}
