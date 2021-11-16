package model;

import java.util.ArrayList;

public class NXSession {
	public ArrayList<NXGroup> groups;
	public ArrayList<NXDevice> devices;
	public NXSession()
	{
		groups=new ArrayList<NXGroup>();
		devices=new ArrayList<NXDevice>();
	}
	public void addDevice(NXDevice device)
	{
	devices.add(device);
	
	}
	public void addGroup(NXGroup group)
	{
		groups.add(group);
	}
	public void removeGroup(NXGroup group)
	{
		groups.remove(group);
	}
	public void removeDevice(NXDevice device )
	{
		devices.remove(device);
		for(NXGroup group:groups)
		{
			group.removeDevice(device);
		}
	}
}
