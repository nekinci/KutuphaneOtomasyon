package com.org.kutuphane;

public class Kitap {
	
	 //Constructors
	public Kitap() {

	}
	
	public Kitap(String barkodNumarasi, String kitapAdi, String yayinevi, String kitle, String tur, int sayfaSayisi,
			String imgPath) {
		super();
		this.barkodNumarasi = barkodNumarasi;
		this.kitapAdi = kitapAdi;
		this.yayinevi = yayinevi;
		this.kitle = kitle;
		this.tur = tur;
		this.sayfaSayisi = sayfaSayisi;
		this.setImgPath(imgPath);
	}

	//Getter and Setter Methods
	
	public String getBarkodNumarasi() {
		return barkodNumarasi;
	}
	
	public String getKitapAdi() {
		return kitapAdi;
	}
	
	public String getKitle() {
		return kitle;
	}
	
	public int getSayfaSayisi() {
		return sayfaSayisi;
	}
	
	public String getTur() {
		return tur;
	}
	
	public String getYayinevi() {
		return yayinevi;
	}
	
	public void setBarkodNumarasi(String barkodNumarasi) {
		this.barkodNumarasi = barkodNumarasi;
	}
	
	public void setKitapAdi(String kitapAdi) {
		this.kitapAdi = kitapAdi;
	}
	
	public void setKitle(String kitle) {
		this.kitle = kitle;
	}
	
	public void setSayfaSayisi(int sayfaSayisi) {
		this.sayfaSayisi = sayfaSayisi;
	}
	
	public void setTur(String tur) {
		this.tur = tur;
	}
	
	public void setYayinevi(String yayinevi) {
		this.yayinevi = yayinevi;
	}
	
	
	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}


	//Variables
	private String barkodNumarasi;
	private String kitapAdi;
	private String yayinevi;
	private String kitle;
	private String tur;
	private String imgPath;
	private int sayfaSayisi;
	public static int kitapID=0;
	
}
