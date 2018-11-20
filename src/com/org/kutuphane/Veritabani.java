package com.org.kutuphane;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.swing.JOptionPane;

import com.mysql.jdbc.Driver;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.ResultSetRow;

public class Veritabani {
	//Constructors
	private Veritabani() throws Exception {} //Singleton Design
	
	
	public static void veritabaniAc(String kaynak,String kadi,String sifre) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		conn=DriverManager.getConnection(kaynak, kadi, sifre);
	}
	
	public static void kapat() throws Exception {
		conn.close();
	}
	
	public static void veriEkle(Kitap k){
		try{
			if(conn==null) {
			System.out.println("Veritabanı bağlantısında sorun var!");
		}
		String sqlQuery="INSERT INTO kitap values(?,?,?,?,?,?,?,?,?)";
		java.sql.PreparedStatement statement=conn.prepareStatement(sqlQuery);
		ResultSet countRow=conn.createStatement().executeQuery("Select id from kitap");
	
		statement.setInt(1, 0);
		statement.setString(2, k.getBarkodNumarasi());
		statement.setString(3, k.getKitapAdi());
		statement.setString(4, k.getYayinevi());
		statement.setString(5, k.getKitle());
		statement.setString(6, k.getTur());
		statement.setInt(7, 22);
		statement.setDate(8, Date.valueOf(LocalDate.now()));
		statement.setString(9, k.getImgPath());
		statement.execute();}catch(Exception e) {
		System.out.println("Hata varrrrr");
		}
	}
	
	public static boolean veriCek(String kadi,String sifre) throws Exception {
		veritabaniAc("jdbc:mysql://localhost:3306/kutuphane", "root", "*5432704*");
		if(conn==null) {
			System.out.println("Veritabanı bağlantısında sorun var");
			return false;
		}
		String sqlQuery="Select kadi,sifre from kullanicilar";
		Statement statement=conn.createStatement();
		ResultSet r=statement.executeQuery(sqlQuery);
		while(r.next()) {
			if(kadi.equals(r.getString(1))&&sifre.equals(r.getString(2))) {
				return true;
			}
		}
		return false;
	}
	
	public static ResultSet tabloCek(String veri) throws Exception {
		
		Statement st=conn.createStatement();
		ResultSet r=st.executeQuery("Select *from kitap where kitapAdi  LIKE  '%"+veri+"%' OR tur LIKE '%"+veri+"%' OR"
				+ " yayinevi LIKE '%"+veri+"%' OR kitle LIKE '%"+veri+"%' OR barkod LIKE '%"+veri+"%' ");
		r.beforeFirst();
		return	r;
	}
	
	public static void veriGuncelle(Kitap k,int id) {
		try {
			String sqlQuery = "UPDATE kitap SET barkod = ?, kitapAdi = ?, kitle=?,"
					+ "sayfaSayisi=?,tur=?,yayinevi=?,resim=? where id=?";
			java.sql.PreparedStatement prest=conn.prepareStatement(sqlQuery);
			
			prest.setString(1, k.getBarkodNumarasi());
			prest.setString(2, k.getKitapAdi());
			prest.setString(3, k.getKitle());
			prest.setInt(4,k.getSayfaSayisi());
			prest.setString(5, k.getTur());
			prest.setString(6, k.getYayinevi());
			prest.setString(7,k.getImgPath());
			prest.setInt(8, id);
			
			int i=prest.executeUpdate();
			if(i==1)
				System.out.println("Güncelleme başarılı");
			else
				System.out.println("Yolunda gitmeyen birşeyler var.");
			prest.cancel();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void uyeEkle(Uye u) throws Exception {
		veritabaniAc("jdbc:mysql://localhost:3306/kutuphane", "root", "*5432704*");
		String sqlQuery = "INSERT INTO uyeler values (?,?,?,?,?,?,?,?,?,?)";
		
		java.sql.PreparedStatement prest = conn.prepareStatement(sqlQuery);
		
		prest.setInt(1, 0);
		prest.setString(2, u.getTcno());
		prest.setString(3, u.getAd());
		prest.setString(4, u.getSoyad());
		prest.setString(5, u.getDogtarih());
		prest.setString(6, u.getTelefon());
		prest.setString(7, u.getMail());
		prest.setString(8, u.getCinsiyet());
		prest.setString(9, u.getAdres());
		prest.setString(10, u.getResim());
		
		if(prest.execute())
			System.out.println("Başarılı!");
		conn.close();
	}
	
	public static ResultSet  kitapCek(String veri)  {
		Statement st = null;
		String sqlQuery = null ;
		try {
			sqlQuery= "Select id,barkod,kitapAdi from kitap where kitapAdi LIKE '%"+veri+"%'";
			st = conn.createStatement();
			return st.executeQuery(sqlQuery);
		}catch(Exception e) {}
		return null;
	
	}
	
	public static ResultSet uyeCek(String veri) {
		
		String sqlQuery = "Select id,tcno,ad,soyad from uyeler where ad LIKE '%"+veri+"%'";
		Statement st;
		try {
			st = conn.createStatement();
			return st.executeQuery(sqlQuery);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void kitapKirala(int bid,int pid,String kitap) {
		try {
			veritabaniAc("jdbc:mysql://localhost:3306/kutuphane", "root", "*5432704*");
			String sqlQuery = "insert into kira values(?,?,?,?)";
			java.sql.PreparedStatement prest = conn.prepareStatement(sqlQuery);
			
			prest.setInt(1, 0);
			prest.setInt(2, bid);
			prest.setInt(3, pid);
			prest.setString(4, kitap);
			
			if(prest.execute())
				System.out.println("Başarılı");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void kullaniciGuncelle(String kadi,String sifre,String yeniSifre) {
		
		try {
			if(veriCek(kadi, sifre)) {
				String sqlQuery = "update kullanicilar set sifre = ? where kadi = ?";
				java.sql.PreparedStatement prest = conn.prepareStatement(sqlQuery);
				prest.setString(1, yeniSifre);
				prest.setString(2, kadi);
				prest.executeUpdate();
			}
			else
				JOptionPane.showMessageDialog(null, "Eski şifre veya kullanici adi hatalı");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) throws Throwable{
		veritabaniAc("jdbc:mysql://localhost:3306/kutuphane", "root", "*5432704*");
		//veriEkle(new Kitap("8690005","s","s","s","3",352226));
		MainApp.main(null);
		
	}
	
	//Getter and Setter Methods
	private static Connection getConnection() {
		return conn;
	}
	
	//Variables
	
	private static java.sql.Connection conn;
}
