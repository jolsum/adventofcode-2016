package lars.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Day12 {

	private static final Map<String, AtomicInteger> registers = new HashMap<>();

	public static void main(String[] args) throws IOException {

		List<String> lines = Files.readAllLines(Paths.get("input", "12.txt"));

		for (int i = 0; i < lines.size(); i++) {

			String[] parts = lines.get(i).split(" ");

			switch (parts[0]) {

				case "cpy":
					int value = getValue(parts[1]);
					getRegisterValue(parts[2]).set(value);
					break;

				case "inc":
					getRegisterValue(parts[1]).incrementAndGet();
					break;

				case "dec":
					getRegisterValue(parts[1]).decrementAndGet();
					break;

				case "jnz":
					if (getValue(parts[1]) != 0) {
						i += Integer.parseInt(parts[2]) - 1;
					}
					break;
			}

		}

		System.out.println("Part 1: " + registers.get("a").get());

	}

	private static AtomicInteger getRegisterValue(String register) {
		return registers.computeIfAbsent(register, i -> new AtomicInteger());
	}

	private static int getValue(String intOrRegister) {
		try {
			return Integer.parseInt(intOrRegister);
		}
		catch (NumberFormatException e) {
			return getRegisterValue(intOrRegister).get();
		}
	}

}
