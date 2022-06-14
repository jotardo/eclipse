package oop;

public class Rectangle {

	private double width = 1;
	private double height = 1;
	
	public Rectangle() {
	}
	
	public Rectangle(double width, double height) {
		this.width = width;
		this.height = height;
	}
	
	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public double getArea() {
		return this.width * this.height;
	}
	
	public double getPerimeter() {
		return (this.width + this.height) * 2;
	}
}
