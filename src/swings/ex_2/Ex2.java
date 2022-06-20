package swings.ex_2;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Ex2 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Ex2() {
		super("The front view of a Microwave Oven");
		setLayout(new GridLayout(1, 2));
		setSize(640, 380);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JButton leftSide = new JButton("Food to be placed here");
		add(leftSide);
		
		JPanel rightPanel = new JPanel(new BorderLayout());
		JPanel buttonPanel = new JPanel(new GridLayout(4, 3));
		rightPanel.add(new JTextField("Time to be displayed here"), BorderLayout.NORTH);
		rightPanel.add(buttonPanel, BorderLayout.CENTER);
		for (int i = 1; i < 10; i++)
			buttonPanel.add(new JButton(i + ""));
		buttonPanel.add(new JButton("0"));
		buttonPanel.add(new JButton("Start"));
		buttonPanel.add(new JButton("Stop"));
		add(rightPanel);
		setVisible(true);
	}

}
