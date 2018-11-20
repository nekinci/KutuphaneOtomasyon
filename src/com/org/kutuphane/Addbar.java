package com.org.kutuphane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class Addbar extends JPanel{
	
	
	private static final long serialVersionUID = 1L;

	public Addbar(Mediator mediator) {
		initComponents();
		this.mediator = mediator;
		mediator.uyeEkle("addbar", this);
	}
	
	
	private void initComponents() {
		//Panel Özellikleri
		
		setLayout(null);
		setBackground(Color.cyan);
		setBounds(0,70,691,407);
		setBorder(new TitledBorder("Kitap Ekleme"));
		setVisible(false);
		
		//İlklendirme - initilization
		
		txtKitapID=new JTextField();
		txtKitapAdi=new JTextField();
		txtSayfaSayisi=new JTextField();
		txtYayinevi=new JTextField();
		txtKitle=new JTextField();
		txtTur=new JTextField();
		lblKitapID=new JLabel("Barkod:");
		lblKitapAdi=new JLabel("Kitap Adı:");
		lblSayfaSayisi=new JLabel("Sayfa Sayısı:");
		lblYayinevi=new JLabel("Yayınevi:");
		lblKitle=new JLabel("Kitle:");
		lblTur=new JLabel("Tür:");
		pnlDurum=new JPanel();
		lblDurum=new JLabel("Hazır!");
		lblDurumImg=new JLabel(Icons.getIcon("res/play-button.png", 0, 0, 24, 24));
		lblKitapImg=new JLabel(Icons.getIcon("res/warning.png",100 ,62, 100, 100));
		btnGeri=new JButton("<");
		btnKaydet=new JButton("Kaydet");
		btnGozat=new JButton("Gözat");
		chsDosya = new JFileChooser();
		newPath = FileSystems.getDefault().getPath("res/");
		imgPath = FileSystems.getDefault().getPath("res/warning.png");
		
		//Konumlandırma
		
		lblKitapID.setBounds(35, 40, 120, 35);
		lblKitapAdi.setBounds(35, 80, 120, 35);
		lblSayfaSayisi.setBounds(35, 120, 120, 35);
		lblYayinevi.setBounds(35,160,120,35);
		lblKitle.setBounds(35, 200, 120, 35);	
		lblTur.setBounds(35, 240, 120, 35);
		txtKitapID.setBounds(170,44,165,25);
		txtKitapAdi.setBounds(170, 84, 165, 25);
		txtSayfaSayisi.setBounds(170,124,165,25);
		txtYayinevi.setBounds(170,164,165,25);
		txtKitle.setBounds(170,204,165,25);
		txtTur.setBounds(170,244,165,25);
		pnlDurum.setBounds(0,364,689,39);
		lblDurum.setBounds(60,14,200,11);
		lblDurumImg.setBounds(20, 9, 60, 60);
		lblKitapImg.setBounds(350,44,300,224);
		btnGeri.setBounds(35,285,100,50);
		btnKaydet.setBounds(140, 285, 195, 50);
		btnGozat.setBounds(551,285,100,50);
		
		//Özellik tanımı
		
		Font customFont=Fonts.getFont(Fonts.OPENSANS, 18);
		Color customColor=Color.decode("#33e6ee");
		pnlDurum.setLayout(null);
		lblKitapImg.setBorder(new LineBorder(Color.BLACK));
		
		//Font
		
		lblKitapID.setFont(customFont);
		lblKitapAdi.setFont(customFont);
		lblSayfaSayisi.setFont(customFont);
		lblYayinevi.setFont(customFont);
		lblKitle.setFont(customFont);
		lblTur.setFont(customFont);
		txtKitapID.setFont(customFont);
		txtKitapAdi.setFont(customFont.deriveFont(12f).deriveFont(Font.BOLD));
		txtSayfaSayisi.setFont(customFont);
		txtYayinevi.setFont(customFont);
		txtKitle.setFont(customFont);
		txtTur.setFont(customFont);
		btnGozat.setFont(customFont);
		btnKaydet.setFont(customFont);
		btnGeri.setFont(customFont);
		
		//Background Color 
		
		txtKitapID.setBackground(customColor);
		txtKitapAdi.setBackground(customColor);
		txtSayfaSayisi.setBackground(customColor);
		txtYayinevi.setBackground(customColor);
		txtKitle.setBackground(customColor);
		txtTur.setBackground(customColor);
		btnGozat.setBackground(customColor);
		btnGeri.setBackground(customColor);
		btnKaydet.setBackground(customColor);
		pnlDurum.setBackground(Color.decode("#36cceb"));
		
		//Hizalama
		
		txtKitapID.setHorizontalAlignment(SwingUtilities.CENTER);
		txtKitapAdi.setHorizontalAlignment(SwingUtilities.CENTER);
		txtSayfaSayisi.setHorizontalAlignment(SwingUtilities.CENTER);
		txtYayinevi.setHorizontalAlignment(SwingUtilities.CENTER);
		txtKitle.setHorizontalAlignment(SwingUtilities.CENTER);
		txtTur.setHorizontalAlignment(SwingUtilities.CENTER);

		//Ekleme
		
		add(lblKitapID);
		add(lblKitapAdi);
		add(lblSayfaSayisi);
		add(lblYayinevi);
		add(lblKitle);
		add(lblTur);
		add(txtKitapID);
		add(txtKitapAdi);
		add(txtSayfaSayisi);
		add(txtYayinevi);
		add(txtKitle);
		add(txtTur);
		add(pnlDurum);
		add(lblKitapImg);
		add(btnGeri);
		add(btnKaydet);
		add(btnGozat);
		pnlDurum.add(lblDurumImg);
		pnlDurum.add(lblDurum);
		
		//Geri çıkma butonu
		
		btnGeri.addActionListener((ActionEvent e)->{
			setVisible(false);
			mediator.panelGoster("menubar");
		});
		
		//Kaydet Butonu
		/*
		 * Kitap alındı Veritabanına eklendi.
		 * */
		
		btnKaydet.addActionListener((ActionEvent e)->{
			
			//TODO TODO TODO FIXME boş eklenmiyor tamamda sonra dolu eklemeye çalışınca yine eklenmiyor.
			
			try 
			{
				Kitap k=new Kitap(txtKitapID.getText(), txtKitapAdi.getText(), txtYayinevi.getText(),
						txtKitle.getText(), txtTur.getText(),
						Integer.parseInt(txtSayfaSayisi.getText()),
						newPath.resolve(imgPath.getFileName()).toString());
				
				Veritabani.veriEkle(k);
				lblDurumImg.setIcon(Icons.getIcon("res/like.png", 0, 0, 24, 24));
				lblDurum.setText("Başarıyla eklendi");
			} 
			
			catch (Throwable e1) {
				System.out.println("Veri eklenemedi");
				e1.printStackTrace();
				lblDurum.setText("Başarısız");
				lblDurumImg.setIcon(Icons.getIcon("res/error.png", 0, 0, 24, 24));
				
			}
		});
		
		
		//Gözat butonu 
		/*
		 * FileChooserden dosya çekildi. pathe atandı. daha sonra program klasörüne kopyalandı
		 * sonra grafik arayüzünde resim gösterildi.  
		 * */
		
		btnGozat.addActionListener((ActionEvent e)->{ //Resmi alır belirlenen klasöre koyar 
			
			chsDosya.showSaveDialog(this); //daha sonra veritabanına eklemek üzere lblKitapImg ye yerleştirir.
			imgPath = chsDosya.getSelectedFile().toPath();
			newPath = FileSystems.getDefault().getPath("res/img/");
			
			try { // Dosyayı yeni klasörüne kopyaladık. Daha sonra panelde dosyayı gösterdik.
			
				Files.copy(imgPath,newPath.resolve(imgPath.getFileName()),StandardCopyOption.REPLACE_EXISTING);
				
				lblKitapImg.setIcon(Icons.getIcon(newPath.resolve(imgPath.getFileName()).toString(), 
						(lblKitapImg.getWidth()-100)/2, (lblKitapImg.getHeight()-100)/2, 100, 100));
			} 
			
			catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
	}
	
	
	
	
	
	//Variables
	
	private JTextField txtKitapID;
	private JTextField txtKitapAdi;
	private JTextField txtSayfaSayisi;
	private JTextField txtYayinevi;
	private JTextField txtKitle;
	private JTextField txtTur;
	
	private JLabel lblKitapID;
	private JLabel lblKitapAdi;
	private JLabel lblSayfaSayisi;
	private JLabel lblYayinevi;
	private JLabel lblKitle;
	private JLabel lblTur;
	private JLabel lblDurum;
	private JLabel lblDurumImg;
	private JLabel lblKitapImg;
	
	private JPanel pnlDurum;
	private JButton btnGeri;
	private JButton btnKaydet;
	private JButton btnGozat;
	private JFileChooser chsDosya;
	private Path imgPath;
	private Path newPath;
	private Mediator mediator;
}
