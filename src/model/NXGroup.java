package model;
import java.util.ArrayList;

public class NXGroup {
	public ArrayList<NXDevice> devices;
	public ArrayList<NXGroup> groups;
    public String name;
    public String numberOfDevice;
    NXModel model;
    public NXGroup()
    {
     devices=new ArrayList<NXDevice>();
     groups=new ArrayList<NXGroup>();
    }
    
    public void addDevice(NXDevice device)
    {
    	 if(!devices.contains(device))
    	 {		 
    		 devices.add(device);
    	 }
    	 System.out.println(this.name +"   " + device.alias);
    }
    public void removeDevice(NXDevice device)
    {
    	devices.remove(device);
    }
    public void removeAllDevices(NXDevice device)
    {
      devices.removeAll(devices);
    }

	public String getName() 
	{
		return name;
	}

	public int getNumberOfDevice()
	{
		return devices.size();
	}

	public void setName(String value) 
	{
		name=new String(value);
	}

	public void setNumberOfDevice(String value) 
	{
		numberOfDevice= value;		
	}

}





