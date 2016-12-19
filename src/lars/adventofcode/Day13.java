package lars.adventofcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class Day13 {

	static enum Direction {
		UP, DOWN, LEFT, RIGHT
	}

	private static final int FAVORITE_NUMBER = 1358;
	private static final Location START = new Location(1, 1);
	private static final Location GOAL = new Location(31, 39);

	private static int lowest = Integer.MAX_VALUE;

	public static void main(String[] args) {

		bfs(GOAL, START, Collections.emptySet(), Collections.emptyList());

	}

	private static void bfs(Location goal, Location current, Set<Location> visited, List<Location> path) {

		visited = new HashSet<>(visited);
		visited.add(current);

		path = new ArrayList<>(path);
		path.add(current);

		if (current.equals(goal)) {
			System.out.println("Reached goal in " + (path.size() - 1) + " steps: " + path);
			lowest = Math.min(lowest, path.size() - 1);
			return;
		}

		// Already found a better path
		if (path.size() > lowest) {
			return;
		}

		for (Direction direction : Direction.values()) {

			Location newLocation = current.move(direction);

			// New location is a wall or a place we have already visited
			if (isWall(newLocation) || visited.contains(newLocation)) {
				continue;
			}

			bfs(goal, newLocation, visited, path);
		}

	}

	private static boolean isWall(Location location) {
		return isWall(location.x, location.y) || location.x < 0 || location.y < 0;
	}

	private static boolean isWall(int x, int y) {
		int val = (x * x + 3 * x + 2 * x * y + y + y * y);
		val += FAVORITE_NUMBER;
		String binaryString = Integer.toBinaryString(val);
		int ones = StringUtils.countMatches(binaryString, '1');
		return ones % 2 == 1;
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
			case UP:
				return new Location(x, y + 1);
			case DOWN:
				return new Location(x, y - 1);
			case LEFT:
				return new Location(x - 1, y);
			case RIGHT:
				return new Location(x + 1, y);
			default:
				throw new IllegalArgumentException();
			}
		}

		@Override
		public int hashCode() {
			return 11 * x + 31 * y;
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
