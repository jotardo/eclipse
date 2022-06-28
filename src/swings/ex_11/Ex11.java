package swings.ex_11;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Ex11 extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public Ex11() {
		super("Selection Example");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		String[] strArray = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve" };
		JList<String> list = new JList<>(strArray);
		Box box = new Box(BoxLayout.Y_AXIS);
		JCheckBox[] selectedBox = new JCheckBox[strArray.length];
		JCheckBox tmp = null;
		for (int i = 0; i < selectedBox.length; i++) {
			selectedBox[i] = tmp = new JCheckBox("Selection " + i);
			box.add(tmp);
			tmp.setEnabled(false);
			box.add(Box.createGlue());
		}
		add(list, BorderLayout.WEST);
		add(box, BorderLayout.EAST);
		
		setSize(200,400);
		setLocationRelativeTo(null);
		setVisible(true);
		System.out.println(getPreferredSize());
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				for (int i = 0; i < selectedBox.length; i++) {
					selectedBox[i].setSelected(list.isSelectedIndex(i));
				}
			}
		});
	}

}
