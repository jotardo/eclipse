package little_ui.pane;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Enumeration;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import little_ui.question.Question;
import little_ui.thread.TimeThread;

public class ContentPane extends APane implements ActionListener {

	private class TextPane extends JPanel{

		private static final long serialVersionUID = 0l;
		
		public TextPane() {
			super(new FlowLayout(FlowLayout.LEFT, 5, 3));
		}
		
		private void displayQuestion(Question src) {
			removeAll();
			String[] str = src.getQuestion().split("(\\|)|\n");
			System.out.println(Arrays.toString(str));
			for (int i = 0; i < str.length; i++) {
				if (str[i].matches(Question.getPattern()))
					add(new JLabel(new ImageIcon(str[i].substring(5, str[i].length() - 1))));
				else if (str[i].equals(""))
					add(Box.createRigidArea(new Dimension(questionArea.getSize().width, 0)));
				else
					add(new JLabel(str[i]));
			}
			updateUI();
		}
		
		private void displayAnswer(Question src) {
			removeAll();
			String[] choice = src.getChoices();
			for (int i = 0; i < choice.length; i++) {
				String[] str = choice[i].split("(\\|)|\n");
				add(new JLabel((char) ('A' + i) + ". "));
				for (int j = 0; j < str.length; j++) {
					if (str[j].matches(Question.getPattern()))
						add(new JLabel(new ImageIcon(str[j].substring(5, str[j].length() - 1))));
					else if (str[j].equals(""))
						add(Box.createRigidArea(new Dimension(questionArea.getSize().width, 0)));
					else
						add(new JLabel(str[j]));
				}
				add(Box.createRigidArea(new Dimension(questionArea.getSize().width, 0)));
			}
			updateUI();
		}

	}

	private static final int TIME_MAX = 25*60;
	private static final int QUESTION_NUM = 25;
	private static final long serialVersionUID = 1L;

	private JButton nextQuestBtn, prevQuestBtn, endTimer, startTimer;
	private ButtonGroup questionBtnGr = new ButtonGroup();
	private ButtonGroup choiceGroup = new ButtonGroup();
	private JLabel timer;
	private JProgressBar timerBar;
	private Thread timerThread;
	private JToggleButton[] questionArr;
	private JToggleButton choiceA, choiceB, choiceC, choiceD;
	private JToggleButton[] choiceArr = new JToggleButton[4];
	private JLabel[] answerArr;
	private Border lineBorder = BorderFactory.createStrokeBorder(new BasicStroke(1.5f,BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
	private TextPane questionArea, choiceArea;
	private int currentIndex = 0;
	private JToggleButton pressedChoice;
	private Question[] exported = Question.makeRandomQuestion(25);
	
	public ContentPane() {
		super();
		setSize(1100, 550);
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		initContent(45);
	}

	private void initContent(int hgap) {
		JPanel btnPanel = new JPanel();
		Dimension d = new Dimension(150, 50);
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.PAGE_AXIS));
		btnPanel.setBorder(BorderFactory.createEtchedBorder());
		JPanel fieldPane = new JPanel(new GridLayout(5, 5 * 2));
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
		timer.setFont(timer.getFont().deriveFont(20f));
		btnPanel.add(timerBar = new JProgressBar(JProgressBar.HORIZONTAL));
		timerBar.setMaximumSize(new Dimension(160, 30));
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
		answerArr = new JLabel[QUESTION_NUM];
		for (int i = 0; i < 25; i++) {
			field.add(new JLabel(String.format("%2d.", i)));
			field.add(answerArr[i] = new JLabel(" "));
		}
	}

	private void initButtonPane(JPanel gridBtn) {
		questionArr = new JToggleButton[QUESTION_NUM];
		for (int i = 0; i < questionArr.length; i++) {
			questionArr[i] = new JToggleButton(i + "");
			questionArr[i].setFont(questionArr[i].getFont().deriveFont(13f));
			questionArr[i].setEnabled(false);
			gridBtn.add(questionArr[i]);
			questionBtnGr.add(questionArr[i]);
		}
	}

