package lars.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day8 {

	public static void main(String[] args) throws IOException {
		
		int width = 50;
		int height = 6;
		
		int[][] screen = new int[height][width];
		
		printScreen(screen);
		
		List<String> lines = Files.readAllLines(Paths.get("input", "8.txt"));

		for (String line : lines) {
			
			System.out.println(line);
			
			if (line.startsWith("rect")) {
				int pos = line.indexOf("x");
				int rectW = Integer.parseInt(line.substring(5, pos));
				int rectH = Integer.parseInt(line.substring(pos + 1));
				screen = drawRect(screen, rectW, rectH);
			}
			else if (line.startsWith("rotate column")) {
				int column = Integer.parseInt(line.substring(line.indexOf("=") + 1, line.indexOf(" by ")));
				int amount = Integer.parseInt(line.substring(line.indexOf("by ") + 3));
				
				screen = rotateColumn(screen, column, amount);				
			}
			else if (line.startsWith("rotate row")) {
				int column = Integer.parseInt(line.substring(line.indexOf("=") + 1, line.indexOf(" by ")));
				int amount = Integer.parseInt(line.substring(line.indexOf("by ") + 3));
				
				screen = rotateRow(screen, column, amount);
				
			}
			printScreen(screen);
		}
		
		
		System.out.println("Part 1: " + countPixels(screen));
	}

	private static int countPixels(int[][] screen) {
		int count = 0;
		for (int i = 0; i < screen.length; i++) {
			for (int j = 0; j < screen[i].length; j++) {
				count += screen[i][j];
			}
		}
		return count;
	}
	
	
	private static int[][] drawRect(int[][] screen, int width, int height) {
		
		int[][] newScreen = deepCopyIntMatrix(screen);
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				newScreen[i][j] = 1;
			}
		}
		
		return newScreen;
	}
	
	private static int[][] rotateColumn(int[][] screen, int column, int amount) {
		
		int[][] newScreen = deepCopyIntMatrix(screen);
		for (int i = 0; i < screen.length; i++) {
			int copy = (i + amount) % screen.length;
			newScreen[copy][column] = screen[i][column]; 
		}
		
		return newScreen;
	}
	
	private static int[][] rotateRow(int[][] screen, int row, int amount) {
		
		int[][] newScreen = deepCopyIntMatrix(screen);
		for (int i = 0; i < screen[row].length; i++) {
			int copy = (i + amount) % screen[row].length;
			newScreen[row][copy] = screen[row][i]; 
		}
		
		return newScreen;
	}
	
	public static int[][] deepCopyIntMatrix(int[][] input) {
	    int[][] result = new int[input.length][];
	    for (int r = 0; r < input.length; r++) {
	        result[r] = input[r].clone();
	    }
	    return result;
	}	
	
	private static void printScreen(int[][] screen) {
		for (int i = 0; i < screen.length; i++) {
			for (int j = 0; j < screen[i].length; j++) {
				System.out.print(screen[i][j] == 1 ? "#" : ".");
			}
			System.out.println();
		}
		System.out.println();
	}
	
}
