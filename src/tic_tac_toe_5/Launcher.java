package tic_tac_toe_5;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.UnsupportedLookAndFeelException;

public class Launcher {
	
	private static StringBuilder sb = new StringBuilder();
	private static JLabel status = new JLabel();
	private static JLabel notification = new JLabel();
	private static GridBoard gridBoard;
	
	public Launcher() throws UnsupportedLookAndFeelException {		
		JFrame frame = new JFrame("X-O");
		JScrollPane playPane = new JScrollPane(gridBoard = new GridBoard(100));
		frame.setSize(1024, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(status, BorderLayout.NORTH);
		frame.add(playPane, BorderLayout.CENTER);
		frame.add(notification, BorderLayout.SOUTH);
		frame.setVisible(true);
		
		updateDisplay();
	}
	
	static void updateDisplay() {
		if (status.getHorizontalAlignment() != JLabel.CENTER)
			status.setHorizontalAlignment(JLabel.CENTER);
		sb.delete(0, sb.length());
		sb.append("Turn ").append(gridBoard.getTurn()).append('-').append("State being X: ").append(gridBoard.isX());
		status.setText(sb.toString());
	}
	
	static void notify(String str) {
		if (str == null)
			return;
		
		notification.setText(str);
		Timer timer = new Timer(5000, null);
		timer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				timer.stop();
				notification.setText(null);				
			}
		});
		timer.start();
	}
}
