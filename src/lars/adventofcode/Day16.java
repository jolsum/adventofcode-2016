package lars.adventofcode;

public class Day16 {

	public static void main(String[] args) {

		System.out.println("Test: " + checkSum(fill(20, "10000")));
		System.out.println("Part 1: " + checkSum(fill(272, "10001001100000001")));
		System.out.println("Part 2: " + checkSum(fill(35651584, "10001001100000001")));

	}

	private static CharSequence fill(int diskSize, String input) {

		StringBuilder diskData = new StringBuilder(input);
		while (diskData.length() < diskSize) {
			StringBuilder reversed = new StringBuilder(diskData).reverse();
			diskData.append("0");
			reversed.chars().map(Day16::swap).forEach(diskData::append);
		}

		diskData.setLength(diskSize);

		return diskData;
	}

	private static int swap(int in) {
		return in == '1' ? 0 : 1;
	}

	private static CharSequence checkSum(CharSequence in) {

		StringBuilder out = new StringBuilder();
		for (int i = 0; i < in.length(); i += 2) {
			CharSequence sub = in.subSequence(i, i + 2);
			out.append(("00".equals(sub) || "11".equals(sub)) ? 1 : 0);
		}

		return out.length() % 2 == 0 ? checkSum(out) : out;
	}

}
