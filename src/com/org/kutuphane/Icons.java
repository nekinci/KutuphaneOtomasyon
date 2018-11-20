package com.org.kutuphane;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;

public class Icons implements Icon {
	private int height,width,x,y;
	private BufferedImage img;
	private Icons(String path,int x,int y,int width,int height) {
		try {
		img=ImageIO.read(new File(path));}catch(Exception e) {}
		this.x=x;this.y=y;this.height=height;this.width=width;
	}
	
	public static Icons getIcon(String path,int x,int y,int width,int height) {
		return new Icons(path, x, y, width, height);
	}
	//Override Methods
	
	@Override
	public int getIconHeight() {
		return height;
	}

	@Override
	public int getIconWidth() {
		return width;
	}

	@Override
	public void paintIcon(Component arg0, Graphics arg1, int arg2, int arg3) {
		Graphics2D g=(Graphics2D) arg1;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(img, x, y,width,height, arg0);
	}
}
