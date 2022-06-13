package little_ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

class ContentPane extends APane implements ActionListener{

	private static final int TIME_MAX = 25 * 60;
	private static final long serialVersionUID = 1L;
	
	private JTextArea txtArea;
	private JButton btn1, btn2, btn3, btn4;
	private ButtonGroup btnGr = new ButtonGroup();
	private JLabel timer = new JLabel();
	private Thread timerThread = new TimeLabel(TIME_MAX, timer);
	
	public ContentPane() {
		super();
		setLayout(new BorderLayout());
		initMenuBar();
		initContent(45);
	}
	
	private void initMenuBar() {
		JMenuBar a = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu help = new JMenu("Help");
		a.add(file);
		a.add(help);
		parent.setJMenuBar(a);
		
	}

	private void initContent(int hgap) {
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.Y_AXIS));
		btnPanel.setBorder(BorderFactory.createEtchedBorder());
		Dimension d = new Dimension(150, 50);
		btnPanel.add(Box.createVerticalStrut(hgap));
		JPanel field = new JPanel();
		initField(field);
		btnPanel.add(field);
		
		btnPanel.add(Box.createVerticalStrut(hgap));
		JPanel table = new JPanel(new GridLayout(5, 5));
		btnPanel.add(table);
		for (int i = 0; i < 25; i++) {
			btnGr.add((AbstractButton) table.add(new JToggleButton(i+1+"")));
		}
		btnPanel.add(Box.createVerticalStrut(hgap));
		btnPanel.add(btn1 = new JButton("Câu kế tiếp"));
		btnPanel.add(btn2 = new JButton("Câu trước"));
		btnPanel.add(Box.createVerticalStrut(hgap));
		//btnPanel.add(new JSeparator());
		btnPanel.add(timer);
		btnPanel.add(btn4 = new JButton("Bắt đầu"));
		btnPanel.add(btn3 = new JButton("Nộp bài"));
		add(btnPanel, BorderLayout.WEST);
		btnPanel.add(Box.createVerticalStrut(hgap));
		
		JPanel question = new JPanel();
		add(question, BorderLayout.CENTER);
		initQuestion(question);
		add(new JPanel(), BorderLayout.NORTH);
		add(new JPanel(), BorderLayout.SOUTH);
		add(new JPanel(), BorderLayout.EAST);
		
		table.setBorder(BorderFactory.createTitledBorder("Bảng các câu hỏi"));
		table.setMaximumSize(new Dimension(table.getPreferredSize().width, 30));
		btn1.setMaximumSize(d);
		btn1.setAlignmentX(CENTER_ALIGNMENT);
		btn2.setMaximumSize(d);
		btn2.setAlignmentX(CENTER_ALIGNMENT);
		btn3.setMaximumSize(d);
		btn3.setAlignmentX(CENTER_ALIGNMENT);
		btn3.setFont(btn3.getFont().deriveFont(Font.BOLD, 15f));
		btn3.setEnabled(false);
		btn4.setFont(btn4.getFont().deriveFont(Font.BOLD, 15f));
		btn4.setMaximumSize(d);
		btn4.setAlignmentX(CENTER_ALIGNMENT);
		timer.setAlignmentX(CENTER_ALIGNMENT);
	}

	private void initQuestion(JPanel question) {
		Font a = getFont().deriveFont(25f);
		JPanel answerArea;
		JToggleButton b1, b2, b3, b4;
		question.setLayout(new BorderLayout());
		question.add(txtArea = new JTextArea(), BorderLayout.CENTER);
		question.add(answerArea = new JPanel(new FlowLayout(FlowLayout.CENTER, 90, 0)), BorderLayout.SOUTH);
		answerArea.add(b1 = new JToggleButton("A"));
		answerArea.add(b2 = new JToggleButton("B"));
		answerArea.add(b3 = new JToggleButton("C"));
		answerArea.add(b4 = new JToggleButton("D"));
		b1.setFont(a);
		b2.setFont(a);
		b3.setFont(a);
		b4.setFont(a);
		txtArea.setEditable(false);
		
	}

	private void initField(JPanel field) {
		field.setBorder(BorderFactory.createTitledBorder(BorderFactory.createDashedBorder(null), "Đáp án đã chọn"));
		field.setLayout(new GridLayout(5, 10));
		for (int i = 0; i < 25; i++) {
			field.add(new JLabel(String.format("%2d.", i+1)));
			field.add(new JLabel());
		}
	}

	@Override
	public void makeEvents() {
		btn4.addActionListener(this);
		btn3.addActionListener(this);
	}

	@Override
	public void killEvents() {
		btn4.removeActionListener(this);
		btn3.removeActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn4) {
			timerThread = new TimeLabel(TIME_MAX, timer);
			timerThread.start();
			btn3.setEnabled(true);
			btn4.setEnabled(false);
			
		}
		else if (e.getSource() == btn3) {
			int a = JOptionPane.showConfirmDialog(this, "Do you want to nop bai?", "HMMM?", JOptionPane.YES_NO_OPTION);
			if (a == JOptionPane.YES_OPTION) {
				if (!timerThread.isInterrupted()) {
					timerThread.interrupt();
				}
				btn3.setEnabled(false);
				btn4.setEnabled(true);
			}
		}
	}

}
