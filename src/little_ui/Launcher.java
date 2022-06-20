package little_ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import little_ui.pane.APane;
import little_ui.pane.ContentPane;
import little_ui.pane.ResultPane;
import little_ui.pane.TitlePane;
import little_ui.util.AlphaContainer;

public class Launcher extends JFrame {
	private class Background extends JPanel {
		private static final long serialVersionUID = 1L;
		private Image bg = new ImageIcon("res/a.jpg").getImage();
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Rectangle rect = g.getClipBounds();
			g.drawImage(bg, rect.x, rect.y, rect.width, rect.height, null);
		}
	
	}

	private static final long serialVersionUID = 1L;
	public static Launcher frame;
	private static APane titlePane, contentPane, resultPane;
	private static APane currentPane;
	private static JPanel bgPanel, contentPanel;
	private static AlphaContainer alphaStuff;
	private static JLayeredPane panelHolder = new JLayeredPane();

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

	public Launcher() {
		super("QUESTION GAME");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setPreferredSize(new Dimension(1100, 600));
		getContentPane().add(panelHolder);
		initMenuBar();
		panelHolder.setLayout(null);
		panelHolder.add(bgPanel = new Background(), new Integer(0));
		bgPanel.setBounds(0, 0, getContentPane().getPreferredSize().width, getContentPane().getPreferredSize().height);
		panelHolder.add(alphaStuff = new AlphaContainer(contentPanel = new JPanel()), new Integer(1));
		contentPanel.setLayout(null);
		contentPanel.setBackground(new Color(0, 0, 0, 0));
		contentPanel.setBounds(0, 0, getContentPane().getPreferredSize().width, getContentPane().getPreferredSize().height);
		alphaStuff.setBounds(contentPanel.getBounds());

		pack();
		setLocationRelativeTo(null);
		
		frame = this;
	}

	public static void main(String[] args) {
		try {
			LookAndFeel a = new NimbusLookAndFeel();
			a.getDefaults().put("TitledBorder.font", a.getDefaults().getFont("TitledBorder.font").deriveFont(Font.ITALIC + Font.BOLD, 15f));
			a.getDefaults().put("defaultFont",
					a.getDefaults().getFont("defaultFont").deriveFont(14f));
			UIManager.setLookAndFeel(a);
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		titlePane = new TitlePane();
		contentPane = new ContentPane();
		resultPane = new ResultPane();
		new Launcher().setVisible(true);
		showTitlePane();
		
//		javax.swing.UIDefaults a = UIManager.getLookAndFeelDefaults();
//		a.forEach((key, value) -> {System.out.println(key + " :: " + value + "\n");});
	}
	
	public static void showTitlePane() {
		switchPane(titlePane);
	}

	public static void showContentPane() {
		switchPane(contentPane);
	}

	public static void showResultPane() {
		switchPane(resultPane);
	}

	private static void switchPane(APane targetPane) {
		if (targetPane == null) {
			System.out.println("Warning : There are no pane to switch to");
			return;
		}
		if (currentPane != null) {
			currentPane.killEvents();
			contentPanel.remove(currentPane);
		}
		contentPanel.add(currentPane = targetPane, BorderLayout.CENTER);
		currentPane.setBounds((int) contentPanel.getBounds().getCenterX() - targetPane.getWidth() / 2, (int) contentPanel.getBounds().getCenterY() - targetPane.getHeight() / 2, targetPane.getWidth(), targetPane.getHeight());
		contentPanel.revalidate();
		contentPanel.repaint();
		currentPane.makeEvents();
	}
}
