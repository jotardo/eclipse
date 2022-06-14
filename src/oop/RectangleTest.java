package oop;

public class RectangleTest {
	public static void main(String[] args) {
		Rectangle rect1 = new Rectangle(4, 40);
		System.out.printf("Rectangle object 1 has :\nWidth : %.2f\nHeight: %.2f\nArea : %.2f\nPerimeter: %.2f\n",
				rect1.getWidth(), rect1.getHeight(), rect1.getArea(), rect1.getPerimeter());
		System.out.println("----------------------------");
		Rectangle rect2 = new Rectangle(3.5d, 35.9d);
		System.out.printf("Rectangle object 2 has :\nWidth : %.2f\nHeight: %.2f\nArea : %.2f\nPerimeter: %.2f",
				rect2.getWidth(), rect2.getHeight(), rect2.getArea(), rect2.getPerimeter());
	}
}
