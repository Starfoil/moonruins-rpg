package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gameObjects.ResourceObject;
import gameObjects.ResourcePackage;
import items.*;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Data.DataStorage;
import Player.PLAYER;

public class ResourcePanel extends JPanel{
	JLabel gold = new JLabel();
	JLabel silver = new JLabel();
	JLabel copper = new JLabel();
	JLabel fire = new JLabel();
	JLabel key = new JLabel();

	JLabel description = new JLabel();
	JLabel resourceLabel = new JLabel();
	DefaultListModel<String> resourceInventory = new DefaultListModel<String>();

	public ResourcePanel(){
		setLayout(null);
		populateList();

		addInventoryList("Inventory", resourceInventory, 50, 50, 300, 500);

		addLabels(copper, 1000, 50);
		addLabels(silver, 1000, 75);
		addLabels(gold, 1000, 100);
		
		addLabels(fire, 1000, 500);
		addLabels(key, 1000, 525);
		
		addLabels(resourceLabel, 400, 50);
		resourceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		addLabels(description, 400, 80);
		
		updateLabels();
	}


	private void addInventoryList(String label, DefaultListModel<String> inv, int x, int y, int width, int height){
		JList<String> inventoryList = new JList<String>(inv);
		inventoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		inventoryList.addListSelectionListener(new ListSelectionListener(){
			@SuppressWarnings("unchecked")
			public void valueChanged(ListSelectionEvent ev){
		    String s =(((JList<String>) ev.getSource()).getSelectedValue());
		    if (s != null){
		    	String[] tokens = s.split(" ");
		    	ResourceObject r = DataStorage.getResource(tokens[0].trim());
		    	if (r != null) updateDescription(r);
		    }
		}});
		JScrollPane inventoryListScroll = new JScrollPane(inventoryList);
		inventoryListScroll.setBounds(x, y, width, height);
		JLabel inventoryLabel = new JLabel(label);
		inventoryLabel.setBounds(x, y - 25, width, 25);
		add(inventoryListScroll);
		add(inventoryLabel);
		JButton inventoryButton = new JButton("Sell");
		inventoryButton.setBounds(x, y + height + 10, 75, 30);
		inventoryButton.addActionListener(new ActionListener() {	 
			public void actionPerformed(ActionEvent e)
			{	
			}}); 
		add(inventoryButton);
	}

	private void updateLabels(){
		copper.setText("Copper : " + Integer.toString(PLAYER.coins.copper));
		silver.setText("Silver : " + Integer.toString(PLAYER.coins.silver));
		gold.setText("Gold : " + Integer.toString(PLAYER.coins.gold));

		fire.setText("Fire : " + Integer.toString(PLAYER.misc.fire));
		key.setText("Keys : " + Integer.toString(PLAYER.misc.keys));
	}
	
	private void updateDescription(ResourceObject resource){
		resourceLabel.setText(resource.name);
		description.setText(resource.description);
	}

	private void addLabels(JLabel label, int x, int y){
		label.setBounds(x, y, 300, 25);
		add(label);	 	
	}
	
	public void updatePanel(){
		updateLabels();
		populateList();
	}

	public void populateList(){
		resourceInventory.removeAllElements();
		for (ResourcePackage r : PLAYER.inventory.resources){
			resourceInventory.addElement(r.toString());
		}
	}

}
