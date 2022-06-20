package little_ui.thread;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class TimeThread extends Thread {

	public static boolean exist = false;
	private int hour, minute, second, milisecond;
	private int timeLeftMS;
	private JLabel timeLabel;
	private JProgressBar progressBar;

	public TimeThread(int secondInput, JLabel timeLabel, JProgressBar pBar) {
		this.timeLabel = timeLabel;
		this.progressBar = pBar;
		if (secondInput > 0) {
			hour = secondInput / 3600;
			minute = (secondInput - hour * 3600) / 60;
			second = secondInput - hour * 3600 - minute * 60;
			this.timeLeftMS = secondInput * 1000;
			this.progressBar.setMaximum(timeLeftMS);
			updateTime();
		}
		exist = true;
	}
	
	private void updateTime() {
		if (timeLabel != null) {
			timeLabel.setBackground((hour <= 0 && minute <= 0 && second <= 15) ? Color.red : Color.BLUE);
			timeLabel.setText(String.format("%02d%c%02d%c%02d", hour, ':', minute, ':', second));
		}
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
		if (hour <= 0 && minute <= 0 && second <= 0 && milisecond <= 0) {
			throw new InterruptedException("Interrupt (Forced)");
		}
		else {
			milisecond--;
			if (milisecond < 0) {
				second--;
				if (second < 0) {
					minute--;
					if (minute < 0) {
						hour--;
						minute = 59;
					}
					second = 59;
				}
				milisecond = 999;
			}
		}
	}
}
