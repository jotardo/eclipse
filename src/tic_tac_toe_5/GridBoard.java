package tic_tac_toe_5;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

class GridBoard extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private boolean isX = true;
	private int turn = 1;
	private JButton[][] btnLoc;
	private ArrayList<Point> tickedPoints;

	public GridBoard(int size) {
		super(new GridLayout(size, size));
		btnLoc = new JButton[size][size];
		Dimension btnSize = new Dimension(25, 25);
		Border btnBorder = BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
				BorderFactory.createLineBorder(Color.BLACK));
		for (int y = 0; y < btnLoc.length; y++) {
			for (int x = 0; x < btnLoc[y].length; x++) {
				btnLoc[y][x] = new JButton();
				btnLoc[y][x].setPreferredSize(btnSize);
				btnLoc[y][x].setBorder(btnBorder);
				btnLoc[y][x].addActionListener(this);
				btnLoc[y][x].setText("");
				btnLoc[y][x].setName(x + ":" + y);
				add(btnLoc[y][x]);
			}
		}
		tickedPoints = new ArrayList<>(size * size);
	}

	public boolean isX() {
		return isX;
	}

	public int getTurn() {
		return turn;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton src = (JButton) e.getSource();
		if (!src.getText().isEmpty()) {
			Launcher.notify("Warning : You can't choose the block that was ticked!");
			return;
		}
		//danh x, o
		src.setForeground(isX ? Color.BLUE : Color.RED);
		src.setText(isX ? "X" : "O");
		//check win
		if (checkEndGame(src, isX))
			popupWin();
		else {
			if (!isX)
				turn++;
			isX = !isX;
			Launcher.updateDisplay();
		}
	}

	private boolean checkEndGame(JButton btn, boolean isX) {
		JButton[] tmpTickArr = null;
		int x = Integer.valueOf(btn.getName().split(":")[0]);
		int y = Integer.valueOf(btn.getName().split(":")[1]);
		tickedPoints.add(new Point(x, y));

		boolean isVictory = false;
		/*vòng lặp check với delta là số block so với block gốc*/
		for (int delta = -4; delta <= 0; delta++) {
			/*hàng ngang*/
			try {
				tmpTickArr = new JButton[] { btnLoc[y][x + delta], btnLoc[y][x + 1 + delta], btnLoc[y][x + 2 + delta],
						btnLoc[y][x + 3 + delta], btnLoc[y][x + 4 + delta] };
				if (checkWinning(tmpTickArr)) {
					System.out.println("Winned by row!");
					isVictory = true;
				}
			} catch (ArrayIndexOutOfBoundsException e) {}
			/*hàng dọc*/
			try {
				tmpTickArr = new JButton[] { btnLoc[y + delta][x], btnLoc[y + 1 + delta][x], btnLoc[y + 2 + delta][x],
						btnLoc[y + 3 + delta][x], btnLoc[y + 4 + delta][x] };
				if (checkWinning(tmpTickArr)) {
					System.out.println("Winned by column!");
					isVictory = true;
				}
			} catch (ArrayIndexOutOfBoundsException e) {}
			/*hàng chéo phải*/
			try {
				tmpTickArr = new JButton[] { btnLoc[y + delta][x + delta], btnLoc[y + 1 + delta][x + 1 + delta],
						btnLoc[y + 2 + delta][x + 2 + delta], btnLoc[y + 3 + delta][x + 3 + delta],
						btnLoc[y + 4 + delta][x + 4 + delta] };
				if (checkWinning(tmpTickArr)) {
					System.out.println("Winned by east diagonal!");
					isVictory = true;
				}
			} catch (ArrayIndexOutOfBoundsException e) {}
			/*hàng chéo trái*/
			try {
				tmpTickArr = new JButton[] { btnLoc[y + delta][x - delta], btnLoc[y + 1 + delta][x - 1 - delta],
						btnLoc[y + 2 + delta][x - 2 - delta], btnLoc[y + 3 + delta][x - 3 - delta],
						btnLoc[y + 4 + delta][x - 4 - delta] };
				if (checkWinning(tmpTickArr)) {
					System.out.println("Winned by west diagonal!");
					isVictory = true;
				}
			} catch (ArrayIndexOutOfBoundsException e) {}
		}
//		System.gc();
		return isVictory;
	}

	private boolean checkWinning(JButton[] arr) {
		/*Khi nó không đúng, lập tức trả false*/
		for (int i = 1; i < arr.length; i++)
			if (!(arr[i - 1].getText().equals(arr[i].getText())))
				return false;
		return true;
	}

	void popupWin() {
		int result = JOptionPane.showOptionDialog(getParent(), "WINNER! Want to Replay?", "I don't know man",
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
		switch (result) {
		case JOptionPane.YES_OPTION:
			tickedPoints.forEach((pt) -> {
				btnLoc[pt.y][pt.x].setForeground(Color.BLACK);
				btnLoc[pt.y][pt.x].setText("");
			});
			tickedPoints.clear();
			break;
		case JOptionPane.NO_OPTION:
			System.exit(0);
		}
	}
}
