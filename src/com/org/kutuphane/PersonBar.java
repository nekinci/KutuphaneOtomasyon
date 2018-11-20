package com.org.kutuphane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

public class PersonBar extends JPanel {
	
	//Constructors
	
	private static final long serialVersionUID = 9106674928373466753L;

	public PersonBar(Mediator mediator) {
		this.mediator = mediator;
		mediator.uyeEkle("personbar", this);
		initComponents();
	}
	
	//Method
	private void initComponents() {

		setBounds(0,70,691,407);
		setBorder(new TitledBorder("Üye Paneli"));
		setBackground(Color.CYAN);
		setVisible(true);
		setLayout(null);
		
		lblTCNO = new JLabel("T.C Kimlik:");
		lblAd = new JLabel("Ad:");
		lblSoyad = new JLabel("Soyad:");
		lblDogumTarihi = new JLabel("Doğum Tar:");
		lblTelefon = new JLabel("Telefon:");
		lblMail = new JLabel("Mail:");
		lblCinsiyet = new JLabel("Cinsiyet:");
		lblAdres = new JLabel("Adres:");
		txtTCNO = new JTextField();
		txtAd = new JTextField();
		txtSoyad = new JTextField();
		txtDogumTarihi = new JFormattedTextField(createFormatter("##.##.####"));
		txtTelefon = new JFormattedTextField(createFormatter("0(###) ### ####"));
		txtMail = new JTextField();
		txtCinsiyet = new JComboBox<>();
		txtAdres = new JTextField();
		lblPersonImg = new JLabel("");
		btnKaydet = new JButton("Kaydet");
		btnGozat = new JButton("Gözat");
		chooser = new JFileChooser();
		kimlikKarti = new JPanel();
		kimlikFrame = new JFrame("Üye kartı");
		btnGeri = new JButton("<");
		
		lblTCNO.setBounds(90,60,120,25);
		lblAd.setBounds(90,90,120,25);
		lblSoyad.setBounds(90,120,120,25);
		lblDogumTarihi.setBounds(90,150,120,25);
		lblTelefon.setBounds(90,180,120,25);
		lblMail.setBounds(90,210,120,25);
		lblCinsiyet.setBounds(90,235,120,35);
		lblAdres.setBounds(90,270,120,25);
		txtTCNO.setBounds(205, 60, 150, 25);
		txtAd.setBounds(205,90,150,25);
		txtSoyad.setBounds(205,120,150,25);
		txtDogumTarihi.setBounds(205,150,150,25);
		txtTelefon.setBounds(205,180,150,25);
		txtMail.setBounds(205,210,150,25);
		txtCinsiyet.setBounds(205,240,150,25);
		txtAdres.setBounds(205,270,150,25);
		lblPersonImg.setBounds(360,60,165,165);
		btnGozat.setBounds(425,230,100,30);
		btnKaydet.setBounds(255,300,100,30);
		kimlikKarti.setBounds(530,60,215,100);
		btnGeri.setBounds(205,300,45,30);
		
		
		Color color = Color.decode("#33e6ee");
		Font font = Fonts.getFont(Fonts.OPENSANS, 18);
		
		
		lblPersonImg.setBorder(new LineBorder(Color.black));
		txtCinsiyet.addItem("ERKEK");txtCinsiyet.addItem("KADIN");
		txtDogumTarihi.setValue("00.00.0000");
		txtDogumTarihi.setToolTipText("gg.aa.yyyy");
		kimlikFrame.setContentPane(kimlikKarti);
		kimlikFrame.setSize(500, 500);
		kimlikFrame.pack();
		
		txtTCNO.setBackground(color);
		txtAd.setBackground(color);
		txtSoyad.setBackground(color);
		txtDogumTarihi.setBackground(color);
		txtTelefon.setBackground(color);
		txtMail.setBackground(color);
		txtCinsiyet.setBackground(color);
		txtAdres.setBackground(color);
		btnGozat.setBackground(color);
		btnKaydet.setBackground(color);
		kimlikKarti.setBackground(color);
		btnGeri.setBackground(color);
		
		lblTCNO.setFont(font);
		lblAd.setFont(font);
		lblSoyad.setFont(font);
		lblDogumTarihi.setFont(font);
		lblTelefon.setFont(font);
		lblMail.setFont(font);
		lblCinsiyet.setFont(font);
		lblAdres.setFont(font);
		txtTCNO.setFont(font);
		txtAd.setFont(font);
		txtSoyad.setFont(font);
		txtDogumTarihi.setFont(font);
		txtTelefon.setFont(font);
		txtMail.setFont(font);
		txtCinsiyet.setFont(font);
		txtAdres.setFont(font);
		btnGozat.setFont(font);
		btnKaydet.setFont(font);
		btnGeri.setFont(font);
		
		add(lblTCNO);
		add(lblAd);
		add(lblSoyad);
		add(lblDogumTarihi);
		add(lblTelefon);
		add(lblMail);
		add(lblCinsiyet);
		add(lblAdres);
		add(txtTCNO);
		add(txtAd);
		add(txtSoyad);
		add(txtDogumTarihi);
		add(txtTelefon);
		add(txtMail);
		add(txtCinsiyet);
		add(txtAdres);
		add(lblPersonImg);
		add(btnGozat);
		add(btnKaydet);
		add(btnGeri);

		btnGozat.addActionListener((ActionEvent e)->{
			try {
				chooser.showOpenDialog(this);
				imgPath = chooser.getSelectedFile().toPath();
				newPath = FileSystems.getDefault().getPath("res/img/"+imgPath.getFileName());
				Files.copy(imgPath, newPath, StandardCopyOption.REPLACE_EXISTING);
				lblPersonImg.setIcon(Icons.getIcon(newPath.toString(), (lblPersonImg.getWidth()-99)/2,
						(lblPersonImg.getHeight()-99)/2, 99,99));
				System.out.println(newPath);
				initKimlikComponents();
				kimlikFrame.show();
				kimlikFrame.setSize(350, 150);
				kimlikFrame.setAlwaysOnTop(true);
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		});
		
		btnKaydet.addActionListener((ActionEvent e)->{
			Uye u = new Uye(0, txtTCNO.getText(), txtAd.getText(), txtSoyad.getText(), txtDogumTarihi.getText(),
					txtTelefon.getText(), txtMail.getText(), txtCinsiyet.getSelectedItem().toString(), txtAdres.getText(),
					newPath.toString());
			try {
				Veritabani.uyeEkle(u);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		
		btnGeri.addActionListener((ActionEvent e)->{
			setVisible(false);
			mediator.panelGoster("menubar");
		});
		
		
		
	}
	
	
	private MaskFormatter createFormatter(String string) {
		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter(string);
		}catch(ParseException ex) {
			ex.printStackTrace();
		}
		return formatter;
	}
	
	private void initKimlikComponents() {
		kimlikKarti.setLayout(null);
		lblKPersonImg = new JLabel();
		lblKTCNO = new JLabel();
		lblKAd = new JLabel();
		lblKSoyad = new JLabel();
		lblKCinsiyet = new JLabel();
		lblKDogTarih = new JLabel();
		
		lblKPersonImg.setBounds(10, 40, 100, 100);
		lblKPersonImg.setBorder(new LineBorder(Color.black));
		lblKPersonImg.setIcon(Icons.getIcon(newPath.toString(), 0, 0, 100, 100));
		kimlikKarti.add(lblKPersonImg);
		
		
		lblKTCNO.setText(txtTCNO.getText());
		lblKTCNO.setBounds(125, 55, 100, 15);
		kimlikKarti.add(lblKTCNO);
		
		JLabel lblTitle= new JLabel("T.C. Kütüphaneler Odası");
		lblTitle.setBounds(0, 0, 350, 20);
		lblTitle.setHorizontalAlignment(SwingUtilities.CENTER);
		kimlikKarti.add(lblTitle);
		
		JLabel lblTTitle = new JLabel("Kimlik Kartı");
		lblTTitle.setBounds(0, 20, 350, 15);
		lblTTitle.setHorizontalAlignment(SwingUtilities.CENTER);
		kimlikKarti.add(lblTTitle);
		
		JLabel lblTTCNO=new JLabel("T.C. No");
		lblTTCNO.setBounds(120,40,100,15);
		lblTTCNO.setForeground(Color.red.darker());
		kimlikKarti.add(lblTTCNO);
		
		JLabel lblTAd = new JLabel("İsim");
		lblTAd.setBounds(120, 70, 100, 15);
		lblTAd.setForeground(Color.RED.darker());
		kimlikKarti.add(lblTAd);
		
		JLabel lblTSoyad = new JLabel("Doğum Tarihi");
		lblTSoyad.setBounds(120, 100, 100, 15);
		lblTSoyad.setForeground(Color.red.darker());
		kimlikKarti.add(lblTSoyad);
		
		JLabel lblTCinsiyet = new JLabel("Cinsiyet");
		lblTCinsiyet.setForeground(Color.RED.darker());
		lblTCinsiyet.setBounds(120, 130, 100, 15);
		kimlikKarti.add(lblTCinsiyet);
		
		lblKAd = new JLabel(txtAd.getText()+" "+txtSoyad.getText());
		lblKAd.setBounds(125, 85, 100, 15);
		kimlikKarti.add(lblKAd);
		
		lblKDogTarih = new JLabel(txtDogumTarihi.getText());
		lblKDogTarih.setBounds(125, 115, 100, 15);
		kimlikKarti.add(lblKDogTarih);
		
		lblKSoyad = new JLabel(txtCinsiyet.getSelectedItem().toString());
		lblKSoyad.setBounds(125, 145, 100, 15);
		kimlikKarti.add(lblKSoyad);
	}
	
	

	//Variables
	private JLabel lblTCNO;
	private JLabel lblAd;
	private JLabel lblSoyad;
	private JLabel lblDogumTarihi;
	private JLabel lblTelefon;
	private JLabel lblMail;
	private JLabel lblCinsiyet; 
	private JLabel lblAdres;
	private JLabel lblPersonImg;
	
	private JTextField txtTCNO;
	private JTextField txtAd;
	private JTextField txtSoyad;
	private JTextField txtMail;
	private JTextField txtAdres;
	private JFormattedTextField txtDogumTarihi;
	private JFormattedTextField txtTelefon;
	private JComboBox<String> txtCinsiyet;
	private JFileChooser chooser;
	
	private JButton btnGozat;
	private JButton btnKaydet;
	private JButton btnGeri;
	
	private Path imgPath;
	private Path newPath;
	private Mediator mediator;
	
	private JPanel kimlikKarti;
	private JFrame kimlikFrame;
	
	
	private JLabel lblKPersonImg;
	private JLabel lblKTCNO;
	private JLabel lblKAd;
	private JLabel lblKSoyad;
	private JLabel lblKDogTarih;
	private JLabel lblKCinsiyet;
}
