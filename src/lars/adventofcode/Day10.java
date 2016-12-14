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
	
	
	private interface ChipReceiver {
		
		void receive(Integer chip);
		
	}
	
	
	private static class Bot implements ChipReceiver{
		
		SortedSet<Integer> chips = new TreeSet<>();
		
		ChipReceiver lowTarget;
		ChipReceiver highTarget;
		
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
	
	private static class Output implements ChipReceiver {

		Set<Integer> chips = new HashSet<>();
		
		@Override
		public void receive(Integer chip) {
			chips.add(chip);
		}
		
	}
	
}
