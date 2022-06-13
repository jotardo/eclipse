package swings_graphics.ex_4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

//Ex4Draw:: Màn vẽ, nơi hình vẽ đc vẽ
// - Cái này hơi lằng nhằng kkk
// - chỉ vẽ đc theo hướng từ Tây Bắc đến �?ông Nam
class Ex4Draw extends JPanel {

	private static final long serialVersionUID = 1L;

	//tui tạo lớp con MouseStuff để chịu trách nhiệm quản lí chuột mà ko add
	//lựa ch�?n lằng nhằng nào khác
	private MouseAdapter mouseAdpt = new MouseStuff();
	
	//biến này dùng để lưu trữ màu, sẽ nói kĩ khi đến paintComponent();
	private Color color;
	
	//t�?a độ điểm gốc
	private Point mousePt1;
	
	//t�?a độ điểm di chuyển (aka nơi chuột drag)
	private Point mousePt2;
	
	//nhóm dữ liệu các hình đã đc vẽ
	//bao gồm (t�?a độ x, t�?a độ y, chi�?u rộng, chi�?u cao & màu của nó)
	private ArrayList<RectangleData> rectData;
	
	private RectangleData latestData;

	public Ex4Draw() {
		super();
		setDoubleBuffered(true);	//Double Buffered để vẽ "trơn tru" hơn
		addMouseListener(mouseAdpt);	//MouseListener cho Press, Released
		addMouseMotionListener(mouseAdpt);	//MouseMotionListener cho Drag
		
		this.color = Color.black;	//Ở đây tui để mặc định là màu đen
		this.mousePt1 = new Point();	//tạo lập các thứ
		this.mousePt2 = new Point();
		this.rectData = new ArrayList<>();
		this.latestData = new RectangleData();
	}

	//Phương thức này dùng khi đổi màu vẽ, khi đó màu hiện tại sẽ là màu mới đc tạo
	void setDrawColor(Color c) {
		if (c == null)
			return;
		this.color = c;
	}

	//Phương thức này dùng khi muốn xóa, khi đó m�?i thứ sẽ trống
	void clearDrawing() {
		this.mousePt1 = new Point();
		this.mousePt2 = new Point();
		rectData.clear();
		repaint();
		System.gc(); //garbage collector: xoá những thành phần ko đc tham chiếu
	}

	//AAAAAAAAAAAAAAAAAAAAA le sauce
	@Override
	protected void paintComponent(Graphics g) {
		//Phương thức này dùng để làm trống màn vẽ
		super.paintComponent(g);
		
		//Gi�? ta vẽ lại các hình cũ
		//bằng cách g�?i các dữ liệu để đặt màu bằng g.setColor(color) & drawRect
		for (RectangleData rect : rectData) {
			g.setColor(rect.color);
			g.drawRect(rect.x, rect.y, rect.width, rect.height);
		}

		//Sau đó, nó sẽ vẽ cái mới
		//bằng cách đặt màu là màu gốc của lớp này (cái this.color ó)
		//và vẽ rect với 2 t�?a độ điểm 1 và điểm 2
		g.setColor(this.color);
		if (mousePt2.x < mousePt1.x)
			if (mousePt2.y < mousePt1.y)
				latestData.replaceData(mousePt2.x, mousePt2.y, mousePt1.x - mousePt2.x, mousePt1.y - mousePt2.y, color);
			else
				latestData.replaceData(mousePt2.x, mousePt1.y, mousePt1.x - mousePt2.x, mousePt2.y - mousePt1.y, color);
		else
			if (mousePt2.y < mousePt1.y)
				latestData.replaceData(mousePt1.x, mousePt2.y, mousePt2.x - mousePt1.x, mousePt1.y - mousePt2.y, color);
			else
				latestData.replaceData(mousePt1.x, mousePt1.y, mousePt2.x - mousePt1.x, mousePt2.y - mousePt1.y, color);
		g.drawRect(latestData.x, latestData.y, latestData.width, latestData.height);
	}

	/*Lớp này phục vụ cho chuột*/
	private class MouseStuff extends MouseAdapter {
		
		/*Khi ấn chuột, nó thả mousePressed event trước
		 * nó sẽ đặt t�?a độ gốc là vị trí bạn ấn*/
		@Override
		public void mousePressed(MouseEvent e) {
			super.mousePressed(e);
			mousePt1.setLocation(e.getPoint());
		}
		
		/*Sau đó bạn kéo, mouseDragged event được thả
		 * nó sẽ cập nhật con tr�? tại vị trí của nó,
		 * đồng th�?i, màn vẽ sẽ được vẽ lại để cập nhật hình hiện tại*/
		@Override
		public void mouseDragged(MouseEvent e) {
			super.mouseDragged(e);
			mousePt2.setLocation(e.getPoint());
			repaint();
		}

		
		/*Cuối cùng bạn thả chuột, mouseReleased event được thả
		 * nó sẽ chốt hình bạn đã vẽ, bằng cách đưa dữ liệu ta vẽ được vào
		 * nhóm rectData, để khi vẽ lại nó sẽ còn theo cách tui code paintComponent*/
		@Override
		public void mouseReleased(MouseEvent e) {
			super.mouseReleased(e);
			rectData.add(latestData);
			latestData = new RectangleData();
		}
	}

	/*Lớp này dùng để khởi tạo dữ liệu hình đã vẽ*/
	private class RectangleData {

		private int x=0, y=0, width=0, height=0;
		private Color color;

		public RectangleData() {
		}
		
		void replaceData(int x, int y, int width, int height, Color color) {
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
			this.color = color;
		}

	}

}
