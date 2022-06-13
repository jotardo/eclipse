package swings.ex_14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;

class SVData {

	static final int MALE = 0;
	static final int FEMALE = 1;
	String name = "";
	int day = 1, month = 1, year = 1980;
	int gender = -1;
	int[] hobby = { 0, 0, 0, 0 };

	public SVData() {
	}

	public SVData(String name, int day, int month, int year, int gender, int[] hobby) {
		this.name = name;
		this.day = day;
		this.month = month;
		this.year = year;
		this.gender = gender;
		this.hobby = hobby;
	}
	
	
	
	void importData(String src) {
		String[] tmp;
		BufferedReader a = new BufferedReader(new StringReader(src));
		try {
			this.name = a.readLine().substring(4+1);
			this.day = Integer.valueOf(a.readLine().substring(3+1));
			this.month = Integer.valueOf(a.readLine().substring(5+1));
			this.year = Integer.valueOf(a.readLine().substring(4+1));
			this.gender = Integer.valueOf(a.readLine().substring(6+1));
			tmp = a.readLine().substring(5+1+1, 7+10).split(", ");
			for (int i = 0; i < tmp.length; i++) {
				this.hobby[i] = Integer.valueOf(tmp[i]);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return "name=" + name + "\nday=" + day + "\nmonth=" + month
				+ "\nyear=" + year + "\ngender=" + gender + "\nhobby=" + Arrays.toString(hobby);
	}

	static void exportData(String target) {
		
	}
	
	

}
