package little_ui;

import javax.swing.JPanel;

abstract class APane extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static final Launcher parent = Launcher.frame;
	
	public APane() {
		super();
	}
	
	public abstract void makeEvents();
	public abstract void killEvents();
}
