package org.sgx.jmencoder.desktrec.test;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;

import javax.swing.*;

public class TransparentWindow extends JComponent { 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame; 
    private Image background;

public TransparentWindow(JFrame frame) {
    this.frame = frame;
    updateBackground( );
}

public void updateBackground( ) {
    try {
        Robot rbt = new Robot( );
        Toolkit tk = Toolkit.getDefaultToolkit( );
        Dimension dim = tk.getScreenSize( );
        background = rbt.createScreenCapture(
        new Rectangle(0,0,(int)dim.getWidth( ),
                          (int)dim.getHeight( )));
    } catch (Exception ex) {       
        ex.printStackTrace( );
    }
}
public void paintComponent(Graphics g) {
    Point pos = this.getLocationOnScreen( );
    Point offset = new Point(-pos.x,-pos.y);
    g.drawImage(background,offset.x,offset.y,null);
}

public static void main(String[] args) {
    JFrame frame = new JFrame("Transparent Window");
    frame.addWindowStateListener(new WindowStateListener() {
		
		@Override
		public void windowStateChanged(WindowEvent e) {
			System.out.println("windowStateChanged");
			// TODO Auto-generated method stub
			
		}
	});
    frame.addWindowListener(new WindowListener() {
		
		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
	});
    TransparentWindow bg = new TransparentWindow(frame);
    bg.setLayout(new BorderLayout( ));
    JButton button = new JButton("This is a button");
    bg.add("North",button);
JLabel label = new JLabel("This is a label");
    bg.add("South",label);
    frame.getContentPane( ).add("Center",bg);
    frame.pack( );
    frame.setSize(150,100);
    frame.show( );
}
}