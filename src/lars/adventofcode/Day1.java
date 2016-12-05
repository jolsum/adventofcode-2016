package lars.adventofcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day1 {

	public static void main(String[] args) throws FileNotFoundException {

		int direction = 0;
		int lat = 0;
		int lon = 0;

		try (Scanner s = new Scanner(new File("input", "1.txt"))) {
			s.useDelimiter(", ");
			while (s.hasNext()) {
				String op = s.next().trim();
				String turn = op.substring(0, 1);
				int count = Integer.parseInt(op.substring(1));

				direction += turn.equals("R") ? 1 : -1;
				direction %= 4;

				if (direction == 0) {
					lat += count;
				} else if (direction == 1) {
					lon += count;
				} else if (direction == 2) {
					lat -= count;
				} else if (direction == 3) {
					lon -= count;
				}

				System.out.println(op + " -> " + lat + ":" + lon + " " + direction);
			}

			System.out.println(lat + " " + lon + " -> " + (Math.abs(lat) + Math.abs(lon)));
		}
	}

}
