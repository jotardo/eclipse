package swings.ex_8;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

class ScrollTime extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ScrollTime() {
		super("JScrollPane Demo");
		setSize(350, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		String[] cagetories = { "Household", "Office", "Extended family", "Company (US)", "Comapny (World)", "Team",
				"Will", "Birthday Card List", "High School", "Country", "Continent", "Planet" };
		JList<String> list = new JList<>(cagetories);
		JScrollPane scrollPane = new JScrollPane(list);
		add(scrollPane);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new ScrollTime();
	}

}
