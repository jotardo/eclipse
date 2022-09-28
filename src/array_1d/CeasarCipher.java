package array_1d;

public class CeasarCipher {
	
	private static char[] alphabetUppercase = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
//	private static char[] alphabetLowercase = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	
	private static boolean isUppercase(char c) {
		for (char tmp: alphabetUppercase)
			if (c == tmp)
				return true;
		return false;
	}
	
//	private static boolean isLowercase(char c) {
//		for (char tmp: alphabetLowercase)
//			if (c == tmp)
//				return true;
//		return false;
//	}
	
	public static String encrypt(String message, int shiftValue) {
		if (message == null)
			return null;
		StringBuilder sb = new StringBuilder(message.length());
		if (shiftValue < 0)
			shiftValue += 26 * (-shiftValue / 26 + 1);	//return to positive range
		for (char singleChar : message.toCharArray())
			if (isUppercase(singleChar))
				sb.append((char) alphabetUppercase[(singleChar - 'A' + shiftValue) % 26]);
//			else if (isLowercase(singleChar))
//				sb.append((char) alphabetLowercase[(singleChar - 'a' + shiftValue) % 26]);
			else
				sb.append(singleChar);
		return sb.toString();
	}
	
	public static String decrypt(String message, int shiftValue) {
		if (message == null)
			return null;
		StringBuilder sb = new StringBuilder(message.length());
		if (shiftValue < 0)
			shiftValue += 26 * (-shiftValue / 26 + 1);	//return to positive range
		for (char singleChar : message.toCharArray())
			if (isUppercase(singleChar))
				sb.append((char) alphabetUppercase[(singleChar - 'A' - shiftValue + 26) % 26]);
//			else if (isLowercase(singleChar))
//				sb.append((char) alphabetLowercase[(singleChar - 'a' - shiftValue + 26) % 26]);
			else
				sb.append(singleChar);
		return sb.toString();
	}
	
	public static void main(String[] args) {
//		String copypasta = "There is no such thing as a coincidence. The fact that you're watching this video mean you're energetically aligned with me and this message. Your thoughts create your reality. but you already knew that. Yet, you still live a life that you dread. [Oh excuse me- AAAH] That is because when you visualize your dream life, you unconsciously believe that it is unrealistic. Here is a hack: I have created a dream life meditation that uses questions and binaural beats. When presented with the question, your mind must accept it. And your subconscious mind will absorb it. When listening to binaural beats it puts your mind into theta frequency allowing you access to your subconscious mind. Link in the bio.";
		String copypasta = "THE ONE PIECE! THE ONE PIECE IS REEEEAL!!";
		String encodedCopy;
		System.out.println("Encrypted (shift by 7) ::\n".concat(encodedCopy = encrypt(copypasta, 7)));
		System.out.println("Decrypted (shift by 7) ::\n".concat(decrypt(encodedCopy, 7)));
	}

}
