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
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ButtonUI;
import javax.swing.text.FlowView;

class ContentPane extends APane implements ActionListener{

	private static final int TIME_MAX = 25 * 60;
	private static final long serialVersionUID = 1L;
	
	private JTextArea txtArea;
	private JButton nextQuestBtn, prevQuestBtn, endTimer, startTimer;
	private ButtonGroup btnGr = new ButtonGroup();
	private JLabel timer = new JLabel();
	private Thread timerThread = new TimeTicker(TIME_MAX, timer);
	private JToggleButton[] toggleArr;
	
	public ContentPane() {
		super();
		setLayout(new BorderLayout());
		initContent(45);
	}

	private void initContent(int hgap) {
		JPanel btnPanel = new JPanel();
		Dimension d = new Dimension(150, 50);
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.PAGE_AXIS));
		btnPanel.setBorder(BorderFactory.createEtchedBorder());
		JPanel fieldPane = new JPanel();
		initFieldPane(fieldPane);
		btnPanel.add(fieldPane);
		btnPanel.add(Box.createGlue());
		JPanel gridPane = new JPanel(new GridLayout(5, 5));
		initButtonPane(gridPane);
		btnPanel.add(gridPane);
		btnPanel.add(Box.createGlue());
		//section question
		btnPanel.add(nextQuestBtn = new JButton("Câu kế tiếp"));
		btnPanel.add(prevQuestBtn = new JButton("Câu trước"));
		//section timer
		btnPanel.add(Box.createGlue());
		btnPanel.add(timer);
		JProgressBar b;
		btnPanel.add(b = new JProgressBar(JProgressBar.HORIZONTAL));
		b.setBorder(new BevelBorder(BevelBorder.LOWERED));
		btnPanel.add(startTimer = new JButton("Bắt đầu"));
		btnPanel.add(endTimer = new JButton("Nộp bài"));
		add(btnPanel, BorderLayout.WEST);
		JPanel question = new JPanel();
		add(question, BorderLayout.CENTER);
		initQuestion(question);
		add(new JPanel(), BorderLayout.NORTH);
		add(new JPanel(), BorderLayout.SOUTH);
		add(new JPanel(), BorderLayout.EAST);
		
		gridPane.setBorder(BorderFactory.createTitledBorder("Bảng các câu hỏi"));
		gridPane.setMaximumSize(new Dimension(gridPane.getPreferredSize().width, 30));
		nextQuestBtn.setMaximumSize(d);
		nextQuestBtn.setAlignmentX(CENTER_ALIGNMENT);
		prevQuestBtn.setMaximumSize(d);
		prevQuestBtn.setAlignmentX(CENTER_ALIGNMENT);
		endTimer.setMaximumSize(d);
		endTimer.setAlignmentX(CENTER_ALIGNMENT);
		endTimer.setFont(endTimer.getFont().deriveFont(Font.BOLD, 15f));
		endTimer.setEnabled(false);
		endTimer.setForeground(Color.red);
		startTimer.setFont(startTimer.getFont().deriveFont(Font.BOLD, 15f));
		startTimer.setMaximumSize(d);
		startTimer.setAlignmentX(CENTER_ALIGNMENT);
		timer.setAlignmentX(CENTER_ALIGNMENT);
	}

	private void initFieldPane(JPanel field) {
		field.setBorder(BorderFactory.createTitledBorder(BorderFactory.createDashedBorder(null), "Đáp án đã chọn"));
		field.setLayout(new GridLayout(5, 10));
		for (int i = 0; i < 25; i++) {
			field.add(new JLabel(String.format("%2d.", i)));
			field.add(new JLabel());
		}
	}

	private void initButtonPane(JPanel gridBtn) {
		toggleArr = new JToggleButton[25];
		for (int i = 0; i < toggleArr.length; i++) {
			toggleArr[i] = new JToggleButton(i+"");
			toggleArr[i].addActionListener(this);
			gridBtn.add(toggleArr[i]);
			btnGr.add(toggleArr[i]);
		}
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

	@Override
	public void makeEvents() {
		startTimer.addActionListener(this);
		endTimer.addActionListener(this);
	}

	@Override
	public void killEvents() {
		startTimer.removeActionListener(this);
		endTimer.removeActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startTimer) {
			timerThread = new TimeTicker(TIME_MAX, timer);
			timerThread.start();
			endTimer.setEnabled(true);
			startTimer.setEnabled(false);
			
		}
		else if (e.getSource() == endTimer) {
			int a = JOptionPane.showConfirmDialog(this, "Do you want to nop bai?", "Nop bai", JOptionPane.YES_NO_OPTION);
			if (a == JOptionPane.YES_OPTION) {
				if (!timerThread.isInterrupted()) {
					timerThread.interrupt();
				}
				endTimer.setEnabled(false);
				startTimer.setEnabled(true);
			}
		}
		else
			System.out.println(e.getActionCommand());
	}

}
