package View;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenuItem;

import model.NXDevice;
import model.NXGroup;
import model.NXModel;

public class CopyAction extends AbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NXModel model;
	public  SelectorView view;
	public NXGroup group;
	JMenuItem ml;
	public CopyAction(NXModel m, SelectorView v, NXGroup g)
	{
		super();
		this.model=m;
		this.view =v;
		this.group=g;
		 
			
	}
	public void actionPerformed(ActionEvent e) 
	{
		int[] selected=view.tableauModul.getSelectedRows();
		
		for(int i=0;i<selected.length; i++)
	  {
			NXDevice device=view.app.devices.get(selected[i]);
			group.addDevice(device);
	  }	
		view.exemple.fireTableDataChanged();
	}

}


