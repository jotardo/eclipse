package swings.ex_6;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

class Ex6 {
	
	private static final String folderDir = "res/ex_6/";
	private static final String[] fileName = {"cybercore.png", "cyberstatic.png", "cyberwily.png", "bruh.png"};
	private static ImageIcon[] imgIcons;

	public static void main(String[] args) throws IOException {
		imgIcons = new ImageIcon[fileName.length];
		for (int i = 0; i < fileName.length; i++) {
			imgIcons[i] = new ImageIcon(folderDir + fileName[i]);
		}
		
		JFrame frame = new JFrame("Testing JComboBox");
		//frame.setLayout(new BorderLayout());
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		addContent(frame);
		frame.setVisible(true);
	}

	private static void addContent(JFrame frameHolder) {
		if (frameHolder == null)
			return;
		JComboBox<String> comboBox = new JComboBox<>(fileName);
		JLabel imgHolder = new JLabel(imgIcons[0]);
		//JScrollPane tmp = new JScrollPane(imgHolder);
		frameHolder.add(comboBox, BorderLayout.NORTH);
		frameHolder.add(imgHolder);
		//frameHolder.add(tmp);
		
		comboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
					imgHolder.setIcon(imgIcons[comboBox.getSelectedIndex()]);
			}
		});
	}

}
