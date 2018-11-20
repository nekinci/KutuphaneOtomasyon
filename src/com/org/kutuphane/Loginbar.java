package com.org.kutuphane;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

public class Loginbar extends JPanel {

	private static final long serialVersionUID = 1L;

	//Constructors
	public Loginbar(Mediator mediator) {
		initComponents();
		this.mediator = mediator;
		mediator.uyeEkle("loginbar", this);
	}
	
	//Methods
	
	private void beniHatirla(String kadi,String sifre,boolean check) throws IOException{
		File f=new File("system/rmb.psw");
		f.delete(); // dosyayı her zaman silsinki yalnızca bir kullanıcı adı kayıtlı kalsın
		
		if(!Files.isDirectory(FileSystems.getDefault().getPath("system")));
			new File("system").mkdir(); // klasör yoksa oluştur
		
		if(!Files.isDirectory(FileSystems.getDefault().getPath("system/rmb.psw")));
			f.createNewFile(); //Dosya yoksa oluştur
		
		FileOutputStream fos = new FileOutputStream(f); 
		ObjectOutputStream o=new ObjectOutputStream(fos);
		
		String toplam = check+"\n"+kadi+"\n"+sifre;
		o.writeObject(toplam);
		
		o.close();
		fos.close();
		
	}
	
	private void beniHatirla() {
		try {
			File f= new File("system/rmb.psw");
			if(!f.exists())
				beniHatirla("","",false);
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream o1 = new ObjectInputStream(fis);
			String toplam = (String) o1.readObject();
			String kadi="",sifre="",check="";
			int kontrol = 0;
			for(char c:toplam.toCharArray()) {
				if(c=='\n') {
					kontrol++;
					continue;
				}
				if(kontrol == 0)
					check+=c;
				if(kontrol == 1)
					kadi+=c;
				if(kontrol == 2)
					sifre+=c;
			}
			txtUserName.setText(kadi);
			txtPassword.setText(sifre);
			this.check = check.equals("true") ? true: false; 
			
			
			o1.close();
			fis.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	private void logWrite() {
		try {
			File f = new File("login.log");
			char oku [] = new char [(int) f.length()];
			if(!f.exists())
				f.createNewFile();
			FileReader fr = new FileReader(f);
			fr.read(oku);
			FileWriter fw = new FileWriter(f);
			fw.write(new String(oku)+"\n[LOGIN TIME:] ["+LocalDateTime.now().toString()+"] [Successfully Login]");
			fw.close();
			fr.close();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	private void initComponents() {
		setLayout(null);
		setBounds(0,70,691,407); 
		setBorder(new TitledBorder("Giriş Paneli"));
		setBackground(Color.CYAN);
		
		lblUserName=new JLabel("Kullanıcı Adı");
		lblPassword=new JLabel("Şifre");
		txtUserName=new JTextField();
		txtPassword=new JPasswordField();
		btnLogin=new JButton("Giriş");
		chckRememberMe = new JCheckBox("Beni hatırla");
		
		lblUserName.setBounds(208,127,100,35);
		lblPassword.setBounds(208, 167, 100, 35);
		txtUserName.setBounds(313,133,150,25);
		txtPassword.setBounds(313, 173, 150, 25);
		btnLogin.setBounds(363, 220, 100, 25);
		chckRememberMe.setBounds(313,202,150,15);

		txtUserName.setFont(Fonts.getFont(Fonts.OPENSANS, 20));
		txtUserName.setHorizontalAlignment(SwingUtilities.CENTER);
		txtPassword.setFont(Fonts.getFont(Fonts.OPENSANS, 20));
		txtPassword.setHorizontalAlignment(SwingUtilities.CENTER);
		
		txtUserName.setBackground(Color.decode("#33e6ee"));
		txtPassword.setBackground(Color.decode("#33e6ee"));
		btnLogin.setBackground(Color.CYAN.darker());
		chckRememberMe.setBackground(Color.cyan);
		
		btnLogin.setIcon(Icons.getIcon("res/play-button.png",15,5, 16, 16));
		
		add(lblUserName);
		add(lblPassword);
		add(txtUserName);
		add(txtPassword);
		add(btnLogin);
		add(chckRememberMe);
		beniHatirla();
		chckRememberMe.setSelected(check);
		
		
		btnLogin.addActionListener((ActionEvent e)->{
			try {
				if(Veritabani.veriCek(txtUserName.getText(), txtPassword.getText())) {
					if(chckRememberMe.isSelected()) 
						beniHatirla(txtUserName.getText(),txtPassword.getText(),true);
					if(!chckRememberMe.isSelected())
						beniHatirla("","",false);
					setVisible(false);
					mediator.panelGoster("menubar");
					logWrite();
				}
				else 
					txtUserName.setText("Hatalı kullanıcı adı veya şifre");
			} catch (Exception ex) {ex.printStackTrace();}
		});
		
	}
	
	//Variables
	private JLabel lblUserName;
	private JLabel lblPassword;
	private JTextField txtUserName;
	private JPasswordField txtPassword;
	private JButton btnLogin;
	private JCheckBox chckRememberMe;
	private Mediator mediator;
	private boolean check=false;
}
