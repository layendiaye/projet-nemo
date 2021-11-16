package model;

import java.util.Observable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

//import javax.swing.Timer;
import javax.swing.text.View;

import View.DTDChannelView;

@SuppressWarnings("unused")
public class NXChannel extends Observable// observé
{
	View view;
	DTDChannelView tr;
	public int channelId;
	public int gain;
	public int delay;
	public boolean mute;
	public int level;
	public int crossover;
	public Timer timer;

	public NXChannel(int i) {
		this.channelId = i;
		tr = new DTDChannelView();
		timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				int l;
				// for( l=10;i<=20;)
				// {
				Random rd = new Random();
				l = rd.nextInt(16);
				setLevel(l);
				// }
			}
		};
		timer.scheduleAtFixedRate(task, 0, 100);
	}

	public void setGain(int g) {
		int maxGain = 36;
		int minGain = 0;
		if (g != gain) {
			gain = Math.max(minGain, Math.min(maxGain, g));

			this.setChanged();
			this.notifyObservers(gain);
		}
	}

	public void setDelay(int c) {
		int maxDelay = 600;
		int minDelay = 0;
		if (c != delay) {
			delay = Math.max(minDelay, Math.min(maxDelay, c));
			setChanged();
			this.notifyObservers(delay);
		}
	}

	public void setMute(boolean m) {

		if (m != mute) {
			mute = m;
			this.setChanged();
			this.notifyObservers(mute);
		}

	}

	public void setLevel(int l) {

		int maxLevel = 16;
		int minLevel = 0;
		// int l1=minLevel + (int)(Math.random() * (16));

		if (l != level) {
			level = Math.max(minLevel, Math.min(maxLevel, l));

			this.setChanged();
			this.notifyObservers(level);
			// System.out.println(level);
		}

	}

	public void setCrossover(int cr) {

		if (cr != crossover) {
			crossover = cr;
			this.setChanged();
			this.notifyObservers(crossover);
		}

	}

}
