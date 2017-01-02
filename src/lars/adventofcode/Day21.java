package lars.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;

public class Day21 {

	public static void main(String[] args) throws IOException {

		System.out.println("Test: " + doOperations("abcde", Paths.get("input", "21_test.txt")));
		System.out.println("Part 1: " + doOperations("abcdefgh", Paths.get("input", "21.txt")));

	}

	public static String doOperations(String string, Path file) throws IOException {

		for (String line : Files.readAllLines(file)) {
			string = getFunction(line).apply(string);
		}

		return string;
	}

	private static Function<String, String> getFunction(String line) {
		String[] parts = line.split(" ");

		if (parts[0].equals("swap")) {
			if (parts[1].equals("position")) {
				return l -> swapPosition(l, Integer.parseInt(parts[2]), Integer.parseInt(parts[5]));
			}
			else if (parts[1].equals("letter")) {
				return l -> l.replace(parts[2], "|").replace(parts[5], parts[2]).replace("|", parts[5]);
			}
		}

		if (parts[0].equals("rotate")) {
			if (parts[1].equals("based")) {
				return l -> rotateBased(l, parts[6]);
			}
			else {
				int offset = Integer.parseInt(parts[2]);
				int i = offset * (parts[1].equals("left") ? 1 : -1);
				return l -> rotate(l, i);
			}
		}

		if (parts[0].equals("reverse")) {
			return l -> reverse(l, Integer.parseInt(parts[2]), Integer.parseInt(parts[4]));
		}

		if (parts[0].equals("move")) {
			return l -> movePosition(l, Integer.parseInt(parts[2]), Integer.parseInt(parts[5]));
		}

		return l -> l;
	}

	private static String swapPosition(String string, int x, int y) {
		char xc = string.charAt(x);
		char yc = string.charAt(y);

		return new StringBuilder(string).replace(x, x + 1, Character.toString(yc))
				.replace(y, y + 1, Character.toString(xc)).toString();
	}

	private static String reverse(String string, int from, int to) {

		String reversed = new StringBuilder(string.subSequence(from, to + 1)).reverse().toString();

		return string.substring(0, from) + reversed + string.substring(to + 1);
	}

	private static String movePosition(String string, int from, int to) {
		String charFrom = Character.toString(string.charAt(from));
		string = string.replace(charFrom, "");
		return string.substring(0, to) + charFrom + string.substring(to);
	}

	private static String rotate(String s, int offset) {

		int i = offset;
		while (i < 0) {
			i += s.length();
		}
		i %= s.length();

		return s.substring(i) + s.substring(0, i);
	}

	private static String rotateBased(String s, String on) {
		int i = s.indexOf(on);

		i += i >= 4 ? 2 : 1;

		return rotate(s, -i);
	}

}
