package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gameObjects.Stats;
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

public class EquipmentPanel extends JPanel{
	JLabel leftringLabel = new JLabel();
	JLabel rightringLabel = new JLabel();
	JLabel glovesLabel = new JLabel();
	JLabel bootsLabel = new JLabel();
	JLabel pendantLabel = new JLabel();
	JLabel talismanLabel = new JLabel();
	JTextArea statsLabel = new JTextArea();
	
	JLabel hands = new JLabel();
	JLabel magic = new JLabel();

	DefaultListModel<String> gloveInventory = new DefaultListModel<String>();
	DefaultListModel<String> bootInventory = new DefaultListModel<String>();
	DefaultListModel<String> pendantInventory = new DefaultListModel<String>();
	DefaultListModel<String> ringInventory = new DefaultListModel<String>();
	DefaultListModel<String> talismanInventory = new DefaultListModel<String>();
	
	Font font = new Font("Georgia", Font.PLAIN, 20);

	public EquipmentPanel(){
		setLayout(null);
		populateList();
		addStatLabels(hands, 800, 500);
		addStatLabels(magic, 800, 550);

		addInventoryList("Rings", 0, ringInventory, 50, 50, 300, 100);
		addInventoryList("Gloves", 2, gloveInventory, 50, 250, 300, 100);
		addInventoryList("Boots", 3, bootInventory, 50, 450, 300, 100);
		addInventoryList("Pendants", 4, pendantInventory, 450, 50, 300, 100);
		addInventoryList("Talisman", 5, talismanInventory, 450, 250, 300, 100);

		addLabels(leftringLabel, 0, 800, 50);
		addLabels(rightringLabel, 1, 800, 100);
		addLabels(glovesLabel, 2, 800, 150);
		addLabels(bootsLabel, 3, 800, 200);
		addLabels(pendantLabel, 4, 800, 250);
		addLabels(talismanLabel, 5, 800, 300);
		statsLabel = new JTextArea();
		statsLabel.setBounds(450, 450, 300, 100);
		statsLabel.setFont(font);
		statsLabel.setOpaque(false);
		add(statsLabel);
		updateLabels();
	}


	private void addInventoryList(String label, int slotID, DefaultListModel<String> inv, int x, int y, int width, int height){
		JList<String> inventoryList = new JList<String>(inv);
		inventoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		inventoryList.addListSelectionListener(new ListSelectionListener(){
			@SuppressWarnings("unchecked")
			public void valueChanged(ListSelectionEvent ev){
				String s =(((JList<String>) ev.getSource()).getSelectedValue());
				Item i = DataStorage.getItem(s);
				if (s != null) updateTextField(i.stats);
			}});
		JScrollPane inventoryListScroll = new JScrollPane(inventoryList);
		inventoryListScroll.setBounds(x, y, width, height);
		JLabel inventoryLabel = new JLabel(label);
		inventoryLabel.setBounds(x, y - 25, width, 25);
		add(inventoryListScroll);
		add(inventoryLabel);
		if (slotID == 0){ //ring inventory
			JButton leftButton = new JButton("Equip Left");
			JButton rightButton = new JButton("Equip Right");
			leftButton.setBounds(x, y + height + 10, 100, 30);
			rightButton.setBounds(x + 110, y + height + 10, 100, 30);
			leftButton.addActionListener(new ActionListener() {	 
				public void actionPerformed(ActionEvent e)
				{	
					Item item = DataStorage.getItem(inventoryList.getSelectedValue());
					if (PLAYER.equips.itemInventory.contains(item) && item != null){
						PLAYER.equips.deEquip(0);
						PLAYER.equips.equip(item, 0);
						populateList();
						updateLabels();
					}
				}}); 
			rightButton.addActionListener(new ActionListener() {	 
				public void actionPerformed(ActionEvent e)
				{	
					Item item = DataStorage.getItem(inventoryList.getSelectedValue());
					if (PLAYER.equips.itemInventory.contains(item) && item != null){
						PLAYER.equips.deEquip(1);
						PLAYER.equips.equip(item, 1);
						populateList();
						updateLabels();
					}
				}});
			add(leftButton);
			add(rightButton);
		}else{ 
			JButton inventoryButton = new JButton("Equip");
			inventoryButton.setBounds(x, y + height + 10, 75, 30);
			inventoryButton.addActionListener(new ActionListener() {	 
				public void actionPerformed(ActionEvent e)
				{	
					Item item = DataStorage.getItem(inventoryList.getSelectedValue());
					if (PLAYER.equips.itemInventory.contains(item) && item != null){
						PLAYER.equips.deEquip(slotID);
						PLAYER.equips.equip(item, slotID);
						populateList();
						updateLabels();
					}
				}}); 
			add(inventoryButton);
		}
	}
	
