package recursion;

public class DecBinConversion {

	public static int decToBinNonRecursive(int number) throws UnsupportedOperationException{
		if (number < 0)
			throw new UnsupportedOperationException("Not yet implemented");
		int result = 0, i = 0;
		while (number != 0) {
			result = result + (number % 2) * ((int) Math.pow(10, i));
			number = number / 2;
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println("0 = " + decToBinNonRecursive(0));
		System.out.println("1 = " + decToBinNonRecursive(1));
		System.out.println("2 = " + decToBinNonRecursive(2));
	}
}
