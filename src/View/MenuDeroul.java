package View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuDeroul extends JPanel implements ActionListener

{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	JComboBox combo=new JComboBox();
	private String[] vCross={"160 - 185 hz","40 - 60 hz","60 - 130 hz","130 - 160 hz","160 - 185 hz"};

	public MenuDeroul(ActionListener crs)
	{
	    /*setSize(300,80);
		JPanel pan=new JPanel();
		this.setContentPane(pan);*/
		combo=new JComboBox(vCross);
		combo.setEditable(true);				
		combo.setMaximumRowCount(5);//nbre de valeur visible
		combo.setPreferredSize( new Dimension(90,20));
		this.add(combo);
		combo.addActionListener(crs);
		//this.setLayout(new GridLayout(1,2));
		//JPanel sliderPanel=new JPanel(new GridBagLayout());
	    //this.pack();
		//setVisible(true);
		    
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
/*	public static void main (String[] args)
	{
		
		MenuDeroul fen =new MenuDeroul();
		fen.setVisible(true);
	}*/
}




