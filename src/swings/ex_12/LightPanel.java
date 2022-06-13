package swings.ex_12;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

class LightPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private boolean state;
	private Color color;
	
	public LightPanel(Color color) {
		super();
		this.state = false;
		this.color = color;
		setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	}
	
	void setLight(boolean option) {
		this.state = option;
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Rectangle rect = g.getClipBounds();
		int circleDiameter = (int) rect.getHeight() - 25;
		g.setColor(color);
		if (!this.state) {
			g.setColor(Color.DARK_GRAY);
		}
		g.fillOval((int) rect.getCenterX() - circleDiameter / 2, (int) rect.getCenterY() - circleDiameter / 2, circleDiameter, circleDiameter);
		g.setColor(Color.black);
		g.drawOval((int) rect.getCenterX() - circleDiameter / 2, (int) rect.getCenterY() - circleDiameter / 2, circleDiameter, circleDiameter);
	}
	
	@Override
	public Dimension getPreferredSize() {
		// TODO Auto-generated method stub
		Dimension tmp = super.getPreferredSize();
		return new Dimension(tmp.width, tmp.height + 100);
	}
}
