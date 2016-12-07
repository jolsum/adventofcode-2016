package lars.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Day2 {

	public static void main(String[] args) throws IOException {

		int[][] keyPad = {
				{ 0, 0, 1, 0, 0 },
				{ 0, 2, 3, 4, 0 },
				{ 5, 6, 7, 8, 9 },
				{ 0, 0xA, 0xB, 0xC, 0 },
				{ 0, 0, 0xD, 0, 0 }
		};

		// int[][] keyPad = {
		// { 1, 2, 3 },
		// { 4, 5, 6 },
		// { 7, 8, 9 }
		// };

		int startX = 0;
		int startY = 0;

		for (int i = 0; i < keyPad.length; i++) {
			for (int j = 0; j < keyPad[0].length; j++) {
				if (keyPad[i][j] == 5) {
					startX = i;
					startY = j;
				}
			}
			System.out.println(Arrays.toString(keyPad[i]));
		}
		int posX = startX;
		int posY = startY;

		for (String line : Files.readAllLines(Paths.get("input", "2.txt"))) {

			for (int i = 0; i < line.length(); i++) {

				int newX = posX;
				int newY = posY;

				char a = line.charAt(i);
				if (a == 'R') {
					newY++;
				} else if (a == 'L') {
					newY--;
				} else if (a == 'U') {
					newX--;
				} else if (a == 'D') {
					newX++;
				}

				if (newX >= 0 && newX < keyPad.length &&
						newY >= 0 && newY < keyPad[0].length &&
						keyPad[newX][newY] != 0) {
					posX = newX;
					posY = newY;
				}

			}

			System.out.print(keyPad[posX][posY] + " ");
		}

	}

}
