package not_paint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Paint extends JFrame implements ActionListener, MouseListener, ListSelectionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// shape
	static final int BRUSH_MODE = 1 << 0;
	static final int LINE_MODE = 1 << 1;
	static final int RECTANGLE_MODE = 1 << 2;
	static final int ROUNDED_RECTANGLE_MODE = 1 << 3;
	static final int OVAL_MODE = 1 << 4;
	// mode
	static final int LINE_ONLY = 2 << 0;
	static final int FILL_ONLY = 2 << 1;
	static final int LINE_FILL = 2 << 2;

	// OptionBar
	private JPanel optionArea;
	private PaintPanel paintArea;
	private JPanel shapePane, modesPane, colorPane, colorPaneContent, colorHolder1, colorHolder2;
	private JButton exitBtn, clearBtn;
	private JRadioButton brushBtn, rectBtn, roundRectBtn, ovalBtn, lineBtn;
	private JList<String> modesList;
	private CustomColorPane customColorPane;

	private ButtonGroup shapeGroup;
	private Color[] colorOptions = { Color.BLACK, Color.BLUE, Color.CYAN, Color.GRAY, Color.GREEN, Color.MAGENTA,
			Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW };
	private ArrayList<JButton> colorBtn = new ArrayList<>(colorOptions.length);
	private JButton otherColorBtn;
	private int width = 220;
	private int widthCom = width - 25;
	private Border paneBorder = BorderFactory.createEtchedBorder();

	public Paint() {
		super("NotPaint");
		setLayout(new BorderLayout());
		setSize(1024, 640);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(1f, 0.3f, 0.2f, 0.5f));
		initContent();
		setExtendedState(MAXIMIZED_BOTH);
		setVisible(true);
		makeEvents();
	}

	public static void main(String[] args) {
		new Paint();
		System.out.println("Main method started.");
	}

	private void initContent() {
		paintArea = new PaintPanel(BRUSH_MODE, LINE_ONLY);
		add(paintArea, BorderLayout.CENTER);

		optionArea = new JPanel();
		optionArea.setPreferredSize(new Dimension(width, 0));
		optionArea.setLayout(new FlowLayout(FlowLayout.LEADING));
		optionArea.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED), "NotPaint"));
		((TitledBorder) optionArea.getBorder()).setTitleColor(new Color(0, 0, 0, 192));
		((TitledBorder) optionArea.getBorder()).setTitleFont(((TitledBorder) optionArea.getBorder()).getTitleFont().deriveFont(Font.ITALIC, 17f));
		add(optionArea, BorderLayout.WEST);

		initShapeOption(widthCom);
		initModeOption(widthCom);
		initColorChooser(widthCom);
		initMenuBar(widthCom);

		optionArea.add(clearBtn = new JButton("Clear"));
		clearBtn.setPreferredSize(new Dimension(widthCom, 45));
		optionArea.add(exitBtn = new JButton("Exit"));
		exitBtn.setPreferredSize(new Dimension(widthCom, 45));
	}

	private void initMenuBar(int widthCom2) {
		JMenuBar bar = new JMenuBar();
		bar.add(new JMenu("Mah man"));
		bar.add(new JMenu("Help"));
		setJMenuBar(bar);
	}

	private void initShapeOption(int widthCom) {
		shapePane = new JPanel();
		shapePane.setBorder(paneBorder);
		shapePane.setPreferredSize(new Dimension(widthCom, 150));
		shapePane.setLayout(new BoxLayout(shapePane, BoxLayout.Y_AXIS));
		shapePane.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		shapePane.add(new JLabel("Shape"));
		shapePane.add(brushBtn = new JRadioButton("Brush mode"));
		shapePane.add(rectBtn = new JRadioButton("Rectangle"));
		shapePane.add(roundRectBtn = new JRadioButton("Rounded Rectangle"));
		shapePane.add(ovalBtn = new JRadioButton("Oval"));
		shapePane.add(lineBtn = new JRadioButton("Line"));
		optionArea.add(shapePane);
		// modes.add(pencilBtn = new JRadioButton("Pencil Mode"));
		shapeGroup = new ButtonGroup();
		shapeGroup.add(brushBtn);
		shapeGroup.add(rectBtn);
		shapeGroup.add(ovalBtn);
		shapeGroup.add(roundRectBtn);
		shapeGroup.add(lineBtn);
		shapeGroup.setSelected(brushBtn.getModel(), true);
	}

	private void initModeOption(int widthCom) {
		modesPane = new JPanel();
		modesPane.setBorder(paneBorder);
		modesPane.setPreferredSize(new Dimension(widthCom, 80));
		modesPane.setLayout(new BorderLayout());
		modesPane.add(new JLabel("Option"), BorderLayout.NORTH);
		modesPane.add(modesList = new JList<>(new String[] { "Line only", "Fill only", "Line & Fill" }));
		modesList.setSelectedIndex(0);
		modesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		modesList.addListSelectionListener(this);
		optionArea.add(modesPane);
	}

	private void initColorChooser(int widthCom) {
		JLabel lb1, lb2, lb3;
		colorPane = new JPanel(new BorderLayout());
		colorPane.setBorder(paneBorder);
		colorPane.setPreferredSize(new Dimension(widthCom, 90));
		colorPane.setBackground(Color.PINK);
		JPanel colorStuff = new JPanel(new FlowLayout(FlowLayout.LEFT));
		colorStuff.add(lb1 = new JLabel("Color: "));
		lb1.setFont(lb1.getFont().deriveFont(15f));
		colorStuff.add(lb2 = new JLabel("Line"));
		lb2.setFont(lb2.getFont().deriveFont(15f));
		colorStuff.add(colorHolder1 = new JPanel());
		colorHolder1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		colorHolder1.setBackground(Color.BLACK);
		colorStuff.add(lb3 = new JLabel("Fill"));
		lb3.setFont(lb3.getFont().deriveFont(15f));
		colorStuff.add(colorHolder2 = new JPanel());
		colorHolder2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		colorHolder2.setBackground(Color.WHITE);
		colorPane.add(colorStuff, BorderLayout.NORTH);

		colorPaneContent = new JPanel(new GridLayout(2, 6));
		JButton tmp;
		for (int i = 0; i < colorOptions.length; i++) {
			tmp = new JButton();
			tmp.setName("color_" + i);
			tmp.setBackground(colorOptions[i]);
			colorBtn.add(tmp);
			colorPaneContent.add(tmp);
		}
		colorPaneContent.add(otherColorBtn = new JButton("..."));
		colorPane.add(colorPaneContent, BorderLayout.CENTER);

		colorPane.add(customColorPane = new CustomColorPane(), BorderLayout.SOUTH);
		customColorPane.setVisible(false);
		optionArea.add(colorPane);
	}

	protected void makeEvents() {
		rectBtn.addActionListener(this);
		roundRectBtn.addActionListener(this);
		ovalBtn.addActionListener(this);
		brushBtn.addActionListener(this);
		lineBtn.addActionListener(this);
		exitBtn.addActionListener(this);
		clearBtn.addActionListener(this);
		colorBtn.forEach(colorBtn -> {
			colorBtn.addMouseListener(this);
		});
		otherColorBtn.addActionListener(this);
		customColorPane.customColorBtn.addMouseListener(this);
		paintArea.makeEvent();
	}

	protected void killEvents() {
		rectBtn.removeActionListener(this);
		roundRectBtn.removeActionListener(this);
		ovalBtn.removeActionListener(this);
		brushBtn.removeActionListener(this);
		lineBtn.removeActionListener(this);
		exitBtn.removeActionListener(this);
		clearBtn.removeActionListener(this);
		colorBtn.forEach(colorBtn -> {
			colorBtn.removeMouseListener(this);
		});
		otherColorBtn.removeActionListener(this);
		customColorPane.customColorBtn.removeMouseListener(this);
		paintArea.killEvent();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		JButton a = (JButton) e.getSource();
		if (e.getButton() == MouseEvent.BUTTON1) {
			a.doClick(100);
			// System.out.println(e);
			colorHolder1.setBackground(a.getBackground());
			paintArea.setLineColor(a.getBackground());
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			a.doClick(100);
			colorHolder2.setBackground(a.getBackground());
			colorHolder2.setBackground(a.getBackground());
			paintArea.setFillColor(a.getBackground());
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		switch (modesList.getSelectedIndex()) {
		case 0:
			paintArea.setDrawMode(LINE_ONLY);
			break;
		case 1:
			paintArea.setDrawMode(FILL_ONLY);
			break;
		case 2:
			paintArea.setDrawMode(LINE_FILL);
			break;
		default:
			paintArea.setDrawMode(LINE_ONLY);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exitBtn) {
			killEvents();
			System.exit(0);
		}
		else if (e.getSource() == clearBtn) {
			paintArea.clearDrawing();
		}
		else if (e.getSource() == rectBtn)
			paintArea.setShapeType(RECTANGLE_MODE);
		else if (e.getSource() == ovalBtn)
			paintArea.setShapeType(OVAL_MODE);
		else if (e.getSource() == roundRectBtn)
			paintArea.setShapeType(ROUNDED_RECTANGLE_MODE);
		else if (e.getSource() == brushBtn)
			paintArea.setShapeType(BRUSH_MODE);
		else if (e.getSource() == lineBtn)
			paintArea.setShapeType(LINE_MODE);
		else if (e.getSource() == otherColorBtn) {
			customColorPane.setVisible(!customColorPane.isVisible());
			colorPane.setPreferredSize(new Dimension(widthCom,
					90 + (customColorPane.isVisible() ? customColorPane.getPreferredSize().height : 0)));
			repaint();
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

}
