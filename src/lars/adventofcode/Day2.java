package lars.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Day2 {

    public static void main(String[] args) throws IOException {

	int rows = 3;
	int columns = 3;

	int[][] keyPad = new int[rows][columns];

	int startX = 0;
	int startY = 0;

	int num = 0;
	for (int i = 0; i < rows; i++) {
	    for (int j = 0; j < columns; j++) {
		keyPad[i][j] = ++num;
		if (num == 5) {
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
		char a = line.charAt(i);
		if (a == 'R') {
		    posY++;
		} else if (a == 'L') {
		    posY--;
		} else if (a == 'U') {
		    posX--;
		} else if (a == 'D') {
		    posX++;
		} else {
		    throw new IllegalArgumentException(Character.toString(a));
		}

		posX = Math.max(0, Math.min(posX, rows - 1));
		posY = Math.max(0, Math.min(posY, columns - 1));

	    }

	    System.out.print(keyPad[posX][posY]);
	}

    }

}
