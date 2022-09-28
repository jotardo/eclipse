import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FormularUtilTest {

	@Test
	void testGetAreaByHeronFormula() {
		System.out.println("** Get Triangle Area **");
		System.out.println("a=10 b=16.4 c=17.7 :: " + FormularUtil.getAreaByHeronFormula(10, 16.4, 17.7));
		assertEquals(FormularUtil.getAreaByHeronFormula(10, 16.4, 17.7), 80.8, 0.03);

		try {
			System.out.println("a=4 b=2 c=1 :: " + FormularUtil.getAreaByHeronFormula(4, 2, 1));
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Input value is not edge of triangle");
		}
	}

	@Test
	void testCaptchaCreate() {
		System.out.println("** Create Captcha **");
		System.out.println("4 letters :: " + FormularUtil.captchaCreate(4));
		System.out.println("8 letters :: " + FormularUtil.captchaCreate(8));
	}

	@Test
	void testCaptchaCreateError() {
		try {
			System.out.println("3 letters :: " + FormularUtil.captchaCreate(3));
			System.out.println("9 letters :: " + FormularUtil.captchaCreate(9));
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Captcha must be from 4-8 letters");
		}
	}

}
