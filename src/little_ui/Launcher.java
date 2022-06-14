package little_ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import little_ui.pane.APane;
import little_ui.pane.ContentPane;
import little_ui.pane.ResultPane;
import little_ui.pane.TitlePane;

public class Launcher extends JFrame implements ContainerListener{
	
	public static Launcher frame;
	private static final long serialVersionUID = 1L;
	private static APane titlePane;
	private static APane contentPane;
	private static APane resultPane;
	private APane currentPane = null;
	
	public Launcher() {
		super("QUESTION GAME");
		try {
			UIManager.setLookAndFeel(new javax.swing.plaf.nimbus.NimbusLookAndFeel());
			UIManager.getLookAndFeelDefaults().put("defaultFont", new javax.swing.plaf.FontUIResource("SansSerif", Font.PLAIN, 15));
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		initMenuBar();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1100, 700);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setVisible(true);
		getContentPane().addContainerListener(this);
	}
	
	private void initMenuBar() {
		JMenuBar a = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu help = new JMenu("Help");
		a.add(file);
		a.add(help);
		file.add(new JMenuItem());
		help.add(new JMenuItem());
		setJMenuBar(a);
	}
	
	public void showTitlePane() {
		if (currentPane != null)
			remove(currentPane);
		add(currentPane = titlePane);
	}
	
	public void showContentPane() {
		if (currentPane != null)
			remove(currentPane);
		add(currentPane = contentPane);
	}
	
	public void showResultPane() {
		if (currentPane != null)
			remove(currentPane);
		add(currentPane = resultPane);
	}

	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		frame = new Launcher();
		titlePane = new TitlePane();
		contentPane = new ContentPane();
		resultPane = new ResultPane();
		frame.showContentPane();
		
		UIDefaults a = UIManager.getLookAndFeelDefaults();
		a.forEach((key, value) -> {System.out.println(key + " :: " + value + "\n");});
	}

	@Override
	public void componentAdded(ContainerEvent e) {
		if (e.getChild().equals(currentPane))
			currentPane.makeEvents();
		revalidate();
		repaint();
	}

	@Override
	public void componentRemoved(ContainerEvent e) {
		if (e.getChild().equals(currentPane))
			currentPane.killEvents();
		revalidate();
		repaint();
	}
}
