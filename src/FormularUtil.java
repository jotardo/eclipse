import java.util.Random;

public class FormularUtil {
	public static double getAreaByHeronFormula(double a, double b, double c) throws IllegalArgumentException {
		if (a + b <= c || a + c <= b || b + c <= a)
			throw new IllegalArgumentException("Input value is not edge of triangle");
		double halfC = (a + b + c) * 0.5;
		double result = Math.sqrt(halfC * (halfC - a) * (halfC - b) * (halfC - c));
		return result;
	}
	
	public static String captchaCreate(int length) throws IllegalArgumentException {
		if (length < 4 || length > 8)
			throw new IllegalArgumentException("Captcha must be from 4-8 letters");
		char[] captcha = new char[length];
		int limit_A = 'A';
		int limit_a = 'a';
		int limit_0 = '0';
		Random r = new Random();
		for (int i = 0; i < captcha.length; i++) {
			switch (r.nextInt(3)) {
			case 0:
				captcha[i] = (char) (limit_A + r.nextInt(26));
				break;
			case 1:
				captcha[i] = (char) (limit_a + r.nextInt(26));
				break;
			case 2:
				captcha[i] = (char) (limit_0 + r.nextInt(10));
				break;
			};
		};
		return new String(captcha);
	}
}