	private void initQuestion(JPanel question) {
		Font a = getFont().deriveFont(35f);
		question.setLayout(new BorderLayout());
		questionArea = new TextPane();
		questionArea.setBorder(BorderFactory.createTitledBorder(lineBorder, "Question"));
		choiceArea = new TextPane();
		choiceArea.setBorder(BorderFactory.createTitledBorder(lineBorder, "Answers"));
		JSplitPane y = new JSplitPane(JSplitPane.VERTICAL_SPLIT, questionArea, choiceArea);
		y.setDividerLocation(180);
		y.setEnabled(false);
		question.add(y, BorderLayout.CENTER);
		
		JPanel choiceArea;
		question.add(choiceArea = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 0)), BorderLayout.SOUTH);
		for (int i = 0; i < choiceArr.length; i++) {
			choiceArr[i] = new JToggleButton("" + (char) ('A' + i));
			choiceArr[i].setFont(a);
			choiceArr[i].setEnabled(false);
			choiceArea.add(choiceArr[i]);
			choiceGroup.add(choiceArr[i]);
		}
		choiceA = choiceArr[0];
		choiceB = choiceArr[1];
		choiceC = choiceArr[2];
		choiceD = choiceArr[3];
	}

	@Override
	public void makeEvents() {
		for (JToggleButton btn : questionArr)
			btn.addActionListener(this);
		for (JToggleButton btn : choiceArr)
			btn.addActionListener(this);
		startTimer.addActionListener(this);
		endTimer.addActionListener(this);
	}

	@Override
	public void killEvents() {
		for (JToggleButton btn : questionArr)
			btn.removeActionListener(this);
		for (JToggleButton btn : choiceArr)
			btn.removeActionListener(this);
		startTimer.removeActionListener(this);
		endTimer.removeActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startTimer)
			startTimer();
		else if (e.getSource() == endTimer)
			stopTimer();
		else if (e.getSource() == choiceA || e.getSource() == choiceB|| e.getSource() == choiceC|| e.getSource() == choiceD) {
			if (e.getSource() == pressedChoice)
				return;
			System.out.println(true);
			pressedChoice = (JToggleButton) e.getSource();
			answerArr[currentIndex].setText(((JToggleButton) e.getSource()).getText());
			questionArr[currentIndex].setForeground(Color.BLUE);
			questionArr[currentIndex].setFont(questionArr[currentIndex].getFont().deriveFont(Font.BOLD+Font.ITALIC));
		}
		else if (e.getSource() instanceof JToggleButton && e.getActionCommand() != Integer.toString(currentIndex)) {
			currentIndex = Integer.valueOf(e.getActionCommand());
			questionArea.setBorder(BorderFactory.createTitledBorder(lineBorder, "Question #" + currentIndex));
			questionArea.displayQuestion(exported[currentIndex]);
			choiceArea.displayAnswer(exported[currentIndex]);
			if (answerArr[currentIndex].getText().toCharArray()[0] - 'A' >= 0) {
				choiceGroup.setSelected(choiceArr[answerArr[currentIndex].getText().toCharArray()[0] - 'A'].getModel(), true);
				pressedChoice = choiceArr[answerArr[currentIndex].getText().toCharArray()[0] - 'A'];
			}
			else {
				choiceGroup.clearSelection();
				pressedChoice = null;
			}
			updateUI();
		}
	}

	private void startTimer() {
		if (!TimeThread.exist) {
			timerThread = new TimeThread(TIME_MAX, timer, timerBar);
			timerThread.start();
			for (JToggleButton btn : questionArr) {
				btn.setEnabled(true);
			}
			for (JToggleButton btn : choiceArr) {
				btn.setEnabled(true);
			}
			questionArr[0].doClick();
		}
	}

	private void stopTimer() {
		//int a = JOptionPane.showConfirmDialog(this, "Do you want to nop bai?", "Nop bai", JOptionPane.YES_NO_OPTION);
		//if (a == JOptionPane.YES_OPTION) {
			if (!timerThread.isInterrupted()) {
				timerThread.interrupt();
				for (JToggleButton btn : questionArr) {
					btn.setEnabled(false);
				}
				for (JToggleButton btn : choiceArr) {
					btn.setEnabled(false);
				}
			}
		//}
	}
	
	
}
