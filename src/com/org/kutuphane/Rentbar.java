package com.org.kutuphane;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Rentbar extends JPanel{
	
	public Rentbar(Mediator mediator) {
		setBounds(0,70,691,407);
		try {
			initComponents();
		}catch(Exception ex) { ex.printStackTrace();}
		this.mediator=mediator;
		mediator.uyeEkle("rentbar", this);
	}
	
	//Methods
	
	private void initComponents() throws SQLException, Exception {
		
		setBorder(new TitledBorder("Kira Ekranı"));
		setBackground(Color.CYAN);
		setVisible(false);
		setLayout(null);
		

		
		tblKitap = new JTable(new CustomTableModel(Veritabani.kitapCek("")));
		tblUye = new JTable(new CustomTableModel(Veritabani.uyeCek("")));
		tabbedPane = new JTabbedPane(2);
		pnlKirala = new JPanel();
		lblSeciliKitap = new JLabel("Şu anda seçili kitap yok!");
		lblSeciliKisi = new JLabel("Şu anda seçili kişi yok!");
		btnKirala = new JButton("Kirala");
		btnGeri = new JButton("<");
		
		pnlKirala.setBackground(Color.CYAN);
		pnlKirala.setVisible(true);
		pnlKirala.setLayout(null);
		
		tblKitap.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tblUye.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tblKitap.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblUye.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		paneKitap = new JScrollPane(tblKitap);
		paneUye = new JScrollPane(tblUye);
		
		tabbedPane.addTab("Kirala", Icons.getIcon("res/play-button.png", 8, 8, 8, 8), pnlKirala);
		tabbedPane.setTabPlacement(SwingUtilities.BOTTOM);

		paneKitap.setBounds(35,20,300, 200);
		paneUye.setBounds(355,20,300,200);
		tabbedPane.setBounds(5,15,685,390);
		pnlKirala.setBounds(5,15,685,390);
		lblSeciliKitap.setBounds(0, 250, 691, 20);
		lblSeciliKisi.setBounds(0, 280, 691, 20);
		btnKirala.setBounds(270,310,151,25);
		btnGeri.setBounds(165, 310, 100, 25);
		
		lblSeciliKitap.setHorizontalAlignment(SwingUtilities.CENTER);
		lblSeciliKisi.setHorizontalAlignment(SwingUtilities.CENTER);
		
		btnKirala.setBackground(Color.decode("#6ee3ee"));
		btnGeri.setBackground(Color.decode("#6ee3ee"));
		
		pnlKirala.add(paneKitap);
		pnlKirala.add(paneUye);
		pnlKirala.add(lblSeciliKitap);
		pnlKirala.add(lblSeciliKisi);
		pnlKirala.add(btnKirala);
		pnlKirala.add(btnGeri);
		
		add(tabbedPane);
		
		tblKitap.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				lblSeciliKitap.setText(tblKitap.getModel().getValueAt(tblKitap.getSelectedRow(), 2).toString());
				kitapID = tblKitap.getModel().getValueAt(tblKitap.getSelectedRow(), 0).toString();
			}
		});
		
		tblUye.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				String isim = tblUye.getModel().getValueAt(tblUye.getSelectedRow(), 2).toString();
				String soyisim = tblUye.getModel().getValueAt(tblUye.getSelectedRow(), 3).toString();
				lblSeciliKisi.setText(isim+" "+soyisim);
				kisiID = tblUye.getModel().getValueAt(tblUye.getSelectedRow(), 0).toString();
			}
		});
		
		btnKirala.addActionListener((ActionEvent e)->{
			int bid = Integer.parseInt(kitapID);
			int pid = Integer.parseInt(kisiID);
			String kitap = lblSeciliKitap.getText().trim();
			
			int i=JOptionPane.showConfirmDialog(this, kitap+" kitabını kiralamak istediğinize emin misiniz?","Kirala",JOptionPane.YES_NO_OPTION);
			System.out.println(i);
			if(i==0)
				Veritabani.kitapKirala(bid, pid, kitap);
		});
		
		btnGeri.addActionListener((ActionEvent e)->{
			setVisible(false);
			mediator.panelGoster("menubar");
		});
			
	}
	
	//Variables
	private Mediator mediator;
	private JTable tblKitap;
	private JTable tblUye;
	private JScrollPane paneKitap;
	private JScrollPane paneUye;
	private JTabbedPane tabbedPane;
	private JPanel pnlKirala;
	private JLabel lblSeciliKitap;
	private JLabel lblSeciliKisi;
	private JButton btnKirala;
	private JButton btnGeri;
	
	private String kitapID;
	private String kisiID;
	
}
