package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.NXDevice;
import model.NXGroup;
import model.NXModel;
import model.updateDeviceTable;
import model.updteGroupTable;

public class SelectorView extends JPanel implements ListSelectionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NXModel model;
	public updteGroupTable exemple;
	public JTable tableauGroup, tableauModul;
	public updateDeviceTable app;
	public JMenu menu;
	DTDChannelView channels;

	public SelectorView(NXModel m) {
		super();
		this.model = m;

		this.setLayout(new BorderLayout());
		this.setBackground(Color.green);

		JPanel panel = new JPanel(new GridLayout(2, 1));

		JPanel top = new JPanel(new BorderLayout());
		String[] d = { "DEVICES", "GROUPS" };
		String[][] g = new String[0][0];
		JTable entete = new JTable(g, d);
		top.add(entete);
		top.add(entete.getTableHeader(), BorderLayout.NORTH);
		top.add(entete, BorderLayout.CENTER);

		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.pink);
		app = new updateDeviceTable(model);
		tableauModul = new JTable(app);
		tableauModul.setBackground(Color.green);
		panel1.add(tableauModul);
		JScrollPane pane1 = new JScrollPane(tableauModul);
		pane1.setPreferredSize(new Dimension(400, 100));
		panel1.add(pane1);

		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.pink);
		exemple = new updteGroupTable(model, this);
		tableauGroup = new JTable(exemple);
		tableauGroup.setBackground(Color.green);
		panel2.add(tableauGroup);
		JScrollPane pane = new JScrollPane(tableauGroup);
		pane.setPreferredSize(new Dimension(400, 100));
		panel2.add(pane);

		JPanel panel3 = new JPanel();
		JMenuBar mbar = new JMenuBar();
		JButton addBouton = new JButton();
		addBouton.setPreferredSize(new Dimension(100, 26));
		menu = new JMenu("Add_to");
		menu.setPreferredSize(new Dimension(125, 20));
		mbar.add(menu);
		panel3.setBackground(Color.YELLOW);
		addBouton.add(mbar);
		panel3.add(addBouton);

		JPanel boutons = new JPanel();
		boutons.setBackground(Color.YELLOW);
		boutons.add(new JButton(new AddAction(model, this)));
		boutons.add(new JButton(new RemoveAction(model, this)));
		panel.add(panel1, BorderLayout.WEST);
		panel.add(panel2, BorderLayout.EAST);
		panel.add(panel3, BorderLayout.SOUTH);
		panel.add(boutons);
		// panel.setBackground(Color.red);
		this.add(top, BorderLayout.NORTH);
		this.add(panel, BorderLayout.CENTER);
		app.upDateDataSource(tableauGroup.getSelectedRows());

		ListSelectionModel listSelectionModel = tableauGroup.getSelectionModel();
		listSelectionModel.addListSelectionListener(this);
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		app.upDateDataSource(tableauGroup.getSelectedRows());

	}

	public void upDateGroupMenu() {
		menu.removeAll();
		for (NXGroup group : model.currentSession.groups) {
			JMenuItem ml = new JMenuItem(group.name);
			menu.add(ml);
			ml.addActionListener(new CopyAction(model, this, group));

		}

	}

	public NXDevice[] getSelectedDevices() {
		int[] selectedRows = tableauModul.getSelectedRows();
		NXDevice[] selectedDevices = new NXDevice[selectedRows.length];
		for (int i = 0; i < selectedRows.length; i++) {
			selectedDevices[i] = app.devices.get(selectedRows[i]);
		}
		return selectedDevices;
	}
	/*
	 * public void setDeviceChannelView(SelectorView dc) { ListSelectionModel
	 * listSelectionModel =tableauModul.getSelectionModel();
	 * listSelectionModel.addListSelectionListener(this); upDateDeviceView(); }
	 * public void upDateDeviceView() {
	 * 
	 * NXDevice[] devices=this.getSelectedDevices(); if(devices.length==1) {
	 * 
	 * }
	 * 
	 * }
	 */

}
