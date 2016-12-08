package lars.adventofcode;

import org.apache.commons.codec.digest.DigestUtils;

public class Day5 {

	public static void main(String[] args) {

		String input = "ojvtpuvg";

		int found = 0;
		for (int i = 0;; i++) {
			String md5 = DigestUtils.md5Hex(input + i);
			if (md5.startsWith("00000")) {
				System.out.print(md5.charAt(5));
				if (++found == 8) {
					break;
				}
			}
		}

	}

}
