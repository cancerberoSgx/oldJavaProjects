package org.sgx.picturemakeup.gui;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JLayeredPane;

import org.sgx.picturemakeup.toolsViewers.AbstractToolViewer;


/**
 *  encargado de mostrar un ImageWidget junto con su ToolViewer
 *  
 * @author sgx
 *
 */
public class ImageEditor extends JLayeredPane {

	private static final long serialVersionUID = 1L;
	
	ImageWidgetImpl img; 
	AbstractToolViewer toolV;
	
	public ImageEditor(ImageWidgetImpl img, AbstractToolViewer toolv) {
		super();
		this.img = img;		
		toolV = toolv;
		this.setLayout(null);
		this.setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
	}
	
	public void updateWith(BufferedImage buffi) {
		toolV.getComponent().setSize(new Dimension(buffi.getWidth(), buffi.getHeight()));
		
	}

	public ImageWidgetImpl getImg() {
		return img;
	}

	public void setImg(ImageWidgetImpl img) {
		this.img = img;
	}

	public AbstractToolViewer getToolV() {
		return toolV;
	}

	public void setToolV(AbstractToolViewer toolV) {
		this.toolV = toolV;
	}
}
