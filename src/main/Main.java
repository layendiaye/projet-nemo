package main;

import View.View;
import model.NXDTDController;
import model.NXDevice;
import model.NXGroup;
import model.NXModel;
import model.NXSession;

public class Main {
	public static void main(String[] args) {

		// creer le model
		NXModel model = new NXModel();
		// creer la session courante du model
		NXSession session = new NXSession();
		model.currentSession = session;
		View view = new View(model);
		// creer un appareil et ajoute � la session

		NXDTDController appareil1 = new NXDTDController();
		appareil1.alias = "appareil 1";
		appareil1.family = "NXAMP";
		session.addDevice(appareil1);// [session.devices.length]=appareil1;
		NXDTDController appareil2 = new NXDTDController();
		appareil2.alias = "appareil 2";
		appareil2.family = "NINA";
		session.addDevice(appareil2);
		NXDTDController appareil3 = new NXDTDController();
		appareil3.alias = "appareil 3";
		appareil3.family = "XAMP";
		session.addDevice(appareil3);
		NXDevice appareil4 = new NXDTDController();
		appareil4.alias = "appareil 4";
		appareil4.family = "XAMP'";
		session.addDevice(appareil4);
		NXDevice appareil5 = new NXDevice();
		appareil5.alias = "appareil 5";
		appareil5.family = "NXAPPT";
		session.addDevice(appareil5);
		// creer un groupe et ajouter � la session
		NXGroup groupe1 = new NXGroup();
		groupe1.name = "Groupe 1";
		session.addGroup(groupe1);// [session.groups.length]=groupe1;
		NXGroup groupe2 = new NXGroup();
		groupe2.name = "Groupe 2";
		session.addGroup(groupe2);
		NXGroup groupe3 = new NXGroup();
		groupe3.name = "Groupe 3";
		session.addGroup(groupe3);
		// ajouter appareil au groupe
		groupe1.addDevice(appareil1);
		groupe1.addDevice(appareil2);
		groupe1.addDevice(appareil3);
		groupe2.addDevice(appareil3);
		groupe2.addDevice(appareil1);
		groupe2.addDevice(appareil4);
		groupe3.addDevice(appareil4);
		view.getContentPane();
		view.setVisible(true);

		/*
		 * UsbManager monManager=new UsbManager(); monManager.lire();
		 * monManager.ecrire();
		 */

	}

}
