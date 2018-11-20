package com.org.kutuphane;

import java.util.HashMap;

import javax.swing.JPanel;

public class Mediator {
	
	public Mediator() {
		
	}
	
	//Methods
	
	public void uyeEkle(String pnlName,JPanel pnl) {
		components.put(pnlName, pnl);
	}
	
	public void panelGoster(String pnlName) {
		if(!components.containsKey(pnlName)) {
			System.out.println("Böyle bir öge yok!");
		}
		components.get(pnlName).setVisible(true);
		
	}
	
	//Variables
	private HashMap<String, JPanel> components = new HashMap<>();
	
}
