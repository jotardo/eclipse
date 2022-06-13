package not_paint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;

public class CustomColorPane extends JPanel implements AdjustmentListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel realPanel = new JPanel();
	private JScrollBar r, g, b;
	private JTextField rTxt, gTxt, bTxt;
	JButton customColorBtn;

	public CustomColorPane() {
		super();
		// setPreferredSize(new Dimension(500, 500));
		setLayout(new BorderLayout());
		initContent();
		setVisible(true);
	}

	private void initContent() {
		realPanel.setLayout(new GridBagLayout());

		JLabel red, green, blue;
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		realPanel.add(red=new JLabel("R "), gbc);
		red.setForeground(Color.RED);
		gbc.gridy++;
		realPanel.add(green=new JLabel("G "), gbc);
		green.setForeground(Color.green);
		gbc.gridy++;
		realPanel.add(blue=new JLabel("B "), gbc);
		blue.setForeground(Color.blue);

		gbc.gridx++;
		gbc.gridy = 0;
		gbc.ipadx = 40;
		realPanel.add(r = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 256), gbc);
		gbc.gridy++;
		realPanel.add(g = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 256), gbc);
		gbc.gridy++;
		realPanel.add(b = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 256), gbc);

		gbc.gridx++;
		gbc.gridy = 0;
		gbc.ipadx = 0;
		realPanel.add(rTxt = new JTextField(3), gbc);
		gbc.gridy++;
		realPanel.add(gTxt = new JTextField(3), gbc);
		gbc.gridy++;
		realPanel.add(bTxt = new JTextField(3), gbc);

		gbc.gridx++;
		gbc.gridy = 0;
		gbc.gridheight = 3;
		gbc.ipady = 45;
		realPanel.add(customColorBtn = new JButton(), gbc);

		r.addAdjustmentListener(this);
		rTxt.setText("0");
		rTxt.addKeyListener(this);
		g.addAdjustmentListener(this);
		gTxt.setText("0");
		gTxt.addKeyListener(this);
		b.addAdjustmentListener(this);
		bTxt.setText("0");
		bTxt.addKeyListener(this);
		r.setValue(255);
		g.setValue(255);
		b.setValue(255);

		add(realPanel);
	}

//	public static void main(String[] args) {
//		javax.swing.JFrame frame = new javax.swing.JFrame("AA");
//		frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
//		frame.setLayout(new BorderLayout());
//		frame.add(new CustomColorPane());
//		frame.pack();
//		frame.setVisible(true);
//	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		if (e != null) {
			int num = e.getValue() > 255 ? 255 : e.getValue();
			String txt = num + "";
			if (e.getSource().equals(r)) {
				rTxt.setText(txt);
			}
			if (e.getSource().equals(g)) {
				gTxt.setText(txt);
			}
			if (e.getSource().equals(b)) {
				bTxt.setText(txt);
			}
		}
		customColorBtn.setBackground(new Color(Integer.valueOf(rTxt.getText()), Integer.valueOf(gTxt.getText()),
				Integer.valueOf(bTxt.getText())));
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(250, 70);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


}
