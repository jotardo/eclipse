package not_paint;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;
import java.util.*;

@SuppressWarnings("serial")
public class PaintPanel extends JPanel implements MouseListener, MouseMotionListener{

	private static final String RECTANGLE = "Rect";
	private static final String ROUNDED_RECTANGLE = "Rect_Round";
	private static final String OVAL = "Oval";
	private static final String BRUSH = "Brush_oval";
	private static final String LINE = "Line";
	
	private ArrayList<ShapeData> shapeArr;
	private ShapeData latestShapeData;
	private Point mouseAnchorPt;
	private Point mousePt;
	private int shapeMode;
	private int fillMode;
	private Color color1, color2;
	private boolean isRender;
	public PaintPanel(int type, int fillMode) {
		super(true);
		setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		this.shapeMode = type;
		this.fillMode = fillMode;
		this.color1 = Color.BLACK;
		this.color2 = Color.WHITE;
		this.isRender = false;
		this.latestShapeData = new ShapeData();
		this.shapeArr = new ArrayList<ShapeData>();
		this.mousePt = new Point();
		this.mouseAnchorPt = new Point();
	}
	
	void setShapeType(int mode) {
		this.shapeMode = mode;
	}
	
	public void setDrawMode(int mode) {
		this.fillMode = mode;
	}
	
	public void setLineColor(Color color) {
		this.color1 = color;
	}
	
	public void setFillColor(Color color) {
		this.color2 = color;
	}

	public void setMousePosition(Point point) {
		if (point == null)
			return;
		mousePt.setLocation(point);
	}

	public void setMouseAnchorPosition(Point point) {
		if (point == null)
			return;
		mouseAnchorPt.setLocation(point);
	}
	
	public void clearDrawing() {
		latestShapeData = new ShapeData();
		shapeArr.clear();
		this.mousePt = new Point();
		this.mouseAnchorPt = new Point();
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//redraw older sprite
		ShapeData instance;
		for (int i = 0; i < shapeArr.size(); i++) {
			instance = shapeArr.get(i);
			drawGraphic(g, instance);
		}
		if (!isRender)
			return;
		//drawing new shape
		int width = mousePt.x - mouseAnchorPt.x;
		int height = mousePt.y - mouseAnchorPt.y;
		String shapeID = null;
		switch (this.shapeMode) {
			case Paint.RECTANGLE_MODE: shapeID = RECTANGLE;	break;
			case Paint.ROUNDED_RECTANGLE_MODE: shapeID = ROUNDED_RECTANGLE; break;
			case Paint.OVAL_MODE: shapeID = OVAL; break;
			case Paint.BRUSH_MODE: shapeID = BRUSH;	break;
			case Paint.LINE_MODE: shapeID = LINE; break;
		}
		if (this.shapeMode != Paint.BRUSH_MODE && this.shapeMode != Paint.LINE_MODE) {
			if (width < 0 && height >= 0)
				latestShapeData.replaceData(shapeID, mousePt.x, mouseAnchorPt.y, abs(width), abs(height), color1, color2, fillMode);
			else if (width < 0 && height < 0)
				latestShapeData.replaceData(shapeID, mousePt.x, mousePt.y, abs(width), abs(height), color1, color2, fillMode);
			else if (width >= 0 && height < 0)
				latestShapeData.replaceData(shapeID, mouseAnchorPt.x, mousePt.y, abs(width), abs(height), color1, color2, fillMode);
			else
				latestShapeData.replaceData(shapeID, mouseAnchorPt.x, mouseAnchorPt.y, abs(width), abs(height), color1, color2, fillMode);
		}
		else if (this.shapeMode == Paint.BRUSH_MODE)
			latestShapeData.replaceData(BRUSH, mousePt.x, mousePt.y, 0, 0, color1, color2, fillMode);
		else if (this.shapeMode == Paint.LINE_MODE)
			latestShapeData.replaceData(LINE, mouseAnchorPt.x, mouseAnchorPt.y, mousePt.x, mousePt.y, color1, color2, fillMode);
		drawGraphic(g, latestShapeData);
	}

