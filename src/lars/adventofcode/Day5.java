package lars.adventofcode;

import java.util.Arrays;

import org.apache.commons.codec.digest.DigestUtils;

public class Day5 {

	public static void main(String[] args) {

		String input = "ojvtpuvg";

		String part1 = "";
		char[] part2 = new char[8];

		int found = 0;
		for (int i = 0;; i++) {
			String md5 = DigestUtils.md5Hex(input + i);
			if (md5.startsWith("00000")) {
				char char6 = md5.charAt(5);
				char char7 = md5.charAt(6);

				if (part1.length() < 8) {
					part1 += char6;
					System.out.println("Part1: " + part1);
				}

				if (Character.isDigit(char6)) {
					int pos = Integer.parseInt(Character.toString(char6));
					if (pos < 8 && part2[pos] == (char) 0) {
						part2[pos] = char7;
						System.out.println("Part2: " + Arrays.toString(part2));
						if (++found == 8) {
							break;
						}
					}
				}

			}
		}
	}
}
