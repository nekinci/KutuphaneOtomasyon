package com.org.kutuphane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Editbar extends JPanel {
	//Col=Column=Sütun=Dikey
	//Row=Row=Satır=Yatay
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Constructors
	public Editbar(Mediator mediator) {
		setBounds(0,70,691,407);
		setVisible(true);
		setLayout(null);
		setBorder(new TitledBorder("Düzenleme Paneli"));
		setBackground(Color.cyan);
		initComponents();
		this.mediator=mediator;
		mediator.uyeEkle("editbar", this);
	}
	
	//Methods 
	
	private void textFree() {
		txtBarkod.setText("");
		txtKitapAdi.setText("");
		txtKitle.setText("");
		txtSayfaSayisi.setText("");
		txtTur.setText("");
		txtYayinEvi.setText("");
	}
	
	private void initComponents() { //Olası durumlar için try-catch
		
		try {
			Veritabani.veritabaniAc("jdbc:mysql://localhost:3306/kutuphane", "root", "*5432704*");
			table=new JTable(new CustomTableModel(Veritabani.tabloCek("")));
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		} 
		catch (Exception e) {}
		
		//Tanımlama
		font=Fonts.getFont(Fonts.OPENSANS, 20);
		color=Color.decode("#33e6ee");
		scroll=new JScrollPane(table);
		txtSearch = new JTextField();
		btnSearch = new JButton("Ara");
		txtBarkod=new JTextField();
		txtKitapAdi=new JTextField();
		txtKitle=new JTextField();
		txtSayfaSayisi=new JTextField();
		txtTur=new JTextField();
		txtYayinEvi=new JTextField();
		lblKitapID=new JLabel("Barkod:");
		lblKitapAdi=new JLabel("Kitap Adı:");
		lblKitle=new JLabel("Kitle:");
		lblSayfaSayisi = new JLabel("Sayfa Sayısı:");
		lblTur=new JLabel("Tür:");
		lblYayinevi=new JLabel("Yayınevi:");
		lblImg = new JLabel(Icons.getIcon("res/warning.png", (235-99)/2, (135-99)/2, 99, 99));
		btnGozat = new JButton("Gözat");
		btnGuncelle = new JButton("Güncelle");
		fileChooser = new JFileChooser();
		btnGeri = new JButton("<");
		
		//Konumlandırma
		scroll.setBounds(5,60,400,300);
		txtSearch.setBounds(5, 25, 300, 25);
		btnSearch.setBounds(315, 25, 90, 25);
		txtBarkod.setBounds(515,25,170,25);
		txtKitapAdi.setBounds(515,55,170,25);
		txtKitle.setBounds(515, 85, 170, 25);
		txtSayfaSayisi.setBounds(515, 115, 170, 25);
		txtTur.setBounds(515,145,170,25);
		txtYayinEvi.setBounds(515,175,170,25);
		lblKitapID.setBounds(450,25,100,25);
		lblKitapAdi.setBounds(439,55,100,25);
		lblKitle.setBounds(470,85,100,25);
		lblSayfaSayisi.setBounds(415,115,100,25);
		lblTur.setBounds(480,145,100,25);
		lblYayinevi.setBounds(448, 175, 100, 25);
		lblImg.setBounds(450,205,235,152);
		btnGozat.setBounds(585,365,100,30);
		btnGuncelle.setBounds(450,365,130,30);
		btnGeri.setBounds(310,365,130,30);
		
		//Fonts
		txtBarkod.setFont(font);
		txtKitapAdi.setFont(font);
		txtKitle.setFont(font);
		txtSayfaSayisi.setFont(font);
		txtTur.setFont(font);
		txtYayinEvi.setFont(font);
		txtSearch.setFont(font.deriveFont(Font.BOLD));
		
		//Colors
		txtBarkod.setBackground(color);
		txtKitapAdi.setBackground(color);
		txtKitle.setBackground(color);
		txtSayfaSayisi.setBackground(color);
		txtTur.setBackground(color);
		txtYayinEvi.setBackground(color);
		txtSearch.setBackground(Color.decode("#afbfcf"));
		btnGozat.setBackground(color);
		btnGuncelle.setBackground(color);
		btnSearch.setBackground(color);
		btnGeri.setBackground(color);
		//Optional
		
		lblImg.setBorder(new LineBorder(Color.BLACK));
		//table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(20);
		
		
		//Eklemeler
		add(scroll);
		add(txtSearch);
		add(btnSearch);
		add(txtBarkod);
		add(txtKitapAdi);
		add(txtKitle);
		add(txtSayfaSayisi);
		add(txtTur);
		add(txtYayinEvi);
		add(lblKitapID);
		add(lblKitapAdi);
		add(lblKitle);
		add(lblSayfaSayisi);
		add(lblTur);
		add(lblYayinevi);
		add(lblImg);
		add(btnGozat);
		add(btnGuncelle);
		add(btnGeri);
		//Listeners
		
		//Arama butonu
		
		btnSearch.addActionListener((ActionEvent e)->{
			try {
				table.setModel(new CustomTableModel(Veritabani.tabloCek(txtSearch.getText())));
				textFree();
			} catch (Exception e1) {}});
		
		//Arama TextFieldi - yazarken ara -
		
		txtSearch.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent arg0) {
				try {
					table.setModel(new CustomTableModel(Veritabani.tabloCek(txtSearch.getText())));
					textFree();
				} 
				catch (Exception e) {}
			}
			public void keyReleased(KeyEvent arg0) {}			
			public void keyPressed(KeyEvent arg0) {}
		});
		
		//Tablo satır seçme
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				rowIndex = table.getSelectedRow();
				for(int i=0;i<9;i++) {
					if(i==0)
						kitapID = table.getModel().getValueAt(rowIndex, i).toString();
					if(i==1)
						txtBarkod.setText(table.getModel().getValueAt(rowIndex, i).toString());
					if(i==2)
						txtKitapAdi.setText(table.getModel().getValueAt(rowIndex, i).toString());
					if(i==4)
						txtKitle.setText(table.getModel().getValueAt(rowIndex, i).toString());
					if(i==6)
						txtSayfaSayisi.setText(table.getModel().getValueAt(rowIndex, i).toString());
					if(i==5)
						txtTur.setText(table.getModel().getValueAt(rowIndex, i).toString());
					if(i==3)
						txtYayinEvi.setText(table.getModel().getValueAt(rowIndex, i).toString());
					if(i==8) {
						String imagePath = table.getModel().getValueAt(rowIndex, i).toString();
						lblImg.setIcon(Icons.getIcon(imagePath, 
								(lblImg.getWidth()-99)/2,(lblImg.getHeight()-99)/2, 99, 99));
						newPath = FileSystems.getDefault().getPath(imagePath);
					}
				}
				
				
			}
		});
		
		//Güncelle Butonu
		
		btnGuncelle.addActionListener((ActionEvent e)->{
			try {
				Kitap kitap=new Kitap(txtBarkod.getText(), txtKitapAdi.getText(), txtYayinEvi.getText(), 
						txtKitle.getText(), txtTur.getText(), Integer.parseInt(txtSayfaSayisi.getText()), newPath.toString());
				Veritabani.veriGuncelle(kitap,Integer.valueOf(kitapID));
				table.setModel(new CustomTableModel(Veritabani.tabloCek("")));
				textFree();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		
		//Gözat Butonu
		
		btnGozat.addActionListener((ActionEvent e)->{
			fileChooser.showOpenDialog(this);
			System.out.println(fileChooser.getSelectedFile());
			System.out.println("İmgPath: "+imgPath);
			System.out.println("NewPath: "+newPath);
			if(fileChooser.getSelectedFile()!=null) {
				imgPath = fileChooser.getSelectedFile().toPath();
				tempPath = newPath; //TODO burda bir dursun belki işe yarar.
				newPath = FileSystems.getDefault().getPath("res/img/"+imgPath.getFileName());
				try {
					Files.copy(imgPath, newPath,StandardCopyOption.REPLACE_EXISTING);
					lblImg.setIcon(Icons.getIcon(newPath.toString(), (235-99)/2, (145-99)/2,99,99));
					System.out.println("Kopyalandı.");
				} catch (IOException ex) {}
				
			}
			
			System.out.println("İmgPath: "+imgPath);
			System.out.println("new Path : "+ newPath);
			System.out.println("Temp: "+tempPath);
		});
		
		btnGeri.addActionListener((ActionEvent e)->{
			setVisible(false);
			mediator.panelGoster("menubar");
		});
	}
	
	
	
	//Variables
	
	private JTable table;
	private JScrollPane scroll;
	private JButton btnSearch;
	private JButton btnGuncelle;
	private JButton btnGozat;
	private JButton btnGeri;
	
	private JTextField txtSearch;
	private JTextField txtBarkod;
	private JTextField txtKitapAdi;
	private JTextField txtYayinEvi;
	private JTextField txtTur;
	private JTextField txtKitle;
	private JTextField txtSayfaSayisi;
	
	private JLabel lblKitapID;
	private JLabel lblKitapAdi;
	private JLabel lblSayfaSayisi;
	private JLabel lblYayinevi;
	private JLabel lblKitle;
	private JLabel lblTur;
	private JLabel lblImg;

	private Font font;
	private Color color;
	private String kitapID;
	private Path imgPath;
	private Path newPath;
	private Path tempPath;
	private JFileChooser fileChooser;

	private int rowIndex;
	private Mediator mediator; //Mediator işlemleri için
}
