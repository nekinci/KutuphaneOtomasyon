package com.org.kutuphane;

public class Uye {
	
	//Variables
	private int id;
	private String tcno;
	private String ad;
	private String soyad;
	private String dogtarih;
	private String telefon;
	private String mail;
	private String cinsiyet;
	private String adres;
	private String resim;
	
	//Constructors
	public Uye(int id, String tcno, String ad, String soyad, String dogtarih, String telefon, String mail,
			String cinsiyet, String adres, String resim) {
		super();
		this.id = id;
		this.tcno = tcno;
		this.ad = ad;
		this.soyad = soyad;
		this.dogtarih = dogtarih;
		this.telefon = telefon;
		this.mail = mail;
		this.cinsiyet = cinsiyet;
		this.adres = adres;
		this.resim = resim;
	}
	
	//Getter and Setter Methods
	
	public String getAd() {
		return ad;
	}
	
	public String getDogtarih() {
		return dogtarih;
	}
	
	public String getAdres() {
		return adres;
	}
	
	public int getId() {
		return id;
	}
	
	public String getCinsiyet() {
		return cinsiyet;
	}
	
	public String getMail() {
		return mail;
	}
	
	public String getResim() {
		return resim;
	}
	
	public String getSoyad() {
		return soyad;
	}
	
	public String getTcno() {
		return tcno;
	}
	
	public String getTelefon() {
		return telefon;
	}
	
	public void setAd(String ad) {
		this.ad = ad;
	}
	
	public void setAdres(String adres) {
		this.adres = adres;
	}
	
	public void setCinsiyet(String cinsiyet) {
		this.cinsiyet = cinsiyet;
	}
	
	public void setDogtarih(String dogtarih) {
		this.dogtarih = dogtarih;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public void setResim(String resim) {
		this.resim = resim;
	}
	
	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}
	
	public void setTcno(String tcno) {
		this.tcno = tcno;
	}
	
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	
}
