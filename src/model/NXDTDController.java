package model;

import java.util.ArrayList;

public class NXDTDController extends NXDevice {
	public ArrayList<NXChannel> channels;

	public NXDTDController() {

		channels = new ArrayList<NXChannel>();
		for (int i = 0; i < 3; i++) {
			NXChannel channel = new NXChannel(i + 1);
			channels.add(channel);
		}
	}

}
