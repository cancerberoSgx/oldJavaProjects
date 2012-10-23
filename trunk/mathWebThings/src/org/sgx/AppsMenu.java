package org.sgx;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.sgx.fplotter.Plotter;
import org.sgx.j2s.fractals.Julia;
import org.sgx.j2s.fractals.JuliaShell;
import org.sgx.j2s.soundManager.smallPianoApp.gui.PianoKeyboard;
import org.sgx.stringUtilities.StringUtilMainComp;
import org.sgx.webMatrix.gui.MatrixOperationShell;

import com.sun.org.apache.xpath.internal.patterns.FunctionPattern;

//import com.cloudgarden.resource.SWTResourceManager;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class AppsMenu extends Composite {
 
	private Menu menu1;
	private Button button1;
	private MenuItem aboutMenuItem;
	private MenuItem contentsMenuItem;
	private Menu helpMenu;
	private MenuItem helpMenuItem;
	private Button button5;
	private Button button4;
	private Button button3;
	private Button button2;

	{
		//Register as a resource user - SWTResourceManager will
		//handle the obtaining and disposing of resources
//		SWTResourceManager.registerResourceUser(this);
	}

	public AppsMenu(Composite parent, int style) {
		super(parent, style);
		initGUI();
	}
	
	/**
	* Initializes the GUI.
	*/
	private void initGUI() {
		try {
			this.setSize(new org.eclipse.swt.graphics.Point(400,300));
//			this.setBackground(SWTResourceManager.getColor(192, 192, 192));
			GridLayout thisLayout = new GridLayout();
			thisLayout.makeColumnsEqualWidth = true;
			this.setLayout(thisLayout);
			{
				button2 = new Button(this, SWT.PUSH | SWT.CENTER);
				button2.setText("visual matrix calculator");
				button2.addSelectionListener(new SelectionListener() {
					public void widgetDefaultSelected(SelectionEvent arg0) {						
					}
					public void widgetSelected(SelectionEvent arg0) {
						MatrixOperationShell.main(null);
						AppsMenu.this.getShell().setMinimized(true);
					}					
				});
			}
			{
				button1 = new Button(this, SWT.PUSH | SWT.CENTER);
				button1.setText("julia fractal simulation");
				button1.addSelectionListener(new SelectionListener() {
					public void widgetDefaultSelected(SelectionEvent arg0) {						
					}
					public void widgetSelected(SelectionEvent arg0) {
						JuliaShell.main(null);
						AppsMenu.this.getShell().setMinimized(true);
					}					
				});
			}
			{
				button3 = new Button(this, SWT.PUSH | SWT.CENTER);
				button3.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(final SelectionEvent e) {
						Plotter.main(null);
						AppsMenu.this.getShell().setMinimized(true);
					}
				});
				button3.setText("function plotter");
			}
			{
				button4 = new Button(this, SWT.PUSH | SWT.CENTER);
				button4.setText("string utilities");
				button4.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent arg0) {
						StringUtilMainComp.main(null);
						AppsMenu.this.getShell().setMinimized(true);
					}					
				});
			}
			{
				button5 = new Button(this, SWT.PUSH | SWT.CENTER);
				button5.setText("music piano");
				button5.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent arg0) {
						PianoKeyboard.main(null);
						AppsMenu.this.getShell().setMinimized(true);
					}					
				});
			}
			{
				menu1 = new Menu(getShell(), SWT.BAR);
				getShell().setMenuBar(menu1);
				{
					helpMenuItem = new MenuItem(menu1, SWT.CASCADE);
					helpMenuItem.setText("Help");
					{
						helpMenu = new Menu(helpMenuItem);
						{
							contentsMenuItem = new MenuItem(helpMenu, SWT.CASCADE);
							contentsMenuItem.setText("Contents");
						}
						{
							aboutMenuItem = new MenuItem(helpMenu, SWT.NONE);
							aboutMenuItem.addSelectionListener(new SelectionListener() {
								public void widgetDefaultSelected(
										SelectionEvent arg0) {
								
								}
								public void widgetSelected(SelectionEvent arg0) {
									MessageBox b = new MessageBox(AppsMenu.this.getShell(), SWT.OK);
									b.setMessage("two little applications demonstrating that you can develop" +
											"\nyour application for web and desktop at once.\nAuthor: Sebastián Gurin, cancerbero_sgx at sourceforge net");
									
									b.open();
								}
								
							});
							aboutMenuItem.setText("About");
						}
						helpMenuItem.setMenu(helpMenu);
					}
				}
			}
			this.layout();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	* Auto-generated main method to display this 
	* org.eclipse.swt.widgets.Composite inside a new Shell.
	*/
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setText("my application's menu...");
		AppsMenu inst = new AppsMenu(shell, SWT.NULL);
		Point size = inst.getSize();
		shell.setLayout(new FillLayout());
		shell.layout();
		if(size.x == 0 && size.y == 0) {
			inst.pack();
			shell.pack();
		} else {
			Rectangle shellBounds = shell.computeTrim(0, 0, size.x, size.y);
			shell.setSize(shellBounds.width, shellBounds.height);
		}
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

}
