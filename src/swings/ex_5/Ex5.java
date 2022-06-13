package swings.ex_5;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

class Ex5 extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField field_1, field_1_alt, field_2_alt, field_3, field_3_alt;
	private JRadioButton maleBtn, femaleBtn;
	private ButtonGroup gr = new ButtonGroup();

	public Ex5() {
		super("Khảo sát thông tin");
		setLayout(new GridLayout(3, 1));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		initContent(); //�?ây là nơi các thành phần sẽ tạo để kh�?i làm rối contructor
		initEvents(); // �?ây là nơi các event sẽ được cài đặt

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	

	private void initContent() {
		JLabel label_1, label_2, label_3;
		Dimension dim = new Dimension(69, 20);
		int textBoxSize = 15;
		JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel row3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		add(row1); add(row2); add(row3);
		
		row1.add(label_1 = new JLabel("H�? tên"));
		row1.add(field_1 = new JTextField(textBoxSize));
		row1.add(field_1_alt = new JTextField(textBoxSize));
		label_1.setPreferredSize(dim);
		field_1_alt.setEditable(false);
		
		row2.add(label_2 = new JLabel("Nam"));
		row2.add(maleBtn = new JRadioButton());
		row2.add(label_3 = new JLabel("Nữ"));
		row2.add(femaleBtn = new JRadioButton());
		row2.add(field_2_alt = new JTextField(textBoxSize));
		label_2.setPreferredSize(dim);
		label_3.setPreferredSize(dim);
		femaleBtn.setPreferredSize(dim);
		gr.add(maleBtn); gr.add(femaleBtn);
		field_2_alt.setEditable(false);
		
		row3.add(label_3 = new JLabel("Số ti�?n"));
		row3.add(field_3 = new JTextField(textBoxSize));
		row3.add(field_3_alt = new JTextField(textBoxSize));
		label_3.setPreferredSize(dim);
		field_3_alt.setEditable(false);
	}
	
	private void initEvents() {
		field_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					field_1_alt.setText(field_1.getText());
				}
			}
		});
		field_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				String text = null;
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					text = field_3.getText();
					if (!text.matches("\\d+"))
						return;
					field_3_alt.setText(String.format("%,.2f", Double.valueOf(text)));
				}
			}
		});
		maleBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				field_2_alt.setText("Nam");				
			}
		});
		femaleBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				field_2_alt.setText("Nữ");				
			}
		});
	}

	public static void main(String[] args) {
		new Ex5();

	}

}
