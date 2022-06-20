package little_ui.pane;

import javax.swing.JPanel;

public abstract class APane extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public APane() {
		super();
	}
	public abstract void makeEvents();
	public abstract void killEvents();
}
