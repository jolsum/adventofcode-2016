package lars.adventofcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day1 {

	public static void main(String[] args) throws FileNotFoundException {

		Set<String> visited = new HashSet<>();

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
				direction = direction == -1 ? 3 : direction;

				int i = lat;
				int j = lon;

				if (direction == 0) {
					lat += count;
				}
				else if (direction == 1) {
					lon += count;
				}
				else if (direction == 2) {
					lat -= count;
				}
				else if (direction == 3) {
					lon -= count;
				}

				while (i != lat || j != lon) {
					if (i < lat) {
						i++;
					}
					else if (i > lat) {
						i--;
					}

					if (j < lon) {
						j++;
					}
					else if (j > lon) {
						j--;
					}
					String pos = i + ":" + j;

					boolean ok = visited.add(pos);
					if (!ok) {
						System.out.println(pos + " -> " + (Math.abs(i) + Math.abs(j)));
						return;
					}

				}

			}

			System.out.println(lat + " " + lon + " -> " + (Math.abs(lat) + Math.abs(lon)));
		}
	}

}
