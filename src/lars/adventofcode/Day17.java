package lars.adventofcode;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;

public class Day17 {

	static enum Direction {
		U, D, L, R
	}

	private static String SHORTEST = null;
	private static String LONGEST = null;

	public static void main(String[] args) {

		bfs("dmypynyp", new Location(3, 3), new Location(0, 0), "");

		System.out.println("Part 1: " + SHORTEST);
		System.out.println("Part 2: " + LONGEST.length());
	}

	private static void bfs(String input, Location goal, Location current, String path) {

		if (current.equals(goal)) {
			if (SHORTEST == null || path.length() < SHORTEST.length()) {
				SHORTEST = path;
			}
			if (LONGEST == null || path.length() > LONGEST.length()) {
				LONGEST = path;
			}
			return;
		}

		String md5 = DigestUtils.md5Hex(input + path);
		Set<Direction> possibleDirections = getPossibleDirections(current, md5);

		for (Direction direction : possibleDirections) {

			Location newLocation = current.move(direction);

			bfs(input, goal, newLocation, path + direction);
		}

	}

	private static Set<Direction> getPossibleDirections(Location location, String md5) {

		Set<Direction> ret = new HashSet<>();
		if (md5.charAt(0) > 'a' && location.y > 0) {
			ret.add(Direction.U);
		}
		if (md5.charAt(1) > 'a' && location.y < 3) {
			ret.add(Direction.D);
		}
		if (md5.charAt(2) > 'a' && location.x > 0) {
			ret.add(Direction.L);
		}
		if (md5.charAt(3) > 'a' && location.x < 3) {
			ret.add(Direction.R);
		}

		return ret;
	}

	private static final class Location {

		final int x;
		final int y;

		public Location(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Location move(Direction direction) {
			switch (direction) {
			case U:
				return new Location(x, y - 1);
			case D:
				return new Location(x, y + 1);
			case L:
				return new Location(x - 1, y);
			case R:
				return new Location(x + 1, y);
			default:
				throw new IllegalArgumentException();
			}
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}

		@Override
		public boolean equals(Object obj) {
			Location other = (Location) obj;
			return other.x == x && other.y == y;
		}

		@Override
		public String toString() {
			return x + ":" + y;
		}
	}

}
