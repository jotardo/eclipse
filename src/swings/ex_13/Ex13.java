package swings.ex_13;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

class Calculator {

	public Calculator() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Java Calculator");
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setSize(300, 225);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		initContent(frame);
		frame.setVisible(true);
		
		
	}

	private static void initContent(JFrame frame) {
		if (frame == null)
			return;
		frame.add(new JTextField(), BorderLayout.NORTH);
		frame.add(new JPanel());
		JPanel buttonHolder;
		frame.add(buttonHolder = new JPanel(), BorderLayout.SOUTH);
		buttonHolder.setLayout(new BoxLayout(buttonHolder, BoxLayout.Y_AXIS));
		JPanel theWeirdOne = new JPanel();
		JPanel a;
		theWeirdOne.add(new JButton("Backspace"));
		theWeirdOne.add(a = new JPanel());
		theWeirdOne.add(new JButton("CE"));
		theWeirdOne.add(new JButton("C"));
		buttonHolder.add(theWeirdOne);
		
		JPanel gridBtn = new JPanel(new GridLayout(4, 4, 3, 3));
		gridBtn.add(new JButton("7"));
		gridBtn.add(new JButton("8"));
		gridBtn.add(new JButton("9"));
		gridBtn.add(new JButton("+"));
		gridBtn.add(new JButton("4"));
		gridBtn.add(new JButton("5"));
		gridBtn.add(new JButton("6"));
		gridBtn.add(new JButton("-"));
		gridBtn.add(new JButton("1"));
		gridBtn.add(new JButton("2"));
		gridBtn.add(new JButton("3"));
		gridBtn.add(new JButton("*"));
		gridBtn.add(new JButton("0"));
		gridBtn.add(new JButton("."));
		gridBtn.add(new JButton("="));
		gridBtn.add(new JButton("/"));
		gridBtn.setPreferredSize(new Dimension(gridBtn.getPreferredSize().width + 70, gridBtn.getPreferredSize().height));
		buttonHolder.add(gridBtn);

		System.out.println(theWeirdOne.getPreferredSize());
		theWeirdOne.setBackground(Color.BLACK);
		theWeirdOne.setPreferredSize(new Dimension(buttonHolder.getPreferredSize().width, theWeirdOne.getPreferredSize().height));
		int widthRemain = theWeirdOne.getPreferredSize().width - theWeirdOne.getComponent(0).getPreferredSize().width - theWeirdOne.getComponent(2).getPreferredSize().width - theWeirdOne.getComponent(3).getPreferredSize().width;
		a.setPreferredSize(new Dimension(widthRemain, a.getPreferredSize().height));
		System.out.println(theWeirdOne.getPreferredSize());
	}

}
