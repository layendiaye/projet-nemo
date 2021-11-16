package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.NXChannel;
import model.NXDTDController;
import model.NXDevice;
import model.NXModel;

public class EditorView extends JPanel implements ListSelectionListener
{

	/**
	 * 
	 */
	DTDChannelView channel1, channel2,channel3;
	private static final long serialVersionUID = 1L;
	public NXModel model;
	public SelectorView selector;
	ArrayList<NXChannel> channels;
	public LabelView lab;
	NXDTDController controller;
	EditorView(NXModel m)
	{
		//this.model=m;
		this.setLayout(new BorderLayout());
		
		this.model=m;
		lab=new LabelView();
		this.add(lab,BorderLayout.NORTH);
		JPanel channelPanel=new JPanel(new GridLayout());
		channel1=new DTDChannelView();
		channel2=new DTDChannelView();
		channel3=new DTDChannelView();
		channelPanel.add(channel1);
		channelPanel.add(channel2);
		channelPanel.add(channel3);
		this.add(channelPanel,BorderLayout.CENTER);
	}
	public void setSelector(SelectorView sv)
	{
		selector=sv;
		ListSelectionModel listSelectionModel = selector.tableauModul.getSelectionModel();        
        listSelectionModel.addListSelectionListener(this);
        upDateSelection();
	}
	@Override
	public void valueChanged(ListSelectionEvent arg0) 
	{
		
		upDateSelection();
		//System.out.println(arg0);
	}
	public void upDateSelection()
	{
		NXDevice[] devices=selector.getSelectedDevices();
		if(devices.length==0)
		{
			lab.t2.setText("no selected");
			lab.f.setText("no selected");
			lab.t2.setBackground(Color.red);
			lab.f.setBackground(Color.red);
			channel1.setChannel(null);
			channel2.setChannel(null);
			channel3.setChannel(null);
		}
		else if(devices.length==1)
		{
			lab.t2.setText(devices[0].alias);
						
			lab.f.setText(devices[0].family);
			if(devices[0] instanceof NXDTDController)
			{
				NXDTDController device=(NXDTDController)devices[0];
				channel1.setChannel(device.channels.get(0));
				channel2.setChannel(device.channels.get(1));
				channel3.setChannel(device.channels.get(2));
				lab.t2.setBackground(Color.pink);
				lab.f.setBackground(Color.pink);
			}
			
			
		}
				
					else
		{
			lab.t2.setText("multiple");
			lab.f.setText("Multiple");
			
			channel1.setChannel(null);
			channel2.setChannel(null);
			channel3.setChannel(null);
			lab.t2.setBackground(Color.red);
			lab.f.setBackground(Color.red);
		}
	}
	
}

