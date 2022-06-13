package swings.ex_4;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

class Ex4 extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JLabel redInfo, greenInfo, yellowInfo;
	private JRadioButton redBtn, greenBtn, yellowBtn;
	private final String red = "- Đỏ", green = "- Xanh", yellow = "- Vàng";

	public Ex4() {
		super("Traffic Light Program");
		setSize(480,360);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(3, 1));
		initContent();
		setVisible(true);
	}

	private void initContent() {
		ButtonGroup btnGrp = new ButtonGroup();
		JPanel panelUp = new JPanel(new GridLayout(3, 1));
		panelUp.setBorder(new TitledBorder("Thay đổi tín hiệu đèn giao thông"));
		panelUp.add(redInfo = new JLabel(red));
		panelUp.add(greenInfo = new JLabel(green));
		panelUp.add(yellowInfo = new JLabel(yellow));
		add(panelUp);
		
		JPanel panelMiddle = new JPanel(new GridLayout(1, 4));
		add(panelMiddle);
		panelMiddle.add(new JLabel("Chọn loại đèn:"));
		panelMiddle.add(redBtn = new JRadioButton("Đỏ"));
		panelMiddle.add(greenBtn = new JRadioButton("Xanh"));
		panelMiddle.add(yellowBtn = new JRadioButton("Vàng"));
		redBtn.addActionListener(this);
		greenBtn.addActionListener(this);
		yellowBtn.addActionListener(this);
		btnGrp.add(redBtn); btnGrp.add(yellowBtn); btnGrp.add(greenBtn);
	}

	public static void main(String[] args) {
		new Ex4();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String chosenText = ": Bạn chọn màu " + e.getActionCommand().toLowerCase();
		redInfo.setText(red); greenInfo.setText(green); yellowInfo.setText(yellow);
		if (e.getSource().equals(redBtn))
			redInfo.setText(redInfo.getText().concat(chosenText));
		else if (e.getSource().equals(greenBtn))
			greenInfo.setText(greenInfo.getText().concat(chosenText));
		else if (e.getSource().equals(yellowBtn))
			yellowInfo.setText(yellowInfo.getText().concat(chosenText));
	}

}
