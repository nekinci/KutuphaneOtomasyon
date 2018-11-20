package com.org.kutuphane;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class Adminbar extends JPanel{
	
	public Adminbar(Mediator mediator) {
		this.mediator=mediator;
		mediator.uyeEkle("adminbar", this);
		initComponents();
	}
	
	//Methods
	
	private void initComponents() {
		setLayout(null);
		setBounds(0,70,691,407);
		setVisible(false);
		setBorder(new TitledBorder("Admin Paneli"));
		setBackground(Color.CYAN);
		
		lblKullaniciAdi = new JLabel("Kullanıcı Adı: ");
		lblEskiSifre = new JLabel("Eski Şifre: ");
		lblYeniSifre = new JLabel("Yeni Şifre: ");
		lblYeniSifreOnay = new JLabel("Şifre Onay: ");
		txtKullaniciAdi = new JTextField();
		txtEskiSifre = new JTextField();
		txtYeniSifre = new JTextField();
		txtYeniSifreOnay = new JTextField();
		btnOnay = new JButton("Onayla");
		
		lblKullaniciAdi.setBounds(205, 128, 100, 25);
		lblEskiSifre.setBounds(205, 158, 100, 25);
		lblYeniSifre.setBounds(205, 188, 100, 25);
		lblYeniSifreOnay.setBounds(205,218,100,25);
		txtKullaniciAdi.setBounds(312,128,150,25);
		txtEskiSifre.setBounds(312, 158, 150, 25);
		txtYeniSifre.setBounds(312,188,150,25);
		txtYeniSifreOnay.setBounds(312,218,150,25);
		btnOnay.setBounds(362,248,100,25);
		
		add(lblKullaniciAdi);
		add(lblEskiSifre);
		add(lblYeniSifre);
		add(lblYeniSifreOnay);
		add(txtEskiSifre);
		add(txtYeniSifre);
		add(txtYeniSifreOnay);
		add(txtKullaniciAdi);
		add(btnOnay);
		
		btnOnay.addActionListener((ActionEvent e)->{
			Veritabani.kullaniciGuncelle(txtKullaniciAdi.getText(),txtEskiSifre.getText(),txtYeniSifreOnay.getText());
		});
	}
	
	//Variables
	private Mediator mediator;
	private JLabel lblKullaniciAdi;
	private JLabel lblEskiSifre;
	private JLabel lblYeniSifre;
	private JLabel lblYeniSifreOnay;
	
	private JTextField txtKullaniciAdi;
	private JTextField txtEskiSifre;
	private JTextField txtYeniSifre;
	private JTextField txtYeniSifreOnay;
	
	private JButton btnOnay;
}
