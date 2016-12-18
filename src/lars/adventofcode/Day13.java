package lars.adventofcode;

import org.apache.commons.lang3.StringUtils;

public class Day13 {

	private static final int FAVORITE_NUMBER = 10;

	public static void main(String[] args) {

		for (int y = 0; y < 7; y++) {
			for (int x = 0; x < 10; x++) {
				System.out.print(isWall(x, y) ? "#" : ".");
			}
			System.out.println();
		}

	}

	private static boolean isWall(int x, int y) {
		int val = (x * x + 3 * x + 2 * x * y + y + y * y);
		val += FAVORITE_NUMBER;
		String binaryString = Integer.toBinaryString(val);
		int ones = StringUtils.countMatches(binaryString, '1');
		return ones % 2 == 1;
	}

}
