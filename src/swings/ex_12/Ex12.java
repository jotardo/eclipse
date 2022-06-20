package swings.ex_12;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

public class Ex12 extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JLabel redInfo, greenInfo, yellowInfo;
	private JRadioButton redBtn, greenBtn, yellowBtn;
	private final String red = "- Đỏ", green = "- Xanh", yellow = "- Vàng";
	private LightPanel redLight, greenLight, yellowLight;

	public Ex12() {
		super("Traffic Light Program 2");
		setSize(640,360);
		//setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		initContent();
		setVisible(true);
	}

	private void initContent() {
		ButtonGroup btnGrp = new ButtonGroup();
		JPanel panelUp = new JPanel(new GridLayout(3, 1));
		panelUp.setPreferredSize(new Dimension(getContentPane().getWidth(), 10));
		panelUp.setBorder(new TitledBorder("Thay đổi tín hiệu đèn giao thông"));
		panelUp.add(redInfo = new JLabel(red));
		panelUp.add(greenInfo = new JLabel(green));
		panelUp.add(yellowInfo = new JLabel(yellow));
		add(panelUp);
		
		JPanel panelMiddle = new JPanel(new GridLayout(1, 4));
		panelMiddle.add(new JLabel("Chọn loại đèn:"));
		panelMiddle.add(redBtn = new JRadioButton("Đỏ"));
		panelMiddle.add(greenBtn = new JRadioButton("Xanh"));
		panelMiddle.add(yellowBtn = new JRadioButton("Vàng"));
		redBtn.addActionListener(this);
		greenBtn.addActionListener(this);
		yellowBtn.addActionListener(this);
		btnGrp.add(redBtn); btnGrp.add(yellowBtn); btnGrp.add(greenBtn);
		add(panelMiddle);

		JPanel panelDown = new JPanel(new GridLayout(1, 3));
		panelDown.add(redLight = new LightPanel(Color.red));
		panelDown.add(greenLight = new LightPanel(Color.green));
		panelDown.add(yellowLight = new LightPanel(Color.yellow));
		add(panelDown);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String chosenText = ": Bạn chọn màu " + e.getActionCommand().toLowerCase();
		redInfo.setText(red); greenInfo.setText(green); yellowInfo.setText(yellow);
		redLight.setLight(false); greenLight.setLight(false); yellowLight.setLight(false);
		if (e.getSource().equals(redBtn)) {
			redInfo.setText(redInfo.getText().concat(chosenText));
			redLight.setLight(true);
		}
		else if (e.getSource().equals(greenBtn)) {
			greenInfo.setText(greenInfo.getText().concat(chosenText));
			greenLight.setLight(true);
		}
		else if (e.getSource().equals(yellowBtn)) {
			yellowInfo.setText(yellowInfo.getText().concat(chosenText));
			yellowLight.setLight(true);
		}
	}
	
	

}
