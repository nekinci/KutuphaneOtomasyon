package com.org.kutuphane;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.ButtonUI;

public class Menubar extends JPanel {


	//Constructors
	public Menubar(Mediator mediator) {
		setLayout(null);
		setBounds(0,70,691,407);
		setBorder(new TitledBorder("Ana Menü"));
		setBackground(Color.cyan);
		this.mediator=mediator;
		mediator.uyeEkle("menubar", this);
		initComponents();
	}
	
	//Methods
	private void initComponents() {
		setVisible(false);
		
		menuImage=new JLabel(Icons.getIcon("res/kitapMenu.jpg", 0,10, 297, 325));
		btnKayit=new JButton("Kitap Kaydı",Icons.getIcon("res/ekle.png", 20, 32, 36, 36));
		btnDuzenle=new JButton("Düzenle",Icons.getIcon("res/edit.png",20, 32, 36, 36));
		btnKira=new JButton("Kitap Kirala",Icons.getIcon("res/calendar-1.png", 20, 32, 36, 36));
		btnUyeler=new JButton("Üyeler",Icons.getIcon("res/users.png", 20, 32, 36, 36));
		btnAdmin=new JButton("Admin Panel",Icons.getIcon("res/diamond.png", 79, 39, 36, 36	));
		
		btnKayit.setBounds(320, 30, 172, 100);
		btnDuzenle.setBounds(498,30,172,100);
		btnKira.setBounds(320, 135, 172, 100);
		btnUyeler.setBounds(498,135,172,100);
		btnAdmin.setBounds(320, 240, 351, 115);
		menuImage.setBounds(5, 20, 317, 407);
		
		btnKayit.setBackground(Color.decode("#ffffff"));
		btnDuzenle.setBackground(Color.decode("#a67538"));
		btnKira.setBackground(Color.decode("#a67538"));
		btnUyeler.setBackground(Color.WHITE);
		btnAdmin.setBackground(Color.decode("#ff4820"));
		
		btnDuzenle.setForeground(Color.white);
		btnKira.setForeground(Color.white);
		btnAdmin.setForeground(Color.white);
		
		btnKayit.setHorizontalAlignment(SwingUtilities.RIGHT);
		btnDuzenle.setHorizontalAlignment(SwingUtilities.CENTER);
		btnKira.setHorizontalAlignment(SwingUtilities.RIGHT);
		btnUyeler.setHorizontalAlignment(SwingUtilities.CENTER);
		btnAdmin.setHorizontalAlignment(SwingUtilities.CENTER);
		
		add(menuImage);
		add(btnKayit);
		add(btnDuzenle);
		add(btnKira);
		add(btnUyeler);
		add(btnAdmin);
		
		//Listeners
		
		btnKayit.addActionListener((ActionEvent e)->{
			setVisible(false);
			mediator.panelGoster("addbar");
		});
		
		btnDuzenle.addActionListener((ActionEvent e)->{
			setVisible(false);
			mediator.panelGoster("editbar");
		});
		
		btnUyeler.addActionListener((ActionEvent e)->{
			setVisible(false);
			mediator.panelGoster("personbar");
		});
		
		btnKira.addActionListener((ActionEvent e)->{
			setVisible(false);
			mediator.panelGoster("rentbar");
		});
		
		btnAdmin.addActionListener((ActionEvent e)->{
			setVisible(false);
			mediator.panelGoster("adminbar");
		});
	}
	
	//Variables
	private JLabel menuImage;
	private JButton btnKayit;
	private JButton btnDuzenle;
	private JButton btnKira;
	private JButton btnUyeler;
	private JButton btnAdmin;
	private Mediator mediator;
	
}
