package swings_graphics.ex_4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

class Ex4 extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	//mi�?n để vẽ
	private Ex4Draw drawArea;
	
	//Các option v�? màu, có thể tự thêm màu khác
	private Color[] colors = { Color.BLACK, Color.RED, Color.green,
		Color.blue, Color.orange, Color.YELLOW};
	
	//Mảng chứa các nút (sẽ dùng để tự động hóa tạo nút)
	private JButton[] colorBtn = new JButton[colors.length];
	
	//Nút để xóa
	private JButton clearBtn;
	
	//Border cho nút màu tạo thành
	private Border tmpBorder = BorderFactory.createLineBorder(Color.BLACK);

	public Ex4() {
		super("Rectangle");
		setSize(500, 500);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initContent(); //phần nên để ý kkk
		setVisible(true);
	}

	private void initContent() {
		JPanel paintAndClear; //panel phía dưới
		JButton tmpBtn; //biến chứa nút màu tạm th�?i
		Dimension tmpDim = new Dimension(40, 30); //kích thước các nút màu

		add(new JLabel("Add a rectangle by press, drag and release the mouse", JLabel.CENTER), BorderLayout.NORTH);
		add(drawArea = new Ex4Draw());
		add(paintAndClear = new JPanel(), BorderLayout.SOUTH);
		paintAndClear.setBackground(Color.LIGHT_GRAY);
		
		//tự động hóa tạo các nút màu
		for (int i = 0; i < colorBtn.length; i++) {
			paintAndClear.add(tmpBtn = new JButton());
			tmpBtn.setBorder(tmpBorder);
			tmpBtn.setPreferredSize(tmpDim);
			tmpBtn.setBackground(colors[i]);
			tmpBtn.addActionListener(this); //actionListener ở dưới
			colorBtn[i] = tmpBtn;
		}
		paintAndClear.add(clearBtn = new JButton("Clear Drawing"));
		clearBtn.setPreferredSize(new Dimension(140, tmpDim.height));
		clearBtn.addActionListener(this); //đây cũng vậy
	}

	public static void main(String[] args) {
		new Ex4();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Biến nguồn từ ActionEvent thành JButton
		JButton btn = (JButton) e.getSource();
		
		//Check đk
			//Nút xóa
		if (btn.equals(clearBtn)) {
			System.out.println("A");
			drawArea.clearDrawing(); //xóa màn hình vẽ
		}
			//Các nút màu khác
		else {
				//Reset vi�?n các nút màu
			for (JButton colorBtn : colorBtn) {
				colorBtn.setBorder(tmpBorder);
			}
				//�?ặt border màu đã ch�?n là trống và set màu cho màn vẽ
			btn.setBorder(BorderFactory.createEmptyBorder());
			drawArea.setDrawColor(btn.getBackground());
		}

	}

}
