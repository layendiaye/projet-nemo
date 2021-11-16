package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class updateDeviceTable extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	NXSession session=new NXSession();
	public ArrayList<NXDevice> devices; //= new ArrayList<NXDevice>();
 
    
    public NXModel model;
	public updateDeviceTable(NXModel m)
    {
        super();
        this.model=m;
        devices=new ArrayList<NXDevice>();
    }
	String[] entete = {"ALIAS", "FAMILY"};
    public int getRowCount() 
    {
        return devices.size();
    }
 
    public int getColumnCount() 
    {
        return entete.length;
    }
 
    public String getColumnName(int columnIndex) 
    {
        return entete[columnIndex];
    }
 
   public Object getValueAt(int rowIndex, int columnIndex)
    {
        switch(columnIndex)
        {
      
        case 0:return devices.get(rowIndex).alias;
        case 1:return devices.get(rowIndex).family;
        }
		return columnIndex;
    }
 
   public boolean isCellEditable(int rowIndex, int columnIndex)
   {
	if(columnIndex==1)
	{
       return false; 
	}else
	return true;
   }
   public  void setValueAt(Object value,  int rowIndex, int columnIndex) 
   {
       if(value != null)
       {
       	 NXDevice device =devices.get(rowIndex);
    
           switch(columnIndex)
           {
              case 0:

                   device.alias=(String) value;
                   break;
                 
           }
       }
   }
   
   public  void upDateDataSource( int[] selectedRows)
   {
	   devices.clear();
	   if(selectedRows.length==0)
	   {
		  
		   devices.addAll(model.currentSession.devices);
	   }
	   else {
		   // On veut savoir si le tableau selectedRows contient 0
		   boolean tab=false; // tab sera true si selectedRows contient 0
		   for(int i=0;i<selectedRows.length; i++)
		   {
			   if(selectedRows[i]==0)
			   {
				   // La ligne i de selectedRows vaut 0, donc tab est true, on peut quitter la boucle.
				 tab=true; 
				 break;
			   }
				   
		   }
		   // Est-ce que tab est true (c'est � dire est-ce que selectedRows contient 0) ?
		   if(tab==true)
		   {
			   devices.addAll(model.currentSession.devices);   
		   }
		   
		   	else
		   		{
		   		
	   
		   		for(int i=0;i<selectedRows.length; i++)
		   		{
		   			NXGroup group=model.currentSession.groups.get(selectedRows[i]-1);
		   			devices.addAll(group.devices);
		   		}
		   	}
	   
	   	}
	   fireTableDataChanged();
	   
   	}
}



