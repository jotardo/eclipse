package swings;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import swings.ex_1.Ex1;
import swings.ex_12.Ex12;
import swings.ex_13.Ex13;
import swings.ex_14.SVGUI;
import swings.ex_2.Ex2;
import swings.ex_3.Ex3;
import swings.ex_4.Ex4;
import swings.ex_5.Ex5;
import swings.ex_6.Ex6;
import swings.ex_7.Ex7;
import swings.ex_8.Ex8;
import swings.ex_9.Ex9;

class SwingTest implements ActionListener{
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		JFrame frame = new JFrame("Swings Exercise");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		frame.add(Box.createVerticalGlue());
		JLabel tmpLabel;
		frame.add(tmpLabel = new JLabel("Choose a program to open"));
		frame.add(Box.createVerticalStrut(10));
		tmpLabel.setFont(tmpLabel.getFont().deriveFont(Font.BOLD, 25f));
		tmpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		frame.add(exGrid());
		frame.add(Box.createVerticalGlue());
		frame.setSize(400, 240);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private static JPanel exGrid() {
		JPanel result = new JPanel(new GridLayout(3, 5));
		JButton tmp = null;
		for (int i = 0; i < 15; i++) {
			result.add(tmp = new JButton("Ex " + (i+1)));
			tmp.setFont(tmp.getFont().deriveFont(15f));
			tmp.addActionListener(new SwingTest());
			if (i == 7-1 || i == 10-1 || i == 11-1 || i == 15-1) {
				tmp.setForeground(Color.GRAY);
				tmp.setEnabled(false);
			}
		}
		result.setMaximumSize(result.getPreferredSize());
		return result;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int num = Integer.valueOf(e.getActionCommand().substring("Ex ".length(), e.getActionCommand().length()));
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		switch (num) {
			case 1:	new Ex1(); break;
			case 2:	new Ex2(); break;
			case 3:	new Ex3(); break;
			case 4:	new Ex4(); break;
			case 5:	new Ex5(); break;
			case 6: new Ex6(); break;
			case 7:	new Ex7(); break;
			case 8: new Ex8(); break;
			case 9: new Ex9(); break;
			case 12: new Ex12(); break;
			case 13: new Ex13(); break;
			case 14: new SVGUI(); break;
			default: 
		}
	}
}
