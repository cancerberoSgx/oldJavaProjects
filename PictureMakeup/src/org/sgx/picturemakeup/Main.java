package org.sgx.picturemakeup;

import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import org.sgx.picturemakeup.gui.ImageEditorFrame;
import org.sgx.picturemakeup.gui.ImageWidgetImpl;

public class Main {

	public static void main( String[] argv ) {
             
		ImageEditorFrame frame = new ImageEditorFrame();
        frame.setVisible(true);
        frame.addWindowListener( new WindowAdapter(){
            public void windowClosing( WindowEvent e ){
                System.exit( 0 );
            }
        });                     
//        frame.pack();              
        frame.setSize( 550, 400 );
        frame.getImg().setSize(200, 200); 
        frame.getjSplitPane().setDividerLocation(300); //.setDividerLocation(0.2); 
//        frame.getRootPane().updateUI(); 
//        frame.update(null); 
   }


}
