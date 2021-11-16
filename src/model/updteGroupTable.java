package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import View.SelectorView;

			public class updteGroupTable extends AbstractTableModel
			{
				    /**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				NXSession session=new NXSession();
				

					public ArrayList<NXGroup> groups = new ArrayList<NXGroup>();
					public SelectorView view;
				    public NXModel model;
					public updteGroupTable(NXModel m, SelectorView v)
				    {
				        super();
				        this.model=m;
				        this.view=v;
				    }
					String[] entete2 = {"NAME", "NUMBER_OF_DEVICES"};
				    public int getRowCount() 
				    {
				        return model.currentSession.groups.size()+1;
				    }
				 
				    public int getColumnCount() 
				    {
				        return entete2.length;
				    }
				 
				    public String getColumnName(int columnIndex) 
				    {
				        return entete2[columnIndex];
				    }
				 
				   public Object getValueAt(int rowIndex, int columnIndex)
				    {
					   if (rowIndex==0)
					  
						   switch(columnIndex)
					   {
				    case 0:return "All Devices";
			        case 1:return Integer.toString(model.currentSession.devices.size());
					  
					   }
			        else
				        switch(columnIndex)
				        {
				      
				        case 0:return model.currentSession.groups.get(rowIndex-1).getName();
				        case 1:return Integer.toString(model.currentSession.groups.get(rowIndex-1).getNumberOfDevice());
				        }
						return columnIndex;
				    }
				 
				    public void addGroup(NXGroup group) 
				    {
				    	model.currentSession.groups.add(group);
				    	view.upDateGroupMenu();
				        fireTableRowsInserted(model.currentSession.groups.size() -1,model.currentSession.groups.size()-1);
				    }
				   
				    public void removeGroup(NXGroup selection)
				    {
				    	model.currentSession.groups.remove(selection);
				    	view.upDateGroupMenu();
				        fireTableRowsDeleted(model.currentSession.groups.size() -1,model.currentSession.groups.size()-1);
				    } 
				        
				    public boolean isCellEditable(int rowIndex, int columnIndex)
				    {
				    	if(columnIndex==1)
				    	{
				    		return false;
				    	}
				        return true; 
				    }
				   
				    public  void setValueAt(Object value,  int rowIndex, int columnIndex) 
				    {
				    	if(rowIndex==0)
				    	{
				    		return;
				    	}
				    	 if(value != null)
			        	 {
				        	 NXGroup group =model.currentSession.groups.get(rowIndex-1);
				     
				        	
				        		 
				            switch(columnIndex) 
				           
				        {
				               case 0:
		
				                    group.setName((String) value);
				                    break;
				                   
				               // case 1:
				                //	group.setNumberOfDevice((String) value);
				                //	break;
				            }
				           
				        }
				    }
				   
				}




