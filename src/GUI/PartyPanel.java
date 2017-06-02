package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gameObjects.Follower;
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
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Data.DataSaver;
import Data.DataStorage;
import Player.PLAYER;

public class PartyPanel extends JPanel{
	JLabel slot1 = new JLabel();
	JLabel slot2 = new JLabel();
	JLabel slot3 = new JLabel();
	JLabel slot4 = new JLabel();
	JLabel slot5 = new JLabel();

	JLabel description = new JLabel();
	JTextArea lore = new JTextArea();
	DefaultListModel<String> characterInventory = new DefaultListModel<String>();

	public PartyPanel(){
		setLayout(null);
		populateList();

		addInventoryList("Followers", characterInventory, 50, 50, 400, 500);

		addLabels(slot1, 1, 850, 350);
		addLabels(slot2, 2, 850, 400);
		addLabels(slot3, 3, 850, 450);
		addLabels(slot4, 4, 850, 500);
		addLabels(slot5, 5, 850, 550);
		updateLabels();
		
		description.setBounds(500, 30, 200, 50);
		description.setFont(new Font("Arial", Font.PLAIN, 16));
		add(description);
		lore.setBounds(500, 80, 800, 500);
		lore.setEditable(false);
		lore.setOpaque(false);
		add(lore);
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
		    	Follower c = DataStorage.getCharacter(tokens[0].trim());
			    updateLore(c);
		    }
		}});
		JScrollPane inventoryListScroll = new JScrollPane(inventoryList);
		inventoryListScroll.setBounds(x, y, width, height);
		JLabel inventoryLabel = new JLabel(label);
		inventoryLabel.setBounds(x, y - 25, width, 25);
		add(inventoryListScroll);
		add(inventoryLabel);
		addButton(inventoryList, "Slot 1", 1, x, y + height + 10);
		addButton(inventoryList, "Slot 2", 2, x + 80, y + height + 10);
		addButton(inventoryList, "Slot 3", 3, x + 160, y + height + 10);
		addButton(inventoryList, "Slot 4", 4, x + 240, y + height + 10);
		addButton(inventoryList, "Slot 5", 5, x + 320, y + height + 10);
	}

	private void addButton(JList<String> invList, String text, int slotID, int x, int y){
		JButton inventoryButton = new JButton(text);
		inventoryButton.setBounds(x, y, 75, 30);
		inventoryButton.addActionListener(new ActionListener() {	 
			public void actionPerformed(ActionEvent e){	
				Follower cha = DataStorage.getCharacter(invList.getSelectedValue());
				if (PLAYER.characters.characterInventory.contains(cha) && cha != null){
					PLAYER.characters.deEquipCharacter(slotID);
					PLAYER.characters.equipCharacter(cha, slotID);
					populateList();
					updateLabels();
					DataSaver.SAVE();
				}
			}}); 
		add(inventoryButton);
	}

	private void updateLabels(){
		if (PLAYER.characters.slot1 != null) slot1.setText("Slot 1 : " + PLAYER.characters.slot1.name);
		else slot1.setText("Slot 1 : EMPTY");
		if (PLAYER.characters.slot2 != null) slot2.setText("Slot 2 : " + PLAYER.characters.slot2.name);
		else slot2.setText("Slot 2 : EMPTY");
		if (PLAYER.characters.slot3 != null) slot3.setText("Slot 3 : " + PLAYER.characters.slot3.name);
		else slot3.setText("Slot 3 : EMPTY");
		if (PLAYER.characters.slot4 != null) slot4.setText("Slot 4 : " + PLAYER.characters.slot4.name);
		else slot4.setText("Slot 4 : EMPTY");
		if (PLAYER.characters.slot5 != null) slot5.setText("Slot 5 : " + PLAYER.characters.slot5.name);
		else slot5.setText("Slot 5 : EMPTY");
	}
	
	private void updateLore(Follower cha){
		description.setText(cha.description);
		lore.setText(cha.lore);
	}
	
	private void addLabels(JLabel label, int slotID, int x, int y){
		label.setBounds(x, y, 300, 25);
		add(label);	 	
		JButton labelButton = new JButton("Dismiss");
		labelButton.setBounds(x + 220, y, 100, 30);
		labelButton.addActionListener(new ActionListener() {	 
			public void actionPerformed(ActionEvent e)
			{	
				PLAYER.characters.deEquipCharacter(slotID);
				populateList();
				updateLabels();
			}}); 
		JButton infoButton = new JButton("Info");
		infoButton.setBounds(x + 125, y, 75, 30);
		infoButton.addActionListener(new ActionListener() {	 
			public void actionPerformed(ActionEvent e)
			{	
				Follower cha = PLAYER.characters.getFollower(slotID);
				if (cha != null) updateLore(cha);
			}}); 
		add(labelButton);
		add(infoButton);
	}


	public void populateList(){
		characterInventory.removeAllElements();
		for (gameObjects.Follower c : PLAYER.characters.characterInventory){
			characterInventory.addElement(c.name);
		}
	}

}
