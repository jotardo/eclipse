package swings.ex_14;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

class SVDataUtils {
	
	private static StringBuffer sb = new StringBuffer();
	
	static String encode(String input, String key) {
		sb.replace(0, sb.length(), input);
		for (int i = input.length() - 1; i >= 0; i--) {
			sb.setCharAt(i, (char) (sb.charAt(i) - key.toCharArray()[0]));
			sb.insert(i, key);
		}
		return sb.toString();
	}
	
	static String decode(String input, String key) {
		sb.replace(0, sb.length(), input);
		for (int i = 0; i < sb.length(); i++) {
			sb.delete(i, i + key.length());
			sb.setCharAt(i, (char) (sb.charAt(i) + key.toCharArray()[0]));
		}
		return sb.toString();
	}
	
	static void saveFile(String url, SVData data) {
		BufferedWriter bWriter;
		File newFile = new File(url);
		String a = encode(data.toString(), "%");
		System.out.println(a);
		if (!newFile.isFile())
			try {
				newFile.createNewFile();
				bWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile)));
				bWriter.write(a);
				bWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	static void openFile(String url, SVData target) {
		BufferedReader bReader;
		File newFile = new File(url);
		StringBuffer sb = new StringBuffer();
		try {
			bReader = new BufferedReader(new FileReader(newFile));
			int a = 0;
			while ((a = bReader.read()) != -1) {
				sb.append((char) a);
			}
			bReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		target.importData(decode(sb.toString(), "%"));
	}

}