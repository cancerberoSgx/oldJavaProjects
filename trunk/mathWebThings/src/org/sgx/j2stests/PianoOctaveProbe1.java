package org.sgx.j2stests;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Color;

public class PianoOctaveProbe1 extends Composite {

	private Composite keyDo = null;
	private Composite keyDoSharp = null;
	private Composite keyRe = null;

	/**
	 * This method initializes keyDo	
	 *
	 */
	private void createKeyDo() {
		keyDo = new Composite(this, SWT.BORDER);
		keyDo.setLayout(new GridLayout());
		keyDo.setBackground(new Color(Display.getCurrent(), 255, 255, 255));
		keyDo.setBounds(new Rectangle(5, 0, 91, 176));
	}

	/**
	 * This method initializes keyDoSharp	
	 *
	 */
	private void createKeyDoSharp() {
		keyDoSharp = new Composite(this, SWT.NONE);
		keyDoSharp.setLayout(new GridLayout());
		keyDoSharp.setBackground(new Color(Display.getCurrent(), 0, 0, 0));
		keyDoSharp.setBounds(new Rectangle(60, 0, 58, 96));
	}

	/**
	 * This method initializes keyRe	
	 *
	 */
	private void createKeyRe() {
		keyRe = new Composite(this, SWT.BORDER);
		keyRe.setLayout(new GridLayout());
		keyRe.setBackground(new Color(Display.getCurrent(), 255, 255, 255));
		keyRe.setBounds(new Rectangle(95, 0, 91, 176));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/* Before this is run, be sure to set up the launch configuration (Arguments->VM Arguments)
		 * for the correct SWT library path in order to run with the SWT dlls. 
		 * The dlls are located in the SWT plugin jar.  
		 * For example, on Windows the Eclipse SWT 3.1 plugin jar is:
		 *       installation_directory\plugins\org.eclipse.swt.win32_3.1.0.jar
		 */
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		shell.setSize(new Point(300, 200));
		new PianoOctaveProbe1(shell, SWT.NONE);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	public PianoOctaveProbe1(Composite parent, int style) {
		super(parent, style);
		initialize();
	}

	private void initialize() {

		createKeyDoSharp();
		createKeyDo();
		createKeyRe();

		setSize(new Point(700, 180));
		setLayout(null);
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
