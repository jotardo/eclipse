package little_ui.pane;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import little_ui.util.TimeThread;

public class ContentPane extends APane implements ActionListener {

	private static final int TIME_MAX = 25*60;
	private static final long serialVersionUID = 1L;

	private JButton nextQuestBtn, prevQuestBtn, endTimer, startTimer;
	private ButtonGroup questionBtnGr = new ButtonGroup();
	private ButtonGroup answerGroup = new ButtonGroup();
	private JLabel timer;
	private JProgressBar timerBar;
	private Thread timerThread = null;
	private JToggleButton b1, b2, b3, b4;
	private JToggleButton[] questionArr;
	private JLabel[] answerArr;
	private Border lineBorder = BorderFactory.createStrokeBorder(new BasicStroke(1.5f,BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
	private int currentIndex = 0;
	private JPanel questionArea, choiceArea;
	
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
		// section question
		btnPanel.add(nextQuestBtn = new JButton("Next Question"));
		btnPanel.add(prevQuestBtn = new JButton("Prev Question"));
		// section timer
		btnPanel.add(Box.createGlue());
		btnPanel.add(timer = new JLabel("Time Limit: 25 min"));
		btnPanel.add(timerBar = new JProgressBar(JProgressBar.HORIZONTAL));
		timerBar.setMaximumSize(new Dimension(160, 25));
		timerBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
		btnPanel.add(startTimer = new JButton("Start"));
		btnPanel.add(endTimer = new JButton("Complete"));
		add(btnPanel, BorderLayout.WEST);
		JPanel question = new JPanel();
		add(question, BorderLayout.CENTER);
		initQuestion(question);
		add(new JPanel(), BorderLayout.NORTH);
		add(new JPanel(), BorderLayout.SOUTH);
		add(new JPanel(), BorderLayout.EAST);

		gridPane.setBorder(BorderFactory.createTitledBorder("Question Navigation"));
		gridPane.setMaximumSize(new Dimension(gridPane.getPreferredSize().width, 30));
		nextQuestBtn.setMaximumSize(d);
		nextQuestBtn.setAlignmentX(CENTER_ALIGNMENT);
		prevQuestBtn.setMaximumSize(d);
		prevQuestBtn.setAlignmentX(CENTER_ALIGNMENT);
		startTimer.setFont(startTimer.getFont().deriveFont(Font.BOLD, 15f));
		startTimer.setMaximumSize(d);
		startTimer.setAlignmentX(CENTER_ALIGNMENT);
		endTimer.setMaximumSize(d);
		endTimer.setAlignmentX(CENTER_ALIGNMENT);
		endTimer.setFont(endTimer.getFont().deriveFont(Font.BOLD, 15f));
		endTimer.setForeground(Color.red);
		timer.setAlignmentX(CENTER_ALIGNMENT);
		
	}

	private void initFieldPane(JPanel field) {
		field.setBorder(BorderFactory.createTitledBorder("Choosen Answers"));
		field.setLayout(new GridLayout(5, 10));
		answerArr = new JLabel[25];
		for (int i = 0; i < 25; i++) {
			field.add(new JLabel(String.format("%2d.", i)));
			field.add(answerArr[i] = new JLabel(" "));
		}
	}

	private void initButtonPane(JPanel gridBtn) {
		questionArr = new JToggleButton[25];
		for (int i = 0; i < questionArr.length; i++) {
			questionArr[i] = new JToggleButton(i + "");
			questionArr[i].addActionListener(this);
			gridBtn.add(questionArr[i]);
			questionBtnGr.add(questionArr[i]);
		}
	}

	private void initQuestion(JPanel question) {
		Font a = getFont().deriveFont(25f);
		question.setLayout(new BorderLayout());
		questionArea = new JPanel();
		questionArea.setBorder(BorderFactory.createTitledBorder(lineBorder, "Question"));
		choiceArea = new JPanel();
		choiceArea.setBorder(BorderFactory.createTitledBorder(lineBorder, "Answers"));
		JSplitPane y = new JSplitPane(JSplitPane.VERTICAL_SPLIT, questionArea, choiceArea);
		y.setDividerLocation(150);
		y.setEnabled(false);
		question.add(y, BorderLayout.CENTER);
		
		JPanel answerArea;
		question.add(answerArea = new JPanel(new FlowLayout(FlowLayout.CENTER, 90, 0)), BorderLayout.SOUTH);
		answerArea.add(b1 = new JToggleButton("A"));
		answerArea.add(b2 = new JToggleButton("B"));
		answerArea.add(b3 = new JToggleButton("C"));
		answerArea.add(b4 = new JToggleButton("D"));
		answerGroup.add(b1);
		answerGroup.add(b2);
		answerGroup.add(b3);
		answerGroup.add(b4);
		b1.setFont(a);
		b1.addActionListener(this);
		b2.setFont(a);
		b2.addActionListener(this);
		b3.setFont(a);
		b3.addActionListener(this);
		b4.setFont(a);
		b4.addActionListener(this);
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
		if (e.getSource() == startTimer)
			startTimer();
		else if (e.getSource() == endTimer)
			stopTimer();
		else if (e.getSource() == b1 || e.getSource() == b2 ||e.getSource() == b3||e.getSource() == b4) {
			answerArr[currentIndex].setText(e.getActionCommand());
			questionArr[currentIndex].setFont(questionArr[currentIndex].getFont().deriveFont(Font.BOLD + Font.ITALIC));
			questionArr[currentIndex].setForeground(Color.blue);
		}
		else if (e.getSource() instanceof JToggleButton && e.getActionCommand() != Integer.toString(currentIndex)) {
			currentIndex = Integer.valueOf(e.getActionCommand());
			questionArea.setBorder(BorderFactory.createTitledBorder(lineBorder, "Question #" + currentIndex));
			if (answerArr[currentIndex].getText() == "A")
				answerGroup.setSelected(b1.getModel(), true);
			else if (answerArr[currentIndex].getText() == "B")
				answerGroup.setSelected(b2.getModel(), true);
			else if (answerArr[currentIndex].getText() == "C")
				answerGroup.setSelected(b3.getModel(), true);
			else if (answerArr[currentIndex].getText() == "D")
				answerGroup.setSelected(b4.getModel(), true);
			else
				answerGroup.clearSelection();
		}
	}

	private void stopTimer() {
		int a = JOptionPane.showConfirmDialog(this, "Do you want to nop bai?", "Nop bai", JOptionPane.YES_NO_OPTION);
		if (a == JOptionPane.YES_OPTION) {
			if (!timerThread.isInterrupted()) {
				timerThread.interrupt();
			}
		}
	}

	private void startTimer() {
		if (!TimeThread.exist) {
			timerThread = new TimeThread(TIME_MAX, timer, timerBar);
			timerThread.start();
		}
	}

}
