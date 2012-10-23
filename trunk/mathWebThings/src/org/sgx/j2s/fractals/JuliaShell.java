package org.sgx.j2s.fractals;
import com.cloudgarden.resource.SWTResourceManager;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.sgx.j2s.bitmap.Bitmap;
import org.sgx.j2s.bitmap.BitmapFactory;
import org.sgx.j2s.utils.JsUtils;
import org.sgx.j2s.utils.Utils;
import org.sgx.j2s.widget.base.Color;
import org.sgx.swt.SWTUtils;


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
public class JuliaShell {

	private Shell sShell = null;
	private Composite composite = null;
	private Menu menuBar = null;
	private Menu submenu = null;
	private Label label = null;
	private Text aEntry = null;
	private Label label1 = null;
	private Text bEntry = null;
	private Label csss = null;
	private Text cEntry = null;
	private Label d = null;
	private Label label3;
	private Label label2;
	private Composite composite1;
	private Text dEntry = null;
	private Button bt1 = null;
	protected Bitmap bitmap=null;
	protected Shell canvasShell;
	protected BitmapWidget bitmapWidget;
	
	
	private void createComposite() {
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 4;
		composite = new Composite(sShell, SWT.NONE);
		composite.setLayout(gridLayout);
		{
			composite1 = new Composite(sShell, SWT.NONE);
			GridLayout composite1Layout = new GridLayout();
			composite1Layout.makeColumnsEqualWidth = true;
			composite1.setLayout(composite1Layout);
			{
				label3 = new Label(composite1, SWT.NONE);
				label3.setText("Try to make little modifications to the parameters\nand see how they reflect in the fractal figure");
			}
			{
				label2 = new Label(composite1, SWT.NONE);
				label2.setText("Note: in Internet Explorer you may have to wait\na little longer because \"drawing\" is slower");
			}
		}
		label = new Label(composite, SWT.NONE);
		label.setText("a: ");
		aEntry = new Text(composite, SWT.BORDER);
		label1 = new Label(composite, SWT.NONE);
		label1.setText("b:");
		bEntry = new Text(composite, SWT.BORDER);
		csss = new Label(composite, SWT.NONE);
		csss.setText("c:");
		cEntry = new Text(composite, SWT.BORDER);
		d = new Label(composite, SWT.NONE);
		d.setText("d:");
		dEntry = new Text(composite, SWT.BORDER);
		Label filler = new Label(composite, SWT.NONE);
		bt1 = new Button(composite, SWT.NONE);
		bt1.setText("Make it!");
		bt1.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			private Shell fractalShell;

			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				//first erase previous canvas

				double[] params = getParameters();
				Julia j = new Julia(params[0], params[1], params[2], params[3]);
				
				if(bitmap==null) {
					bitmap = BitmapFactory.getDefault(500,1,Julia.BITMAP_SIZE,Julia.BITMAP_SIZE);
					
					canvasShell = new Shell	(Display.getCurrent());
					canvasShell.setLayout(new FillLayout());
					bitmapWidget = new BitmapWidget(canvasShell, SWT.NONE, bitmap);
					
				}
					bitmap.erase(Color.WHITE);
					canvasShell.open();
					Julia.drawJulia(j, bitmap);
			}
		});
		
		//load default values:	a=-2.24, b=-0.65, c=0.43, d=-2.43;
		setParameters(new double[]{-2.24, -0.65, 0.43,-2.43 });
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
		JuliaShell thisClass = new JuliaShell();
		thisClass.createSShell();
		thisClass.sShell.open();

		while (!thisClass.sShell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	/**
	 * This method initializes sShell
	 */
	private void createSShell() {
		sShell = new Shell();

		{
			//Register as a resource user - SWTResourceManager will
			//handle the obtaining and disposing of resources
			SWTResourceManager.registerResourceUser(sShell);
		}
		
		sShell.setText("Julia fractal generator");
		createComposite();
		sShell.setLayout(new RowLayout(SWT.VERTICAL));
		sShell.setSize(267, 220);
		sShell.setImage(SWTResourceManager.getImage("org/sgx/j2s/fractals/media-playback-start.png"));
		menuBar = new Menu(sShell, SWT.BAR);
		MenuItem submenuItem = new MenuItem(menuBar, SWT.CASCADE);
		submenuItem.setText("help");
		submenu = new Menu(submenuItem);
		MenuItem check = new MenuItem(submenu, SWT.CHECK);
		check.setText("about");
		check.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				 MessageBox messageBox =
					   new MessageBox(sShell,
					    SWT.OK|SWT.RESIZE|
					    SWT.ICON_INFORMATION);
					 messageBox.setMessage("This application doesn't try to be a serious fractal generator. \n The idea is to show that hard tasks can also be done in the browser.\nAuthor: Sebastián Gurin (cancerbero_sgx AT sourceforge net)\nenjoy!");
					 
					 messageBox.open();
			}
			public void widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent e) {
			}
		});
		submenuItem.setMenu(submenu);
		sShell.setMenuBar(menuBar);
	}

	/**
	 * 
	 * @return [a,b,c,d] or null if user entries are not correct
	 */
	public double[] getParameters() {
		double a, b, c, d;
		try {
			a = Double.parseDouble(aEntry.getText()); 
			b = Double.parseDouble(bEntry.getText());
			c = Double.parseDouble(cEntry.getText());
			d = Double.parseDouble(dEntry.getText());			
		} catch (Exception e) {
			return null;
		}
		return new double[]{a,b,c,d};
	}
	
	public void setParameters(double[]params) {
		if(params!=null && params.length==4)
		aEntry.setText(params[0]+"");
		bEntry.setText(params[1]+"");
		cEntry.setText(params[2]+"");
		dEntry.setText(params[3]+"");
	}
}
