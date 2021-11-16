package View;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LabelView extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel Alias, Family;
	JTextField f, t2;
	public LabelView()
	{
		this.setLayout(new GridLayout(1,2));
		this.setBackground(Color.RED);
		JPanel tt=new JPanel(new GridBagLayout());
		Alias=new JLabel("ALIAS");
		tt.add(Alias);
		tt.add(new Label());
		tt.setBackground(Color.pink);
		t2=new JTextField(30);
		t2.setEditable(false);
		tt.add(t2);
		
		JPanel t=new JPanel(new GridBagLayout());
		Family=new JLabel("FAMILY    ");
		t.add(Family); 
		t.setBackground(Color.pink);
		f=new JTextField(30);
		f.setEditable(false);
		t.add(f);
		
		this.add(tt);
		this.add(t);
		
	}
	

}
