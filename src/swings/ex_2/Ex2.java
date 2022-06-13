package swings.ex_2;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

class Ex2 {

	public static void main(String[] args) {
		JFrame frame = new JFrame("The front view of a Microwave Oven");
		frame.setLayout(new GridLayout(1, 2));
		frame.setSize(640, 380);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		JButton leftSide = new JButton("Food to be placed here");
		frame.add(leftSide);
		
		JPanel rightPanel = new JPanel(new BorderLayout());
		JPanel buttonPanel = new JPanel(new GridLayout(4, 3));
		rightPanel.add(new JTextField("Time to be displayed here"), BorderLayout.NORTH);
		rightPanel.add(buttonPanel, BorderLayout.CENTER);
		for (int i = 1; i < 10; i++)
			buttonPanel.add(new JButton(i + ""));
		buttonPanel.add(new JButton("0"));
		buttonPanel.add(new JButton("Start"));
		buttonPanel.add(new JButton("Stop"));
		frame.add(rightPanel);
		
		frame.setVisible(true);
	}

}
