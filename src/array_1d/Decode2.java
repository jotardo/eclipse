package array_1d;

import java.util.Arrays;

public class Decode2 {
	private static char[] ogChar = new char[26];
	private static char[] tiltedChar = new char[26];
	private static int k = 3;
	
	public static void main(String[] args) {
		for (int i = 0; i < 26; i++) {
			ogChar[i] = (char) ('A' + i);
			tiltedChar[i] = (char) ('A' + (i + k) % 26);
		}
		System.out.println(Arrays.toString(ogChar));
		System.out.println(Arrays.toString(tiltedChar));
		System.out.println("Encode :: " + encode("VENI, VIDI, VICI"));
		System.out.println("Decode :: " + decode("YHQL, YLGL, YLFL"));
	}
	
	public static String encode(String str) {
		if (str == null)
			return "";
		StringBuilder sb = new StringBuilder(str.length());
		for (int i = 0; i < str.length(); i++)
			if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z')
				sb.append(tiltedChar[str.charAt(i) - 'A']);
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
				sb.append(ogChar[(str.charAt(i) - 'A' + 26 - k) % 26]);
			else
				sb.append(str.charAt(i));
		return sb.toString();
	}
}
