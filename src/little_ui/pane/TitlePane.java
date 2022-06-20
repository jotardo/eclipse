package little_ui.pane;

import java.awt.Dimension;
import java.awt.Paint;
import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import little_ui.Launcher;

public class TitlePane extends APane implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JButton btn = new JButton("HAHAAHAHHAHAHAHA");
	private JLabel lbl = new JLabel("QUESTION TEST");
	
	public TitlePane() {
		super();
		setSize(640,360);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBorder(BorderFactory.createDashedBorder(null));
		init();
	}

	private void init() {
		JPanel box = new JPanel();
		box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));
		
		add(Box.createGlue());
		add(box);
		add(Box.createGlue());
		
		btn.setAlignmentX(CENTER_ALIGNMENT);
		lbl.setAlignmentX(CENTER_ALIGNMENT);
		box.setMaximumSize(new Dimension(420, box.getMaximumSize().height));
		box.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		box.add(lbl);
		box.add(btn);
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
			Launcher.showContentPane();
		}
	}

}
