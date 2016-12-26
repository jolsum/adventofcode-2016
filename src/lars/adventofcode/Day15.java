package lars.adventofcode;

import java.util.Arrays;
import java.util.List;

public class Day15 {

	public static void main(String[] args) {

		// List<Disc> discs = Arrays.asList(
		// new Disc(5, 4),
		// new Disc(2, 1));

		List<Disc> discs = Arrays.asList(
				new Disc(13, 11),
				new Disc(5, 0),
				new Disc(17, 11),
				new Disc(3, 0),
				new Disc(7, 2),
				new Disc(19, 17));

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
