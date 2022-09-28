package array_1d;


public class Decode {
	
	
	public static void main(String[] args) {
		System.out.println("Encode :: " + encode("VENI, VIDI, VICI"));
		System.out.println("Decode :: " + decode("YHQL, YLGL, YLFL"));
	}
	
	public static String encode(String str) {
		if (str == null)
			return "";
		StringBuilder sb = new StringBuilder(str.length());
		for (int i = 0; i < str.length(); i++)
			if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z')
				sb.append((char) (((str.charAt(i) + 3) % 'A') + 'A'));
			else
				sb.append(str.charAt(i));
		return sb.toString();
	}
	
	public static String decode(String str) {
		if (str == null)
			return "";
		StringBuilder sb = new StringBuilder(str.length());
		for (int i = 0; i < str.length(); i++)
			if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z')
				sb.append((char) (((str.charAt(i) - 3) % 'A') + 'A'));
			else
				sb.append(str.charAt(i));
		return sb.toString();
	}
}
