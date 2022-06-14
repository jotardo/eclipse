package little_ui.pane;

import javax.swing.JPanel;

import little_ui.Launcher;

public abstract class APane extends JPanel {

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
