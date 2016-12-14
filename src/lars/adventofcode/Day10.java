package lars.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class Day10 {

	private static final Map<Integer, Bot> bots = new HashMap<>();
	private static final Map<Integer, Output> outputs = new HashMap<>();

	public static void main(String[] args) throws IOException {

		List<String> lines = Files.readAllLines(Paths.get("input", "10.txt"));

		for (String line : lines) {
			String[] parts = line.split(" ");
			if (line.startsWith("bot")) {
				int botId = Integer.parseInt(parts[1]);
				Bot bot = bots.computeIfAbsent(botId, Bot::new);
				bot.lowTarget = getReceiver(parts[5], parts[6]);
				bot.highTarget = getReceiver(parts[10], parts[11]);
			}
		}

		for (String line : lines) {
			String[] parts = line.split(" ");
			if (line.startsWith("value")) {
				int chip = Integer.parseInt(parts[1]);
				int bot = Integer.parseInt(parts[5]);
				bots.computeIfAbsent(bot, Bot::new).receive(chip);
			}
		}

		int product = outputs.get(0).chips.first() *
				outputs.get(1).chips.first() *
				outputs.get(2).chips.first();

		System.out.println("Part 2: " + product);
	}

	private static ChipReceiver getReceiver(String botOrOutput, String num) {
		int id = Integer.parseInt(num);

		if ("bot".equals(botOrOutput)) {
			return bots.computeIfAbsent(id, Bot::new);
		}
		else if ("output".equals(botOrOutput)) {
			return outputs.computeIfAbsent(id, Output::new);
		}
		throw new IllegalArgumentException("Unknown receiver type: " + botOrOutput);
	}

	private static abstract class ChipReceiver {

		final int id;
		final SortedSet<Integer> chips = new TreeSet<>();

		public ChipReceiver(int id) {
			this.id = id;
		}

		abstract void receive(Integer chip);

		@Override
		public String toString() {
			return getClass().getSimpleName() + " " + id;
		}
	}

	private static class Bot extends ChipReceiver {

		ChipReceiver lowTarget;
		ChipReceiver highTarget;

		public Bot(int id) {
			super(id);
		}

		@Override
		public void receive(Integer chip) {
			chips.add(chip);

			if (chips.size() == 2) {
				if (chips.first() == 17 && chips.last() == 61) {
					System.out.println("Part 1: " + id);
				}

				give(chips.first(), lowTarget);
				give(chips.last(), highTarget);
			}
		}

		private void give(Integer chip, ChipReceiver receiver) {
			chips.remove(chip);
			receiver.receive(chip);
		}

	}

	private static class Output extends ChipReceiver {

		public Output(int id) {
			super(id);
		}

		@Override
		public void receive(Integer chip) {
			chips.add(chip);
		}

	}

}
