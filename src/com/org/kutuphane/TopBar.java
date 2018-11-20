package com.org.kutuphane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TopBar extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public TopBar(Mediator mediator) {
		setBounds(0, 0, 691, 70);
		width=this.getSize().width;
		initComponents();
		this.mediator=mediator;
		mediator.uyeEkle("topbar", this);
	}

	//Methods 
	private void initComponents() {
		setLayout(null);
		setBackground(Color.CYAN);
		
		icon=new JLabel(Icons.getIcon("/home/nekinci/Masaüstü/11.png",0,0, 48, 48));
		title=new JLabel("Kütüphane Otomasyon Programı");
		dateAndTime=new JLabel(LocalDateTime.now().getYear()+"/"+LocalDateTime.now().getMonthValue()+"/"
				+LocalDateTime.now().getDayOfMonth()+" "+LocalDate.now().getDayOfWeek());
		
		icon.setBounds(10, 15, 48, 48);
		title.setBounds(65,22,width-20,35);
		dateAndTime.setBounds(535, 40, width-525, 35);

		title.setFont(Fonts.getFont(Fonts.OPENSANS, 24));
		
		add(icon);
		add(title);
		add(dateAndTime);
		
	}
	//Variables
	private JLabel icon;
	private JLabel title;
	private JLabel dateAndTime;
	private int width;
	private Mediator mediator;
}
