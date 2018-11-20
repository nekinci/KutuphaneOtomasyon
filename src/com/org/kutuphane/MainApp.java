package com.org.kutuphane;

import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainApp extends JFrame{
	//Constructors
	public MainApp() {
		initComponents();
	}
	
	//Methods
	private void initComponents() {
		setLayout(null);
		setBounds((width-691)/2,(height-477)/2,691, 477);
		setVisible(true);
		setTitle("Kütüphane Otomasyon Programı");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mediator = new Mediator();
		top=new TopBar(mediator);
		personbar = new PersonBar(mediator);
		addbar=new Addbar(mediator);
		menu=new Menubar(mediator);
		login=new Loginbar(mediator);
		editbar=new Editbar(mediator);
		rentbar = new Rentbar(mediator);
		adminbar = new Adminbar(mediator);
		top.setVisible(true);
		menu.setVisible(false);
		addbar.setVisible(false);
		login.setVisible(true);
		editbar.setVisible(false);
		personbar.setVisible(false);
		
		
		add(top);
		add(login);
		add(menu);
		add(addbar);
		add(editbar);
		add(personbar);
		add(rentbar);
		add(adminbar);
	}
	
	//Variables
	private TopBar top;
	private Loginbar login;
	private Menubar menu;
	private Addbar addbar;
	private Editbar editbar;
	private PersonBar personbar;
	private Rentbar rentbar;
	private Adminbar adminbar;
	private Mediator mediator;
	
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(()->{new MainApp();});
		
	}
	
	//Final Variables
	private static final int width=GraphicsEnvironment.getLocalGraphicsEnvironment().
			getDefaultScreenDevice().getDisplayMode().getWidth();
	private static final int height=GraphicsEnvironment.getLocalGraphicsEnvironment().
			getDefaultScreenDevice().getDisplayMode().getHeight();
}

