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

import location.Connection;
import location.Location;
import locationGather.GatherLocation;
import Data.DataSaver;
import Data.DataStorage;
import Player.PLAYER;

public class TravelPanel extends JPanel{
	JLabel location = new JLabel();
	JTextArea description = new JTextArea();
	
	JLabel built = new JLabel();
	JLabel travelCost = new JLabel();
	JTextArea buildCost = new JTextArea();
	JLabel travelStatus = new JLabel();
	
	JButton explore = new JButton("Explore");
	JLabel exploreStatus = new JLabel();
	
	DefaultListModel<String> connection = new DefaultListModel<String>();

	public TravelPanel(){
		setLayout(null);
		setupObjects();
		
		location.setBounds(50, 30, 400, 50);
		location.setFont(new Font("Arial", Font.PLAIN, 20));
		add(location);
		addTextBox(description, 50, 80, 700, 400);
		
		
		addConnectionList("Travel", connection, 850, 50, 300, 250);
		addLabels(built, 850, 300);
		addLabels(travelCost, 850, 325);
		travelStatus.setBounds(850, 550, 500, 50);
		travelStatus.setFont(new Font("Arial", Font.PLAIN, 20));
		add(travelStatus);
		addTextBox(buildCost, 850, 400, 300, 100);
		populateList();
		updateLocation();
	}
	
	private void setupObjects(){
		exploreStatus.setBounds(50, 450, 600, 150);
		exploreStatus.setFont(new Font("Arial", Font.PLAIN, 24));
		explore = new JButton("Explore");
		explore.setBounds(200, 400, 140, 70);
		explore.addActionListener(new ActionListener() {	 
			public void actionPerformed(ActionEvent e){	
				exploreStatus.setText(PLAYER.gather());
				DataSaver.SAVE();
			}}); 
		add(explore);
		add(exploreStatus);
	}
	
	
	private void addConnectionList(String label, DefaultListModel<String> inv, int x, int y, int width, int height){
		JList<String> inventoryList = new JList<String>(inv);
		JButton travelButton = new JButton("Travel");
		JButton buildButton = new JButton("Build");
		inventoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		inventoryList.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent ev){
		    @SuppressWarnings("unchecked")
			String s =(((JList<String>) ev.getSource()).getSelectedValue());
		    if (s != null){
		    	Connection c = DataStorage.getConnection(PLAYER.location.name, s);
		    	if (c != null){
		    		updateLabels(c);
		    		if (c.isBuilt){
		    			travelButton.setVisible(true);
		    			buildButton.setVisible(false);
		    		}
			    	else {
			    		travelButton.setVisible(false);
			    		buildButton.setVisible(true);
			    	}
		    	}    	
		    }
		}});
		travelButton.setBounds(1075, 310, 75, 30);
		travelButton.setVisible(false);
		travelButton.addActionListener(new ActionListener() {	 
			public void actionPerformed(ActionEvent e){	
				Location travelTo = DataStorage.getLocation(inventoryList.getSelectedValue());
				travelStatus.setText(PLAYER.travel(travelTo));
				populateList();
				updateLocation();
			}}); 
		add(travelButton);
		buildButton.setBounds(1075, 310, 75, 30);
		buildButton.setVisible(false);
		buildButton.addActionListener(new ActionListener() {	 
			public void actionPerformed(ActionEvent e){	
				Connection conn = DataStorage.getConnection(PLAYER.location.name, inventoryList.getSelectedValue());
				travelStatus.setText(PLAYER.buildConnection(conn));
				populateList();
			}}); 
		add(buildButton);
		JScrollPane inventoryListScroll = new JScrollPane(inventoryList);
		inventoryListScroll.setBounds(x, y, width, height);
		JLabel inventoryLabel = new JLabel(label);
		inventoryLabel.setBounds(x, y - 25, width, 25);
		add(inventoryListScroll);
		add(inventoryLabel);
	}

	private void addTextBox(JTextArea box, int x, int y, int width, int height){
		box.setBounds(x, y, width, height);
		box.setEditable(false);
		box.setOpaque(false);
		add(box);
	}
	
	private void updateLabels(Connection c){
		if (c.isBuilt){
			built.setText("This connection is built");
			travelCost.setText("Travel cost : " + c.travelCost);
			buildCost.setText("");
		}else{
			built.setText("This connection is NOT built yet");
			travelCost.setText("Cannot travel yet");
			buildCost.setText("Cost to build connection : \n\n" + c.displayCost());
		}
	}
	
	private void updateLocation(){
		location.setText("Current Location : " + PLAYER.location.name);
		description.setText(PLAYER.location.description);
		exploreStatus.setText("");
		if (PLAYER.location instanceof GatherLocation){
			explore.setVisible(true);
		}else{
			explore.setVisible(false);
		}
	}
	
	private void addLabels(JLabel label, int x, int y){
		label.setBounds(x, y, 300, 25);
		add(label);	 	
	}


	public void populateList(){
		connection.removeAllElements();
		for (Connection c : PLAYER.location.connections){
			if (c.locationA.name != PLAYER.location.name){
				connection.addElement(c.locationA.name);
			}else{
				connection.addElement(c.locationB.name);
			}
			
		}
	}

}
