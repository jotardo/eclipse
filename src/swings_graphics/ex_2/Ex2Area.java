package swings_graphics.ex_2;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

class Ex2Area extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private int[][] arrays;
	private int pyramidHeight = 50; //giá trị để đặt

	public Ex2Area() {
		super();
		arrays = makePyramid(pyramidHeight);
//		for (int i = 0; i < arrays.length; i++)
//			System.out.println(Arrays.toString(arrays[i])); //xuất mảng trên console		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(new Font("Arial", Font.BOLD, 15)); //font chữ mới
		int fontSize = g.getFont().getSize();
		int xStart = 15;
		int yStart = 30;
		int xSpacing = 15;
		int ySpacing = 15;
		int x = xStart;
		int y = yStart;
		for (int i = 0; i < arrays.length; i++) {
			x = xStart;
			for (int j = 0; j < arrays[i].length; j++) {
				g.drawString(arrays[i][j] + "", x, y);
				x += xSpacing + fontSize / 2 * (String.valueOf(arrays[i][j]).length() - 1);
			}
			y += ySpacing;
			if (y > g.getClipBounds().height)
				break;
		}
	}
	
	private int[][] makePyramid(int height) {
		if (height < 0)
			return new int[0][0]; //tránh trường hợp độ dài âm
		
		int[][] result = new int[height][];
		for (int i = 0; i < result.length; i++) {
			result[i] = new int[i+1];
			for (int j = 0; j < result[i].length; j++) {
				result[i][j] = j + 1;
			}
		}
		return result;
	}
}