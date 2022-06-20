package little_ui.util;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class TimeThread extends Thread {

	public static boolean exist = false;
	private int h, m, s, ms;
	private final char separator = ':';
	private JLabel timeLabel;
	private JProgressBar progressBar;
	private int timeLeftMS;

	public TimeThread(int second, JLabel timeLabel, JProgressBar pBar) {
		this.timeLabel = timeLabel;
		this.progressBar = pBar;
		if (second > 0) {
			h = second / 3600;
			m = (second - h * 3600) / 60;
			s = second - h * 3600 - m * 60;
			this.timeLeftMS = second * 1000;
			this.progressBar.setMaximum(timeLeftMS);
			updateTime();
		}
		exist = true;
	}
	
	private void updateTime() {
		if (timeLabel != null)
			timeLabel.setText(String.format("%02d%c%02d%c%02d", h, separator, m, separator, s));
		if (progressBar != null)
			progressBar.setValue(timeLeftMS);
	}

	@Override
	public void run() {
		while (true) {
			try {
				tick();
				updateTime();
				sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
				exist = false;
				break;
			}
		}
		System.out.println("Xong rồi");
	}

	private void tick() throws InterruptedException {
		timeLeftMS--;
		if (h <= 0 && m <= 0 && s <= 0 && ms <= 0) {
			throw new InterruptedException("Interrupt (Forced)");
		}
		else {
			ms--;
			if (ms < 0) {
				s--;
				if (s < 0) {
					m--;
					if (m < 0) {
						h--;
						m = 59;
					}
					s = 59;
				}
				ms = 999;
			}
		}
	}
}
