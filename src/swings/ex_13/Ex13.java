package swings.ex_13;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Ex13 extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public Ex13() {
		super("Java Calculator");
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(225, 225);
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		initContent(this);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
	}

	private static void initContent(JFrame frame) {
		if (frame == null)
			return;
		frame.add(new JTextField(), BorderLayout.NORTH);
		frame.add(Box.createRigidArea(new Dimension(0, 20)));
		JPanel buttonHolder;
		frame.add(buttonHolder = new JPanel(), BorderLayout.SOUTH);
		buttonHolder.setLayout(new BoxLayout(buttonHolder, BoxLayout.Y_AXIS));
		
		JPanel theWeirdOne = new JPanel();
		theWeirdOne.setLayout(new BoxLayout(theWeirdOne, BoxLayout.LINE_AXIS));
		theWeirdOne.add(new JButton("Backspace"));
		theWeirdOne.add(Box.createGlue());
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
		buttonHolder.add(gridBtn);
	}

}
