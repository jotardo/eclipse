package swings_graphics.ex_2;

import javax.swing.JFrame;

class Ex2 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Ex2() {
		super();
		setSize(600, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
//		setExtendedState(MAXIMIZED_BOTH); //zoom màn hình rộng hơn
		add(new Ex2Area());
		setVisible(true);
	}

	public static void main(String[] args) {
		new Ex2();
	}

}
