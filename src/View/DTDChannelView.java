package View;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.AffineTransformer;
import model.DoubleJSlider;
import model.NXChannel;

public class DTDChannelView extends JPanel implements ChangeListener, Observer, ActionListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// View view;
	public DoubleJSlider slider1;
	public DoubleJSlider slider2;
	public JTextField textA, textB, textC, textD;
	JLabel label1 = new JLabel("Gain");
	JLabel label2 = new JLabel("Delay");
	JLabel label3 = new JLabel();
	JLabel label4 = new JLabel("Xover");
	JButton muteButton;
	NXChannel channel;
	Vumetre vum;
	AffineTransformer gainTransformer;
	AffineTransformer dlyTransformer;
	MenuDeroul crossover;
	final DecimalFormat df = new DecimalFormat("0.0");

	public DTDChannelView() {
		slider1 = new DoubleJSlider(JSlider.HORIZONTAL, 36, 18, 1);
		slider1.setMajorTickSpacing(0);
		slider1.setMinorTickSpacing(3);
		slider1.setPaintTicks(true);
		slider1.setPaintLabels(true);
		slider1.setPreferredSize(new Dimension(150, 20));
		slider1.addChangeListener(this);
		slider1.setName("slider1");
		textA = new JTextField();
		textA.setPreferredSize(new Dimension(35, 20));
		textA.setText(df.format(slider1.getValue()));// sliderA.getValue() + " " );
		textA.addKeyListener(this);

		slider2 = new DoubleJSlider(JSlider.HORIZONTAL, 600, 0, 1);
		slider2.setMajorTickSpacing(0);
		slider2.setMinorTickSpacing(0);
		slider2.setPaintTicks(true);
		slider2.setPaintLabels(true);
		slider2.setPreferredSize(new Dimension(150, 20));
		slider2.addChangeListener(this);
		slider2.setName("slider2");
		textB = new JTextField();
		textB.setPreferredSize(new Dimension(35, 20));
		textB.setText(df.format(slider2.getValue()));
		textB.addKeyListener(this);
		vum = new Vumetre();
		vum.setPreferredSize(new Dimension(150, 10));
		crossover = new MenuDeroul(this);

		try {
			Image img;
			img = ImageIO.read(getClass().getResource("image/icon.JPG"));
			muteButton = new JButton(new ImageIcon(img.getScaledInstance(25, 25, Image.SCALE_DEFAULT)));
			muteButton.setPreferredSize(new Dimension(30, 25));
			muteButton.addActionListener(this);
			Image img1 = ImageIO.read(getClass().getResource("image/iconSelected.png"));
			muteButton.setSelectedIcon(new ImageIcon(img1.getScaledInstance(25, 25, Image.SCALE_DEFAULT)));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Frame

		this.setLayout(new GridLayout(3, 1)); // configuration en 3 ligne et 1 colonne.
		JPanel gaindel = new JPanel(new GridLayout(2, 1));// panel pour contenir le gain, et delay

		gaindel.add(label1);// ajout d'une etiquette apres slider1 et se positionne automatique sur la
							// premiere ligne
		gaindel.add(slider1);
		JPanel y = new JPanel(new GridBagLayout());// avec le GridBagLayout les composants se place les un apres les
													// autres donc juste apres le label1
		y.add(textA);
		gaindel.add(y);

		gaindel.add(label2);
		gaindel.add(slider2);
		JPanel x = new JPanel(new GridBagLayout());
		x.add(textB);
		gaindel.add(x);
		this.add(gaindel);

		// panel pour contenir le vumetre, et le crossover
		JPanel vmcr = new JPanel(new GridLayout(2, 1));
		vmcr.add(label3);
		vmcr.add(vum);
		JPanel v = new JPanel(new GridBagLayout());
		textC = new JTextField();
		textC.setPreferredSize(new Dimension());/*
												 * textfield vide permettant d'avoir le meme allignement que les
												 * composant du panel vmcr
												 */
		v.add(textC);
		vmcr.add(v);

		vmcr.add(label4);
		vmcr.add(crossover);
		JPanel v1 = new JPanel(new GridBagLayout());
		textD = new JTextField();
		textD.setPreferredSize(new Dimension());
		v1.add(textD);
		vmcr.add(v1);
		this.add(vmcr);

		// panel pour contenir le bouton mute uniquement
		JPanel mutebt = new JPanel(new GridBagLayout());
		mutebt.add(muteButton);
		this.add(mutebt);

		// le transformer.
		gainTransformer = new AffineTransformer(0, 36, -6, 6);
		dlyTransformer = new AffineTransformer(0, 600, 0, 200);
	}

	public void stateChanged(ChangeEvent evt) {
		JSlider source;
		source = (JSlider) evt.getSource();
		if (source.getName().equals("slider1")) {
			if (channel != null) {
				channel.setGain(slider1.getValue());

			}

		}

		if (source.getName().equals("slider2")) {
			// textB.setText( df.format(slider2.getScaledValue()));//. source.getValue() + "
			// " );
			if (channel != null) {
				channel.setDelay(slider2.getValue());
			}
		}
	}

	@Override
	public void update(Observable ch, Object arg1) {// observé
		updateGain();
		updateDelay();
		updateMute();
		updateVumetre();
		updateCrossover();

	}

	private void updateVumetre() {
		vum.setValue(channel.level);

	}

	public void updateGain() {

		if (channel != null) {

			double tValue = gainTransformer.transform(channel.gain);
			slider1.setValue(channel.gain);

			if (!textA.isFocusOwner()) {
				textA.setText(df.format(tValue));
			}

		}
	}

	private void updateDelay() {

		if (channel != null) {
			double DValue = dlyTransformer.transform(channel.delay);
			slider2.setValue(channel.delay);
			if (!textB.isFocusOwner()) {
				textB.setText(df.format(DValue));
			}
		}

	}

	private void updateMute() {
		if (channel != null) {

			muteButton.setSelected(channel.mute);

		}

	}

	private void updateCrossover() {

		if (channel != null) {
			if (crossover.combo.getSelectedIndex() != channel.crossover) {
				crossover.combo.setSelectedIndex(channel.crossover);
				// System.out.println(channel.crossover);
			}
		}

	}

	public void setChannel(NXChannel nxChannel) {
		if (channel != null) {
			channel.deleteObserver(this);// arrete d'observer l'ancien cannal
		}

		channel = nxChannel;

		if (channel != null) {
			channel.addObserver(this);// observe le nouveau cannal(le canal courant)
		}

		updateGain();
		updateDelay();
		updateMute();
		updateCrossover();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source;
		source = e.getSource();
		if (source.equals(muteButton)) {

			if (channel != null) {
				boolean selectionCourrante = muteButton.isSelected(); // retourne vrai si le bouton est selectionner
				boolean selectionAttendue = !selectionCourrante; // on veut changer l'�tat du mute
				channel.setMute(selectionAttendue);

			}
		} else if (source.equals(crossover.combo)) {
			if (channel != null) {
				int selection = crossover.combo.getSelectedIndex();
				channel.setCrossover(selection);
				System.out.printf("menuderoulant: %d,channel: %d\n", crossover.combo.getSelectedIndex(),
						channel.crossover);
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent ev) {

		if (ev.getKeyCode() == KeyEvent.VK_ENTER) {
			if (ev.getSource() == textA) {
				if (channel != null) {

					String x = textA.getText();
					double vext = Double.valueOf(x.replaceAll(",", "."));
					// double vext=Double.parseDouble(x.replaceAll(",",",","."));
					int vint = (int) gainTransformer.reverseTransform(vext);
					channel.setGain(vint);

					double tValue = gainTransformer.transform(channel.gain);
					textA.setText(df.format(tValue));

					// slider1.setValue(0);
					// int value = Integer.parseInt(x);
					// channel.setGain(Integer.parseInt(x)); //slider1.setValue(value);

				}
			}
		}
		if (ev.getKeyCode() == KeyEvent.VK_ENTER) {
			if (ev.getSource() == textB) {
				if (channel != null) {
					String s = textB.getText();
					double vextD = Double.valueOf(s.replaceAll(",", "."));
					int vintD = (int) dlyTransformer.reverseTransform(vextD);
					channel.setDelay(vintD);

					double DValue = (dlyTransformer.transform(channel.delay));
					textB.setText(df.format(DValue));

					// int value = Integer.parseInt(saisie);
					// channel.setCrossover(value); //setValue(value);
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}
