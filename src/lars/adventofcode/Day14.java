package lars.adventofcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;

public class Day14 {

	public static void main(String[] args) {

		String salt = "qzyelonm";
		Pattern three = Pattern.compile("(.)\\1{2}");

		Map<String, String> cache = new HashMap<>();

		// Part 1:
		Function<String, String> func = DigestUtils::md5Hex;

		// Part 2:
		// Function<String, String> func = Day14::stretchedHash;

		Map<String, List<Integer>> triplets = new HashMap<>();
		int keys = 0;

		for (int i = 0;; i++) {

			String hash = cache.computeIfAbsent(salt + i, func);

			Matcher matcher = three.matcher(hash);
			if (matcher.find()) {
				triplets.computeIfAbsent(matcher.group(1), k -> new ArrayList<>()).add(i);

				Pattern five = Pattern.compile("(" + matcher.group(1) + ")\\1{4}");
				for (int j = 1; j <= 1000; j++) {
					String nHash = cache.computeIfAbsent(salt + (i + j), func);
					Matcher fiveMatcher = five.matcher(nHash);
					if (fiveMatcher.find()) {
						if (++keys == 64) {
							System.out.println(i);
							return;
						}
						break;
					}
				}

			}

		}

	}

	private static String stretchedHash(String in) {
		for (int i = 0; i < 2017; i++) {
			in = DigestUtils.md5Hex(in);
		}
		return in;
	}

}
