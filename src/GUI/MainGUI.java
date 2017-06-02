package GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.Resources;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Data.DataLoader;
import Data.DataSaver;
import Data.DataStorage;


public class MainGUI extends JFrame{
	
	//static Frame thegame;
	
	private static final int XFRAME = 1200;
	private static final int YFRAME = 675;
	
	public static void setButton(JButton x, String file, int locX, int locY, int sizeX, int sizeY){
		ImageIcon e = new ImageIcon("resources\\"+file);
		final Image img = e.getImage();
		x.setIcon(new ImageIcon(img));
		x.setFocusPainted(false);
	    x.setRolloverEnabled(true);
	    x.setBorderPainted(false);
	    x.setContentAreaFilled(false);
		x.setLocation(locX, locY);
		x.setSize(sizeX,sizeY);
	}
	
	public static void openFrame(JTabbedPane pane){
		JFrame frame = new JFrame("Game");
		frame.add(pane);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setBounds(20, 20, XFRAME , YFRAME);
		frame.setVisible(true);
	}
	

	public static void main(String[] args) throws IOException {
		DataLoader.LOAD();
		JTabbedPane jtp = new JTabbedPane();
		DataStorage.unlockAll();
		
		
		//jtp.addTab("Main", mainPanel);
		ResourcePanel RP = new ResourcePanel();
		jtp.addTab("Equipment", new EquipmentPanel());
		jtp.addTab("Inventory", RP);
		jtp.addTab("Party", new PartyPanel());
		jtp.addTab("Travel", new TravelPanel());
		jtp.addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		        RP.updatePanel();
		        DataSaver.SAVE();
		    }
		});
		
		
		openFrame(jtp);
		
	}
		

}
