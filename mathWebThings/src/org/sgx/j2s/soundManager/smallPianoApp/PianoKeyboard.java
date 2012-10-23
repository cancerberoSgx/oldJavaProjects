//package org.sgx.j2s.soundManager.smallPianoApp;
//
//import java.util.Map;
//
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.events.KeyEvent;
//import org.eclipse.swt.events.KeyListener;
//import org.eclipse.swt.layout.FillLayout;
//import org.eclipse.swt.widgets.Display;
//import org.eclipse.swt.widgets.Shell;
//import org.eclipse.swt.widgets.Text;
//import org.sgx.j2s.soundManager.Sound;
//
//public class PianoKeyboard extends Shell {
//	 
//Map<String,Sound> sounds;
//
//	/**
//	 * Launch the application
//	 * @param args
//	 */
//	public static void main(String args[]) {
//		try {
//			Display display = Display.getDefault();
//			PianoKeyboard shell = new PianoKeyboard(display, SWT.SHELL_TRIM);
//			shell.setLayout(new FillLayout());
//			Text text = new Text(shell, SWT.MULTI);
//			text.setText("focus this shell and use your keyboard as a piano");
//			shell.addKeyListener(new KeyListener() {
//				@Override
//				public void keyPressed(KeyEvent arg0) {
//				}
//				@Override
//				public void keyReleased(KeyEvent arg0) {
//				}				
//			});
//			shell.open();
//			shell.layout();
//			while (!shell.isDisposed()) {
//				if (!display.readAndDispatch())
//					display.sleep();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * Create the shell
//	 * @param display
//	 * @param style
//	 */
//	public PianoKeyboard(Display display, int style) {
//		super(display, style);
//		createContents();
//	}
//
//	/**
//	 * Create contents of the window
//	 */
//	protected void createContents() {
//		setText("SWT Application");
//		setSize(500, 375);
//		//
//	}
//
//	@Override
//	protected void checkSubclass() {
//		// Disable the check that prevents subclassing of SWT components
//	}
//
//}
