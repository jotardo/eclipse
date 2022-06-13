package little_ui;

import java.awt.BorderLayout;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

class Launcher extends JFrame implements ContainerListener{
	
	static Launcher frame;
	private static final long serialVersionUID = 1L;
	private static APane titlePane;
	private static APane contentPane;
	private static APane resultPane;
	private APane currentPane = null;
	
	public Launcher() {
		super("ATGT");
		try {
			UIManager.setLookAndFeel(new javax.swing.plaf.nimbus.NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		//setAlwaysOnTop(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1100, 700);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setVisible(true);
		getContentPane().addContainerListener(this);
	}
	
	void showTitlePane() {
		if (currentPane != null)
			remove(currentPane);
		add(currentPane = titlePane);
	}
	
	void showContentPane() {
		if (currentPane != null)
			remove(currentPane);
		add(currentPane = contentPane);
	}
	
	void showResultPane() {
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
