package little_ui;

import java.awt.Graphics;

import javax.swing.JLabel;

class TimeLabel extends Thread {

	private static int h = 0;
	private static int m = 0;
	private static int s = 0;
	private int realH;
	private int realM;
	private int realS;
	private static char separator = ':';
	private JLabel timeLabel;

	public TimeLabel(int second, JLabel timeLabel) {
		this.timeLabel = timeLabel;
		setClock(second);
	}

	void setClock(int second) {
		if (second > 0) {
			h = realH = second / 3600;
			m = realM = (second - h * 3600) / 60;
			s = realS = second - h * 3600 - m * 60;
			timeLabel.setText(String.format("%02d%c%02d%c%02d", h, separator, m, separator, s));
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				tick();
				timeLabel.setText(String.format("%02d%c%02d%c%02d", realH, separator, realM, separator, realS));
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
		System.out.println("Done");
	}

	private void tick() throws InterruptedException {
		if (realH <= 0 && realM <= 0 && realS <= 0) {
			throw new InterruptedException("Interrupt (Forced)");
		}
		realS--;
		if (realS < 0) {
			realM--;
			if (realM < 0) {
				realH--;
				realM = 59;
			}
			realS = 59;
		}
		sleep(1000);
	}
}
