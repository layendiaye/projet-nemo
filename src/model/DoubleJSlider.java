package model;

import javax.swing.JSlider;

public class DoubleJSlider extends JSlider {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final int scale;

	public DoubleJSlider(int min, int max, int value, int scale) {
		super(min, max, value);
		this.scale = scale;
	}

	public double getScaledValue() {
		return ((double) super.getValue()) / this.scale;
	}
}
