package lars.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Day10 {

	public static void main(String[] args) throws IOException {

		List<String> lines = Files.readAllLines(Paths.get("input", "10_test.txt"));

	}

	private static abstract class ChipReceiver {

		final int id;
		final SortedSet<Integer> chips = new TreeSet<>();

		public ChipReceiver(int id) {
			this.id = id;
		}

		abstract void receive(Integer chip);

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
				lowTarget.receive(chips.first());
				highTarget.receive(chips.last());
			}
			chips.clear();
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
