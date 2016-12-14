package lars.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day9 {

	public static void main(String[] args) throws IOException {

		List<String> lines = Files.readAllLines(Paths.get("input", "9.txt"));

		System.out.println("Part 1");
		lines.forEach(l -> System.out.println(decompress(l, false)));
		
		System.out.println("Part 2");
		lines.forEach(l -> System.out.println(decompress(l, true)));
	}

	public static long decompress(String line, boolean decompressData) {
		int pos = line.indexOf('(');

		if (pos == -1) {
			return line.length();
		}

		long length = pos;
		while (true) {
			int xPos = line.indexOf('x', pos);
			int endPos = line.indexOf(')', xPos) + 1;

			int sequenceLength = Integer.parseInt(line.substring(pos + 1, xPos));
			int times = Integer.parseInt(line.substring(xPos + 1, endPos - 1));

			pos = endPos + sequenceLength;
			
			String sequence = line.substring(endPos, pos);
			
			long realSequenceLength = sequenceLength;
			if (decompressData) {
				realSequenceLength = decompress(sequence, true);
			}
			
			length += (realSequenceLength * times);


			int newPos = line.indexOf('(', pos);
			
			if (newPos == -1) {
				length += line.length() - pos;
				break;
			} 
			else {
				length += newPos - pos;
			}
			pos = newPos;
		}

		return length;
	}

}
