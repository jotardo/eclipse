package swings.ex_3;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

class Ex3 extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField text1, text2, textResult;
	private JButton addBtn, subBtn, mulBtn, divBtn;
	private JMenuItem addOption, subOption, mulOption, divOption, exitOption;
	private ImageIcon addIcon = new ImageIcon("res/ex_3/plus.png");
	private ImageIcon subIcon = new ImageIcon("res/ex_3/minus.png");
	private ImageIcon mulIcon = new ImageIcon("res/ex_3/multiplication.png");
	private ImageIcon divIcon = new ImageIcon("res/ex_3/division.png");
	

	public Ex3() {
		super("Calculator with menu");
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initMenuBar();
		initContent();
		pack();
		setVisible(true);
	}

	private void initMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu operMenu = menuBar.add(new JMenu("Operation"));
		operMenu.setMnemonic('O');
		addOption = operMenu.add(new JMenuItem("Add"));
		addOption.setIcon(addIcon);
		addOption.setMnemonic('A');
		addOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		subOption = operMenu.add(new JMenuItem("Subtract"));
		subOption.setIcon(subIcon);
		subOption.setMnemonic('S');
		subOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		mulOption = operMenu.add(new JMenuItem("Multiply"));
		mulOption.setIcon(mulIcon);
		mulOption.setMnemonic('M');
		mulOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
		divOption = operMenu.add(new JMenuItem("Divide"));
		divOption.setIcon(divIcon);
		divOption.setMnemonic('D');
		divOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		
		JMenu exitMenu = menuBar.add(new JMenu("Exit"));
		exitMenu.setMnemonic('E');
		exitOption = exitMenu.add("Exit");
		exitOption.setMnemonic('X');
		exitOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
		setJMenuBar(menuBar);
		
	}
	
	private void initContent() {
		JPanel dataHolder = new JPanel(new FlowLayout(FlowLayout.LEFT));
		dataHolder.add(new JLabel("Number 1"));
		dataHolder.add(text1 = new JTextField(3));
		dataHolder.add(new JLabel("Number 2"));
		dataHolder.add(text2 = new JTextField(3));
		dataHolder.add(new JLabel("Result"));
		dataHolder.add(textResult = new JTextField(6));
		textResult.setEditable(false);
		add(dataHolder);
		
		JPanel buttonHolder = new JPanel(new FlowLayout());
		buttonHolder.add(addBtn = new JButton("Add", addIcon));
		buttonHolder.add(subBtn = new JButton("Subtract", subIcon));
		buttonHolder.add(mulBtn = new JButton("Multiply", mulIcon));
		buttonHolder.add(divBtn = new JButton("Divide", divIcon));
		add(buttonHolder);
		
		addBtn.addActionListener(this);
		subBtn.addActionListener(this);
		mulBtn.addActionListener(this);
		divBtn.addActionListener(this);
		addOption.addActionListener(this);
		subOption.addActionListener(this);
		mulOption.addActionListener(this);
		divOption.addActionListener(this);
		exitOption.addActionListener(this);
	}

	public static void main(String[] args) {
		new Ex3();
	}
	
	private void calculate(char operator) {
		String intPattern = "\\d+|-\\d+";
		if (!(text1.getText().matches(intPattern) && text2.getText().matches(intPattern)))
			return;
		
		float num1 = Float.valueOf(text1.getText());
		float num2 = Float.valueOf(text2.getText());
		float numResult = 0f;
		switch (operator) {
			case '+': numResult = num1 + num2; break;
			case '-': numResult =  num1 - num2; break;
			case '*': numResult =  num1 * num2; break;
			case '/': numResult = num1 / num2; break;
		}
		if (operator == '/')
			textResult.setText(String.format("%.3f", numResult));
		else
			textResult.setText(Math.round(numResult) + "");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(addBtn) || e.getSource().equals(addOption))
			calculate('+');
		else if (e.getSource().equals(subBtn) || e.getSource().equals(subOption))
			calculate('-');
		else if (e.getSource().equals(mulBtn) || e.getSource().equals(mulOption))
			calculate('*');
		else if (e.getSource().equals(divBtn) || e.getSource().equals(divOption))
			calculate('/');
		else if (e.getSource().equals(exitOption))
			dispose();
		
	}
	
	@Override
	public void dispose() {
		addBtn.removeActionListener(this);
		subBtn.removeActionListener(this);
		mulBtn.removeActionListener(this);
		divBtn.removeActionListener(this);
		addOption.removeActionListener(this);
		subOption.removeActionListener(this);
		mulOption.removeActionListener(this);
		divOption.removeActionListener(this);
		exitOption.removeActionListener(this);
		super.dispose();
	}

}
