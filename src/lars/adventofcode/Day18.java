package lars.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.lang3.StringUtils;

public class Day18 {

	public static void main(String[] args) throws IOException {

		String line = Files.readAllLines(Paths.get("input", "18.txt")).get(0);
		int rows = 40;

		int safeTiles = countSafeTiles(line);
		for (int i = 1; i < rows; i++) {
			String newLine = "";
			for (int j = 0; j < line.length(); j++) {

				boolean left = j > 0 && line.charAt(j - 1) == '^';
				boolean centre = line.charAt(j) == '^';
				boolean right = j < line.length() - 1 && line.charAt(j + 1) == '^';

				boolean trap = isTrap(left, centre, right);

				newLine += trap ? '^' : '.';
			}
			safeTiles += countSafeTiles(newLine);
			line = newLine;
		}

		System.out.println("Safe tiles: " + safeTiles);
	}

	private static boolean isTrap(boolean left, boolean centre, boolean right) {
		if (left && centre && !right) {
			return true;
		}
		if (centre && right && !left) {
			return true;
		}
		if (left && !centre && !right) {
			return true;
		}
		if (right && !centre && !left) {
			return true;
		}

		return false;
	}

	private static int countSafeTiles(String line) {
		return StringUtils.countMatches(line, '.');
	}

}
