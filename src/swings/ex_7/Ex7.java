package swings.ex_7;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Ex7 extends JFrame {

	private static final long serialVersionUID = 1L;

	public Ex7() {
		super("List Demo");
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setSize(500,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		String[] colorStrArr = { "Black", "Blue", "Cyan", "Dark Gray", "Gray", "Green", "Light Gray", "Magenta",
				"Orange", "Pink", "Red", "White", "Yellow" };
		Color[] colorArr = { Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN,
				Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW };
		JList<String> colorList = new JList<>(colorStrArr);
		colorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		colorList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int index = colorList.getSelectedIndex();
				getContentPane().setBackground(colorArr[index]);				
			}
		});
		add(colorList);
		setVisible(true);
	}

}