	private void addStatLabels(JLabel label, int x, int y){
		label.setBounds(x, y, 400, 50);
		label.setFont(new Font("Arial", Font.PLAIN, 20));
		add(label);
	}
	
	private void updateTextField(Stats s){
		statsLabel.setText(s.toString());
	}

	private void updateLabels(){
		if (PLAYER.equips.leftRing != null){
			leftringLabel.setText("Left Ring : " + PLAYER.equips.leftRing.name);
		}else{
			leftringLabel.setText("Left Ring : EMPTY");
		}
		if (PLAYER.equips.rightRing != null){
			rightringLabel.setText("Right Ring : " + PLAYER.equips.rightRing.name);
		}else{
			rightringLabel.setText("Right Ring : EMPTY");
		}
		if (PLAYER.equips.gloves != null){
			glovesLabel.setText("Gloves : " + PLAYER.equips.gloves.name);
		}else{
			glovesLabel.setText("Gloves : EMPTY");
		}
		if (PLAYER.equips.boots != null){
			bootsLabel.setText("Boots : " + PLAYER.equips.boots.name);
		}else{
			bootsLabel.setText("Boots : EMPTY");
		}
		if (PLAYER.equips.pendant != null){
			pendantLabel.setText("Pendant : " + PLAYER.equips.pendant.name);
		}else{
			pendantLabel.setText("Pendant : EMPTY");
		}
		if (PLAYER.equips.talisman != null){
			talismanLabel.setText("Talisman : " + PLAYER.equips.talisman.name);
		}else{
			talismanLabel.setText("Talisman : EMPTY");
		}
		PLAYER.processPlayerStats();
		hands.setText("Resource Multiplier : " + Double.toString(PLAYER.stat.multiplier));
		magic.setText("Magic Find : " + Double.toString(PLAYER.stat.magicFind));
		DataSaver.SAVE();
	}

	private void addLabels(JLabel label, int slotID, int x, int y){
		label.setBounds(x, y, 300, 25);
		add(label);
		JButton labelButton = new JButton("Unequip");
		labelButton.setBounds(x + 250, y, 100, 30);
		labelButton.addActionListener(new ActionListener() {	 
			public void actionPerformed(ActionEvent e)
			{	
				PLAYER.equips.deEquip(slotID);
				populateList();
				updateLabels();
			}}); 
		add(labelButton);	
	}


	public void populateList(){
		gloveInventory.removeAllElements();
		bootInventory.removeAllElements();
		pendantInventory.removeAllElements();
		ringInventory.removeAllElements();
		talismanInventory.removeAllElements();
		for (Item i : PLAYER.equips.itemInventory){
			if(i instanceof Glove){
				gloveInventory.addElement(i.name);
			}
			else if(i instanceof Boot){
				bootInventory.addElement(i.name);
			}
			else if(i instanceof Pendant){
				pendantInventory.addElement(i.name);
			}
			else if(i instanceof Ring){
				ringInventory.addElement(i.name);
			}
			else if(i instanceof Talisman){
				talismanInventory.addElement(i.name);
			}
		}
	}

}
