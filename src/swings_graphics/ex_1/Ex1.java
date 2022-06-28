package swings_graphics.ex_1;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Ex1 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Ex1(int gridNum) {
		super("Grid test");
		setSize(640,360);
		setLayout(new GridLayout(gridNum, gridNum));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		JPanel tmp;
		for (int i = 0; i < Math.pow(gridNum, 2d); i++) {
			add(tmp = new JPanel());
			tmp.setBorder(BorderFactory.createLineBorder(Color.black));
			if (i % 2 != 0) {
				tmp.setBackground(Color.BLACK);
			}
		}
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Ex1(7);
	}

}
