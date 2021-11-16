package View;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import model.NXModel;
public class RemoveAction extends AbstractAction {
    
	
	/**
	 * 
	 */
	public SelectorView view;
	public NXModel model;
	private static final long serialVersionUID = 1L;
	public RemoveAction(NXModel m, SelectorView v) {
        super("Remove_Group");
        model=m;
        view=v;
    }
	
    public void actionPerformed(ActionEvent e) 
    {
    	int[] selectedRows=view.tableauGroup.getSelectedRows();
       for(int i = selectedRows.length-1 ; i >= 0; i--)
       {
if (selectedRows[i]!=0)
           view.exemple.removeGroup(model.currentSession.groups.get(selectedRows[i]-1));
        }
    }
    
}




