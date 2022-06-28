package swings.ex_15;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Ex15 extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JButton addBtn, sortBtn;
	private JTextField nameField, yearField;
	private JComboBox<String> typeBox, styleBox, authorBox;
	private ArrayList<Painting> paintingList = new ArrayList<>();
	private JTextArea entryArea;
	
	public Ex15() {
		super("Art Management");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		setSize(400, 400);
		initContent(this);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void initContent(JFrame frame) {
		String[] paintingType = {"Sơn dầu"};
		String[] paintingStyle = {"Không rõ", "Hiện đại", "Phục hưng", "Cổ điển"};
		String[] paintingAuthor = {"Bùi Xuân Phái", "Leonardo Da Vinci"};
		
		Dimension leftSide = new Dimension(90, 20);
		Dimension rightSide = new Dimension(150, 20);
		JLabel tmpLabel;
		tmpLabel = new JLabel("QUẢN LÍ TÁC PHẨM HỘI HỌA");
		tmpLabel.setAlignmentX(CENTER_ALIGNMENT);
		frame.add(tmpLabel);
		
		JPanel inputArea = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		inputArea.setMaximumSize(new Dimension(400,400));
		Dimension nextLineDim = new Dimension(inputArea.getMaximumSize().width, 0);
		inputArea.add(new JLabel("Tên tác phẩm")).setPreferredSize(leftSide);
		inputArea.add(nameField = new JTextField()).setPreferredSize(rightSide);
		inputArea.add(Box.createRigidArea(nextLineDim));
		inputArea.add(new JLabel("Năm sáng tác")).setPreferredSize(leftSide);
		inputArea.add(yearField = new JTextField()).setPreferredSize(rightSide);
		inputArea.add(Box.createRigidArea(nextLineDim));
		inputArea.add(new JLabel("Loại tranh")).setPreferredSize(leftSide);
		inputArea.add(typeBox = new JComboBox<>(paintingType)).setPreferredSize(rightSide);
		inputArea.add(Box.createRigidArea(nextLineDim));
		inputArea.add(new JLabel("Phong cách")).setPreferredSize(leftSide);
		inputArea.add(styleBox = new JComboBox<>(paintingStyle)).setPreferredSize(rightSide);
		inputArea.add(Box.createRigidArea(nextLineDim));
		inputArea.add(new JLabel("Tác giả")).setPreferredSize(leftSide);
		inputArea.add(authorBox = new JComboBox<>(paintingAuthor)).setPreferredSize(rightSide);
		inputArea.add(Box.createRigidArea(nextLineDim));
		frame.add(inputArea);
		
		JPanel add_sort_pnl = new JPanel();
		add_sort_pnl.add(addBtn = new JButton("Thêm", new ImageIcon("res/ex_3/plus.png"))).setPreferredSize(new Dimension(frame.getWidth() / 2 - 10, 20));
		add_sort_pnl.add(sortBtn = new JButton("Sắp xếp")).setPreferredSize(new Dimension(frame.getWidth() / 2 - 10, 20));
		add_sort_pnl.setMaximumSize(add_sort_pnl.getPreferredSize());
		frame.add(add_sort_pnl);
		
		JScrollPane entryPanel = new JScrollPane();
		entryPanel.setMaximumSize(new Dimension(frame.getSize().width, 400));
		entryPanel.setBorder(BorderFactory.createTitledBorder("Thông tin về tác phẩm hội họa"));
		entryArea = new JTextArea();
		entryArea.setLineWrap(true);
		entryArea.setEditable(false);
		entryPanel.setViewportView(entryArea);
		frame.add(entryPanel);
		
		addBtn.addActionListener(this);
		sortBtn.addActionListener(this);
	}

	public static void main(String[] args) {
		new Ex15();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addBtn) {
			if (nameField.getText().equals("")) {
				JOptionPane.showMessageDialog(getContentPane(), "Please type in the painiting name!", "No", JOptionPane.WARNING_MESSAGE);
				return;
			}
			if (!yearField.getText().matches("\\d{4}")) {
				JOptionPane.showMessageDialog(getContentPane(), "Please type painiting year in YYYY format!", "No", JOptionPane.WARNING_MESSAGE);
				return;
			}
			paintingList.add(new Painting(nameField.getText(), Integer.valueOf(yearField.getText()), typeBox.getSelectedItem().toString(), styleBox.getSelectedItem().toString(), authorBox.getSelectedItem().toString()));
			updateEntryArea();
		}
		else if (e.getSource() == sortBtn);
	}

	private void updateEntryArea() {
		entryArea.setText(null);
		for (Painting paint: paintingList) {
			entryArea.append(paint.toString() + "\n");
		}
		
	}
}
