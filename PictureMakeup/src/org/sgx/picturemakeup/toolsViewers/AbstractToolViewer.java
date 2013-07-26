package org.sgx.picturemakeup.toolsViewers;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * representaci�n visual de una herramienta
 * 
 * nota: es mi responsabilidad ser transparente para 
 * que se vea correctamente la iim�gen que est� detr�s de mi
 * 
 * @author sgx
 *
 */
public class AbstractToolViewer implements ToolViewer {

	JComponent main;
//	int width, height;
	
	public AbstractToolViewer(BufferedImage buffi) {		
		main = new JPanel();		
		main.setBackground(new Color(0,0,0,144));
		updateWith(buffi);
	}

	
	public void updateWith(BufferedImage buffi) {
//		width=buffi.getWidth();
//		height = buffi.getHeight();
//		main.setSize(new Dimension(width, height));
	}


	public JComponent getComponent() {
		return main;
	}
	
}
