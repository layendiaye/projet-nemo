package View;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Vumetre extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int posY = 10;
	int max = 15;
	int min = 0;
	int value = 0;
	int hc = 10;
	int mc = 5;
	// double mc=(int) 4.6;
	int wc = 5;
	int nCells = 10;
	int posX;

	public Vumetre() {
		super();

	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		int is = value * nCells / (max - min);
		int i = 0;
		for (; i < nCells; i++) {
			// posX=(int) (i*(wc+mc));
			posX = i * (wc + mc);

			if (i < is) {
				if (i < 0.5 * nCells) {
					g.setColor(Color.blue);

				} else if (i < 0.8 * nCells) {
					g.setColor(Color.yellow);

				} else {
					g.setColor(Color.red);

				}
			} else {

				g.setColor(Color.lightGray);
			}
			g.fillRect(posX, posY, wc, hc);
			// System.out.printf("%d %d %d %d", posX,posY,wc,hc);

		}

		// la pause trois milli�mes de seconde

		/*
		 * try { Thread.sleep(3); } catch (InterruptedException e) {
		 * e.printStackTrace(); }
		 */

	}

	public void setValue(int v) {
		value = v;
		this.repaint();

	}

}
