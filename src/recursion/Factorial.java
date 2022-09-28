package recursion;

public class Factorial {

	public static long factorial(long num) {
		if (num == 0)
			return 1l;
		return num * factorial(num - 1);
	}
	
	public static long decToBin(long number) {
		if (number == 1)
			return 1;
		return decToBin(number / 2) * 10 + number % 2;
	}
	
	public static void main(String[] args) {
		System.out.println("Factorial = " + factorial(5));
		System.out.println("4 to bin = " + decToBin(2000));

	}

}
