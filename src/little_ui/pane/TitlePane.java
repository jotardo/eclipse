package little_ui.pane;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class TitlePane extends APane implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JButton btn;
	
	public TitlePane() {
		super();
		setBackground(Color.red);
		add(btn = new JButton("HAHAAHAHHAHAHAHA"));
	}

	@Override
	public void makeEvents() {
		btn.addActionListener(this);
	}

	@Override
	public void killEvents() {
		btn.removeActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn) {
			parent.showResultPane();
		}
	}

}
