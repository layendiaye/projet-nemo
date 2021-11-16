package View;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import model.NXGroup;
import model.NXModel;
	    public class AddAction extends AbstractAction {
	        /**
			 * 
			 */
	    		    	public SelectorView view;
	    		    	public NXModel model;
	    				private static final long serialVersionUID = 1L;

	    				public AddAction(NXModel m, SelectorView v)
	    				{
	    		            super("Add_Group");
	    		           
	    		           model=m;
	    		           view=v;
	    		            
	    		        }
	    				public void actionPerformed(ActionEvent e) {
	    		        	NXGroup group=new NXGroup();
	    		           group.name="new group";
	    		           view.exemple.addGroup(group);
	    		 
	    		        }
	    		    }
	    	
