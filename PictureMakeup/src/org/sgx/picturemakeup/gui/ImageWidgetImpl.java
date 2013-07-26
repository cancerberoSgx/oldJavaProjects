package org.sgx.picturemakeup.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.sgx.picturemakeup.model.ImageTransformation;
import org.sgx.utils.ImageUtils;



/**
 * Widget que simula a una imagen a la cual se le pueden aplicar ImageTransformations.
 * 
 * TODO:  
 * filtros de colores:
 * (1)el clásico: filtrar solo componentes r o g o b
 * (2)el usuario elige un color a y un color b. b puede tener 
 * componente alfa. También determina un radio r. El filtro reemplaza todos los pixeles cuyos colores estén dentro de a+radio por B.
 * (3) Futuro agregar al ImagePanel un "area de selección" y realizar 
 * herramientas de selección (por color, rectángular, elíptica, poligonal 
 * y curba blezer.  
 */
public class ImageWidgetImpl extends JPanel {
	
	private static final long serialVersionUID = 1L;

//	static String defaultImgPath = "C:\\seba\\softpoint\\PictureMakeup\\gui\\images\\map.jpg";
	public static String defaultImgPath = "/home/sebastian/desarrollo/sgurin_workspace/PictureMakeup/src/org/sgx/pickturemakeup/resources/images/sample.jpg";//"src"+File.separator+"org"+File.separator+"sgx"+File.separator+"pickturemakeup"+File.separator+"resources"+File.separator+"images"+File.separator+"sample.jpg";
	
//	org.sgx.pickturemakeup.
	
	BufferedImage buffi;
	BufferedImage original;
	String imgPath=defaultImgPath;


	public ImageWidgetImpl(String imagePath) {	
		setBackground(Color.white);
		try {
			buffi = ImageIO.read(new File(imagePath));
			this.setSize(buffi.getWidth(), buffi.getHeight());
			setMinimumSize(new Dimension(buffi.getWidth(), buffi.getHeight()));
			original=ImageUtils.cloneImage(buffi);
			imgPath=imagePath;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	public ImageWidgetImpl(BufferedImage buffi) {
		setBackground(Color.white);
		this.buffi = buffi;
		this.setSize(buffi.getWidth(), buffi.getHeight());
		setMinimumSize(new Dimension(buffi.getWidth(), buffi.getHeight()));
		original=ImageUtils.cloneImage(buffi);
		imgPath="";
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(buffi, null, 0, 0);
	}
	
	/**
	 * aplica la transformación a la imágen de forma que es mostrada 
	 * en la gui. Sin embargo, la imágen en si no se modifica ya 
	 * que si esta función es llamada nuevamente la transformación 
	 * se realiza sobre la imágen original.
	 * @param it
	 */
	public void showTransformation(ImageTransformation it) {
		BufferedImage img = it.applyTransformation(original);
		buffi=img;
		setMinimumSize(new Dimension(buffi.getWidth(), buffi.getHeight()));
		paintComponent(this.getGraphics());
	}	
	
	public void applyCurrentTransformation() {
		original=ImageUtils.cloneImage(buffi);
	}
	
	public void restore() {
		buffi=original;
		this.paintComponent(this.getGraphics());
	}
	
	public static void main(String [] s) {
		JFrame f = new JFrame("sls");
		f.setContentPane(new ImageWidgetImpl(ImageWidgetImpl.defaultImgPath));
		f.setSize(f.getContentPane().getWidth(), f.getContentPane().getHeight());
		f.setVisible(true);
	}

	public String getImgPath() {
		return imgPath;
	}

	public BufferedImage getBufferredImage() {
		return buffi;
	}
	
	public void setImage(BufferedImage buffi, String imgPath) {
		this.buffi=buffi;
		this.imgPath=imgPath;
		this.original=buffi;
		this.updateUI();
		this.addNotify();
		this.getParent().addNotify();
	}
}