	private void drawGraphic(Graphics g, ShapeData shapeData) {
		if (shapeData.shape == null)
			return;
		switch (shapeData.mode) {
		case Paint.FILL_ONLY:
			g.setColor(shapeData.colorFill);
			if (shapeData.shape.equals(RECTANGLE))
				g.fillRect(shapeData.x, shapeData.y, shapeData.width, shapeData.height);
			else if (shapeData.shape.equals(ROUNDED_RECTANGLE))
				g.fillRoundRect(shapeData.x, shapeData.y, shapeData.width, shapeData.height, shapeData.width / 5, shapeData.height / 5);
			else if (shapeData.shape.equals(OVAL))
				g.fillOval(shapeData.x, shapeData.y, shapeData.width, shapeData.height);
			break;
		case Paint.LINE_FILL:
			g.setColor(shapeData.colorFill);
			if (shapeData.shape.equals(RECTANGLE))
				g.fillRect(shapeData.x, shapeData.y, shapeData.width, shapeData.height);
			else if (shapeData.shape.equals(ROUNDED_RECTANGLE))
				g.fillRoundRect(shapeData.x, shapeData.y, shapeData.width, shapeData.height, (shapeData.width) / 5, (shapeData.height) / 5);
			else if (shapeData.shape.equals(OVAL))
				g.fillOval(shapeData.x, shapeData.y, shapeData.width, shapeData.height);
		case Paint.LINE_ONLY:
			g.setColor(shapeData.colorLine);
			if (shapeData.shape.equals(RECTANGLE))
				g.drawRect(shapeData.x, shapeData.y, shapeData.width, shapeData.height);
			else if (shapeData.shape.equals(ROUNDED_RECTANGLE))
				g.drawRoundRect(shapeData.x, shapeData.y, shapeData.width, shapeData.height, shapeData.width / 5, shapeData.height / 5);
			else if (shapeData.shape.equals(OVAL))
				g.drawOval(shapeData.x, shapeData.y, shapeData.width, shapeData.height);
			break;
		default:
		}
		g.setColor(shapeData.colorLine);
		if (shapeData.shape.equals(BRUSH)) {
			g.fillOval(shapeData.x - 4, shapeData.y - 4, 8, 8);
		}
		else if (shapeData.shape.equals(LINE)) {
			g.drawLine(shapeData.x, shapeData.y, shapeData.width, shapeData.height);
		}
	}

	public void endDraw() {
		if (latestShapeData != null && latestShapeData.shape != null) {
			shapeArr.add(latestShapeData);
			latestShapeData = new ShapeData();
		}
	}
	
	private int abs(int a) {
		return a >= 0 ? a : -a;
	}
	

	class ShapeData {
		String shape;
		int x, y, width, height;
		Color colorLine, colorFill;
		int mode;
		
		public ShapeData() {
			this.shape = null;
			this.x = 0;
			this.y = 0;
			this.width = 0;
			this.height = 0;
			this.colorLine = null;
			this.colorFill = null;
			this.mode = 0;
		}
		
		ShapeData replaceData(String shape, int x, int y, int width, int height, Color color1, Color color2, int mode) {
			this.shape = shape;
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
			this.colorLine = color1;
			this.colorFill = color2;
			this.mode = mode;
			return this;
		}

	}

	void makeEvent() {
		addMouseMotionListener(this);
		addMouseListener(this);
	}
	void killEvent() {
		removeMouseMotionListener(this);
		removeMouseListener(this);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		setMousePosition(e.getPoint());
		repaint();
		if (shapeMode == Paint.BRUSH_MODE) {
			endDraw();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {		
	}

	@Override
	public void mouseClicked(MouseEvent e) {		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		this.isRender = true;
		setMouseAnchorPosition(e.getPoint());
		if (this.shapeMode == Paint.BRUSH_MODE)
			endDraw();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.isRender = false;
		endDraw();
	}

	@Override
	public void mouseEntered(MouseEvent e) {		
	}

	@Override
	public void mouseExited(MouseEvent e) {		
	}

}
