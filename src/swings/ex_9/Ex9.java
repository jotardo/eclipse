package swings.ex_9;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

public class Ex9 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Ex9() {
		super("JScrollBar Demostration");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(400, 200);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		initContent();
		setVisible(true);
	}
	
	private void initContent() {
		String[] cagetories = { "Household", "Office", "Extended family", "Company (US)", "Comapny (World)", "Team",
			"Will", "Birthday Card List", "High School", "Country", "Continent", "Planet" };
		ButtonGroup[] btnGrArr = new ButtonGroup[cagetories.length];
		JPanel pnl = new JPanel(new GridLayout(cagetories.length + 1, 7, 6, 0));
		JScrollPane a = new JScrollPane();
		
		a.setViewportView(pnl);
		add(a);
		
		pnl.add(new JLabel());
		pnl.add(new JLabel("0-1"));
		pnl.add(new JLabel("2-5"));
		pnl.add(new JLabel("6-10"));
		pnl.add(new JLabel("11-14"));
		pnl.add(new JLabel("15-17"));
		pnl.add(new JLabel("18-20"));
		
		for (int i = 0; i < cagetories.length; i++) {
			btnGrArr[i] = new ButtonGroup();
			pnl.add(new JLabel(cagetories[i]));
			for (int j = 0; j < 6; j++)
				btnGrArr[i].add((AbstractButton) pnl.add(new JRadioButton()));
		}
	}

}
