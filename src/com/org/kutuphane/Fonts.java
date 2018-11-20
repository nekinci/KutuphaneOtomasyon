package com.org.kutuphane;

import java.awt.Font;
import java.io.File;

public class Fonts {

	/*
	 * Bu sınıf font kütüphanesidir.
	 * */
	
	private Fonts() {
	}
	
	public static Font getFont(String fontName,float fontSize) {
		font=null;
		
		try {
			font=Font.createFont(Font.TRUETYPE_FONT, new File(fontName));
			font=font.deriveFont(fontSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return font;
		
	}
	 //Fields
	private static Font font;
	
	//Const Values
	
	public final static String OPENSANS 	= "res/opensans.ttf";
	public final static String YEAREND  	= "res/yearend.ttf";
	public final static String GRIMACE  	= "res/grimace.ttf";
	public final static String YUKARI   	= "res/yukari.ttf";
	public final static String FRAKTURIKA	= "res/frakturika.ttf";
	public final static String SIDEBURNBOB  = "res/sideburnbob.ttf" ;
	public final static String PROMISED 	= "res/promised.ttf";
	public final static String PENOFTRUTH	= "res/penoftruth.ttf";
	public final static String FLATICON 	= "res/flaticon.ttf"; 
	public final static String MODERNA		= "res/moderna.ttf";
	public final static String DISKO		= "res/disko.ttf";
	public final static String CAPTUREIT	= "res/captureit.ttf";
	public final static String COMFORTAA	= "res/comfortaa.ttf";
	public final static String DESYREL		= "res/desyrel.ttf";
	public final static String DINSTIK		= "res/dinstik.ttf";
}
