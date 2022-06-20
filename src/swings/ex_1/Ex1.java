package swings.ex_1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Ex1 extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<JButton> operatorList = new ArrayList<>();
	private JButton addBtn, subBtn, mulBtn, divBtn, equalBtn;
	private JTextField text1, text2, textResult;
	
	public Ex1() throws HeadlessException {
		super("Calculator");
		try {
			UIManager.setLookAndFeel(new javax.swing.plaf.nimbus.NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setResizable(false);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initContent();
		setSize(440, 220);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void initContent() {
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		add(container, BorderLayout.CENTER);
		
		JPanel panel1 = new JPanel(new FlowLayout());
		JPanel panelOperator = new JPanel(new FlowLayout(FlowLayout.CENTER,10, 0));
		JPanel panel2 = new JPanel(new FlowLayout()); 
		JPanel panelResult = new JPanel(new FlowLayout());
		
		panel1.add(new JLabel("Số thứ 1"));
		panel1.add(text1 = new JTextField(20));
		panelOperator.add(addBtn = new JButton("+"));
		panelOperator.add(subBtn = new JButton("-"));
		panelOperator.add(mulBtn = new JButton("*"));
		panelOperator.add(divBtn = new JButton("/"));
		panel2.add(new JLabel("Số thứ 2"));
		panel2.add(text2 = new JTextField(20));
		panelResult.add(new JLabel("Kết quả:"));
		panelResult.add(textResult = new JTextField(20));
		textResult.setEditable(false);
		
		container.add(panel1);
		container.add(panelOperator);
		container.add(panel2);
		container.add(equalBtn = new JButton("="));
		container.add(panelResult);
		
		equalBtn.setName("equal");
		operatorList.add(addBtn);
		operatorList.add(subBtn);
		operatorList.add(mulBtn);
		operatorList.add(divBtn);
		operatorList.forEach(btn -> btn.addActionListener(this));
		equalBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn;
		char optionChosen = ' ';
		int num1, num2;
		double numResult;
		for (JButton element: operatorList) {
			if (!(element.isEnabled())) {
				optionChosen = element.getText().charAt(0);
				element.setEnabled(true);
			}
		};
		if (e.getSource().equals(equalBtn)) {
			if (!(text1.getText().matches("\\d+|-\\d+") && text2.getText().matches("\\d+|-\\d+"))) {
				System.out.println("NO!");
				return;
			}
			num1 = Integer.valueOf(text1.getText());
			num2 = Integer.valueOf(text2.getText());
			switch (optionChosen) {
				case '+': numResult = num1 + num2; break;
				case '-': numResult =  num1 - num2; break;
				case '*': numResult =  num1 * num2; break;
				case '/': numResult = num1 / num2; break;
				default: numResult = 0;
			}
			textResult.setText(numResult + "");
		}
		else {
			btn = (JButton) e.getSource();
			btn.setEnabled(false);
		}
	}

}
