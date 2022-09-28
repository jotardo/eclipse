package recursion;

public class Fibonacci {

	public static String doFibonacci(int num) {
		if (num <= 0)
			return "";
		return createFibonacci(1, num, 0, 0);
	}

	private static String createFibonacci(int count, int num, int lastSum, int newSum) {
		String result = "";
		if (count > num)
			return "";
		if (count == 1) { //lan nhap dau thi day so giong nhu (0,1)
			lastSum = 0;
			newSum = 1;
		}
		else { //lan nhap sau thif day so giong nhu (0,1,1,2,3,...)
			int tmp = newSum;
			newSum = newSum + lastSum;
			lastSum = tmp;
		}
		return result += newSum + " " + createFibonacci(count+1, num, lastSum, newSum);
	}
	
	public static void main(String[] args) {
		System.out.println(doFibonacci(10));
	}
}
