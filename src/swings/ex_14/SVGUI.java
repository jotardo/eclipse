package swings.ex_14;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SVGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Integer[] day;
	private Integer[] month;
	private Integer[] year;
	private JButton openBtn, saveBtn, exitBtn;
	private JMenuItem openItem, saveItem, exitItem;
	private JFileChooser fileChooser;
	private SVData currentData;
	private JTextField nameField;
	private JComboBox<Integer> dayField, monthField, yearField;
	private JRadioButton maleBtn, femaleBtn;
	private JCheckBox eatBtn, singBtn, shopBtn, travelBtn;
	private ButtonGroup btnGr = new ButtonGroup();

	public SVGUI() {
		super("Lý lịch sinh viên");
		
		/*Cụm try - catch là set giao diện*/
		try {
			UIManager.setLookAndFeel(new javax.swing.plaf.nimbus.NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		setSize(300, 300);
		setResizable(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		initFileChooser();
		initMenuBar();
		initVar();
		initContent();
		setVisible(true);

		currentData = new SVData();
	}

	private void initMenuBar() {
		JMenuBar menuBar;
		JMenu fileMenu;
		// JMenuItem openItem, saveItem, exitItem;
		setJMenuBar(menuBar = new JMenuBar());
		menuBar.add(fileMenu = new JMenu("File"));
		fileMenu.setMnemonic('F');
		fileMenu.add(openItem = new JMenuItem("Open", 'O'));
		fileMenu.add(saveItem = new JMenuItem("Save", 'S'));
		fileMenu.addSeparator();
		fileMenu.add(exitItem = new JMenuItem("Exit", 'E'));
		openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		openItem.addActionListener(this);
		saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		saveItem.addActionListener(this);
		exitItem.addActionListener(this);
	}

	private void initVar() {
		day = new Integer[31];
		month = new Integer[12];
		year = new Integer[42];
		int yearStart = 1980;
		for (int i = 0; i < day.length; i++) {
			day[i] = i + 1;
		}
		for (int i = 0; i < month.length; i++) {
			month[i] = i + 1;
		}
		for (int i = yearStart; i < year.length + yearStart; i++) {
			year[i - yearStart] = i;
		}
	}

	private void initContent() {
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		add(container);

		JLabel tmpLabel;
		container.add(tmpLabel = new JLabel("LÝ LỊCH SINH VIÊN", JLabel.CENTER));
		tmpLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		tmpLabel.setFont(tmpLabel.getFont().deriveFont(15f));

		JPanel panel1, panel2, panel3, panel4, panel4Child, panel5;
		container.add(panel1 = new JPanel());
		container.add(panel2 = new JPanel());
		container.add(panel3 = new JPanel());
		container.add(panel4 = new JPanel());
		container.add(panel5 = new JPanel());

		panel1.add(tmpLabel = new JLabel("Họ và tên"));
		tmpLabel.setFont(tmpLabel.getFont().deriveFont(13f));
		panel1.add(nameField = new JTextField(15));

		panel2.add(tmpLabel = new JLabel("Ngày sinh"));
		tmpLabel.setFont(tmpLabel.getFont().deriveFont(13f));
		panel2.add(dayField = new JComboBox<>(day));
		panel2.add(new JLabel("/"));
		panel2.add(monthField = new JComboBox<>(month));
		panel2.add(new JLabel("/"));
		panel2.add(yearField = new JComboBox<>(year));

		panel3.add(Box.createHorizontalStrut(-100));
		panel3.add(tmpLabel = new JLabel("Giới tính"));
		tmpLabel.setFont(tmpLabel.getFont().deriveFont(13f));
		btnGr.add((AbstractButton) panel3.add(maleBtn = new JRadioButton("Nam")));
		panel3.add(Box.createHorizontalGlue());
		btnGr.add((AbstractButton) panel3.add(femaleBtn = new JRadioButton("Nữ")));

		// panel4.setLayout(new FlowLayout(FlowLayout.LEADING));
		panel4.add(Box.createHorizontalStrut(-50));
		panel4.add(tmpLabel = new JLabel("Sở thích"));
		tmpLabel.setFont(tmpLabel.getFont().deriveFont(13f));
		panel4.add(panel4Child = new JPanel(new GridLayout(2, 2, 5, 0)));
		panel4Child.add(eatBtn = new JCheckBox("Ăn uống"));
		panel4Child.add(singBtn = new JCheckBox("Ca hát"));
		panel4Child.add(shopBtn = new JCheckBox("Mua sắm"));
		panel4Child.add(travelBtn = new JCheckBox("Du lịch"));

		panel5.add(openBtn = new JButton("Open"));
		panel5.add(saveBtn = new JButton("Save"));
		panel5.add(exitBtn = new JButton("Exit"));

		maleBtn.addActionListener(this);
		femaleBtn.addActionListener(this);
		eatBtn.addActionListener(this);
		singBtn.addActionListener(this);
		shopBtn.addActionListener(this);
		travelBtn.addActionListener(this);
		openBtn.addActionListener(this);
		saveBtn.addActionListener(this);
		exitBtn.addActionListener(this);
	}

	private void initFileChooser() {
		fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Tệp SV (.hcmuaf)", "hcmuaf");
		fileChooser.setFileFilter(filter);
		fileChooser.setMultiSelectionEnabled(false);
		fileChooser.setCurrentDirectory(new File("Desktop"));
	}

	public static void main(String[] args) {
//		String a;
//		System.out.println(a = SVDataUtils.encode("Your mom", "$"));
//		System.out.println(SVDataUtils.decode(a, "$"));
		new SVGUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int num;
		if (e.getSource()==openBtn || e.getSource()==openItem) {
			num = fileChooser.showOpenDialog(this);
			if (num == JFileChooser.APPROVE_OPTION) {
				SVDataUtils.openFile(fileChooser.getSelectedFile().getAbsolutePath(), currentData);
				updateCurrentData();
			}
		} else if (e.getSource()==saveBtn || e.getSource()==saveItem) {
			num = fileChooser.showSaveDialog(this);
			if (num == JFileChooser.APPROVE_OPTION) {
				setCurentData();
				SVDataUtils.saveFile(fileChooser.getSelectedFile().getAbsolutePath(), currentData);
			}
		} else if (e.getSource() == exitBtn || e.getSource() == exitItem)
			System.exit(0);
		else if (e.getSource() == maleBtn)
			currentData.gender = SVData.MALE;
		else if (e.getSource() == femaleBtn)
			currentData.gender = SVData.FEMALE;
		else if (e.getSource() == eatBtn)
			currentData.hobby[0] = eatBtn.isSelected() ? 1 : 0;
		else if (e.getSource() == singBtn)
			currentData.hobby[1] = eatBtn.isSelected() ? 1 : 0;
		else if (e.getSource() == shopBtn)
			currentData.hobby[2] = eatBtn.isSelected() ? 1 : 0;
		else if (e.getSource() == travelBtn)
			currentData.hobby[3] = eatBtn.isSelected() ? 1 : 0;
	}

	private void setCurentData() {
		currentData.name = this.nameField.getText();
		currentData.day = (int) this.dayField.getSelectedItem();
		currentData.month = (int) this.monthField.getSelectedItem();
		currentData.year = (int) this.yearField.getSelectedItem();
	}

	private void updateCurrentData() {
		this.nameField.setText(currentData.name);
		this.dayField.setSelectedItem(currentData.day);
		this.monthField.setSelectedItem(currentData.day);
		this.yearField.setSelectedItem(currentData.day);
		this.btnGr.setSelected(this.maleBtn.getModel(), currentData.gender == SVData.MALE);
		this.btnGr.setSelected(this.femaleBtn.getModel(), currentData.gender == SVData.FEMALE);
		this.eatBtn.setSelected(currentData.hobby[0] == 1);
		this.singBtn.setSelected(currentData.hobby[1] == 1);
		this.shopBtn.setSelected(currentData.hobby[2] == 1);
		this.travelBtn.setSelected(currentData.hobby[3] == 1);
	}
}
