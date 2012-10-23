package org.sgx.fplotter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.sgx.j2s.html.myApi.DomUtils;
import org.sgx.j2s.html.myApi.Element;
import org.sgx.j2s.soundManager.smallPianoApp.gui.PianoKeyboard;
import org.sgx.j2s.utils.JsUtils;
import org.sgx.j2s.widget.base.Rectangle;
import org.sgx.math.parser.Function;
import org.sgx.swt.SWTUtils;
import org.sgx.swt.widgets.HTMLComposite;


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
public class Plotter {

	protected Shell shell;
	private Composite htmlComposite;
	private Composite composite1;

	private PlotterCanvas _canvas;
	org.sgx.j2s.widget.base.Color selectedPlotColor = org.sgx.j2s.widget.base.Color.BLACK;

//	protected HTMLComposite canvasComp=null;
	protected Shell canvasShell=null;

	public Plotter(){
		createContents();
		init();
	}
	/**
	 * Open the window
	 */
	public void open() {
		final Display display = Display.getDefault();
		createContents();
		shell.setText("my js plotter");
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

	/**
	 * Create contents of the window
	 */
	protected void createContents() {
		shell = new Shell( Display.getDefault());
		shell.setLayout(new FillLayout());
		shell.setSize(500, 375);
		shell.setText("SWT Application");

		final TabFolder tabFolder = new TabFolder(shell, SWT.NONE);

		final TabItem unoTabItem = new TabItem(tabFolder, SWT.NONE);
		unoTabItem.setText("plot");

		final Composite composite = new Composite(tabFolder, SWT.NONE);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		
		composite.setLayout(gridLayout);
		unoTabItem.setControl(composite);

		final Label functionLabel = new Label(composite, SWT.NONE);
		functionLabel.setLayoutData(new GridData());
		functionLabel.setText("function:");

		final Text functionEntry = new Text(composite, SWT.BORDER);
		functionEntry.setText("sin(x^2)/x");
		final GridData gd_functionEntry = new GridData(SWT.FILL, SWT.CENTER, true, false);
		functionEntry.setLayoutData(gd_functionEntry);

		final Label colorLabel = new Label(composite, SWT.NONE);
		colorLabel.setText("color:");

		final Composite composite_3 = new Composite(composite, SWT.NONE);
		composite_3.setLayoutData(new GridData());
		composite_3.setLayout(new RowLayout());

		final Button button_1 = new Button(composite_3, SWT.NONE);
		RowData button_1LData = new RowData();
		button_1LData.width = 38;
		button_1LData.height = 23;
		button_1.setLayoutData(button_1LData);
		button_1.setText("...");
		
		final Composite colorPlot = new Composite(composite_3, SWT.NONE);
		colorPlot.setLayout(new RowLayout());
		RowData rd_colorPlot = new RowData();
		rd_colorPlot.height = 21;
		rd_colorPlot.width = 11;
		colorPlot.setLayoutData(rd_colorPlot);
		colorPlot.setBackground(SWTUtils.colorToSWT(this.selectedPlotColor));
	
		button_1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				ColorDialog dialog = new ColorDialog(shell);
		        dialog.setRGB(new RGB(255, 255, 255));
		        RGB newColor = dialog.open();
		        if (newColor == null) {
		          return;
		        }
		        colorPlot.setBackground(new Color(shell.getDisplay(), newColor));
		        selectedPlotColor = SWTUtils.toSgxColor(newColor);
			}
		});
		colorLabel.setText("graph color:");	
		new Label(composite, SWT.NONE);

		final Composite composite_2 = new Composite(composite, SWT.NONE);
		composite_2.setLayout(new RowLayout());
		composite_2.setLayoutData(new GridData());
		{
			composite1 = new Composite(composite, SWT.NONE);
			GridLayout composite1Layout = new GridLayout();
			composite1Layout.makeColumnsEqualWidth = true;
			GridData composite1LData = new GridData();
			composite1LData.widthHint = 32;
			composite1LData.heightHint = 21;
			composite1.setLayoutData(composite1LData);
			composite1.setLayout(composite1Layout);
		}
		{
			htmlComposite = new HTMLComposite(composite, SWT.NONE);
			GridLayout composite2Layout = new GridLayout();
			composite2Layout.makeColumnsEqualWidth = true;
			GridData composite2LData = new GridData();
			composite2LData.widthHint = 472;
			composite2LData.heightHint = 226;
			htmlComposite.setLayoutData(composite2LData);
			htmlComposite.setLayout(composite2Layout);
		}

		final Button plotButton = new Button(composite_2, SWT.NONE);
		final RowData rd_plotButton = new RowData();
		rd_plotButton.width = 50;
		plotButton.setLayoutData(rd_plotButton);

//		JsUtils.firebugDebug();
//		/**
//		 * @j2sNative
//		 * debugger;
//		 */{}
		plotButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
		
				//plot
				String functionStr = functionEntry.getText();
				Function f = new Function(functionStr);				
				_canvas.drawFunction(f, selectedPlotColor);
				
				Element canvasEl = (Element) _canvas.getBitmap().getRepresentation();
				
				
				if(canvasShell==null) {					
					canvasShell = new Shell(Display.getCurrent());
					canvasShell.setSize(_canvas.getCanvasW(),_canvas.getCanvasH());
					canvasShell.setLayout(new FillLayout());				
					
					HTMLComposite htmlcomp = new HTMLComposite(canvasShell, SWT.NONE);
					canvasShell.open();
					htmlcomp.setHtmlElement( canvasEl);
					Display.getDefault().readAndDispatch();
				}
			}
		});
		plotButton.setText("plot");

		final Button cleanButton = new Button(composite_2, SWT.NONE);
		final RowData rd_cleanButton = new RowData();
		rd_cleanButton.width = 49;
		cleanButton.setLayoutData(rd_cleanButton);
		cleanButton.setText("clean");
		cleanButton.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
			public void widgetSelected(SelectionEvent arg0) {
				_canvas.clean();
			}			
		});

		final TabItem configTabItem = new TabItem(tabFolder, SWT.NONE);
		configTabItem.setText("config");

		final Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		final GridLayout gridLayout_1 = new GridLayout();
		gridLayout_1.numColumns = 2;
		composite_1.setLayout(gridLayout_1);
		configTabItem.setControl(composite_1);

		final Label backgroundColorLabel = new Label(composite_1, SWT.NONE);
		backgroundColorLabel.setLayoutData(new GridData());
		backgroundColorLabel.setText("background color:");

		final Composite composite_3_1 = new Composite(composite_1, SWT.NONE);
		composite_3_1.setLayoutData(new GridData());
		composite_3_1.setLayout(new RowLayout());

		final Button button_1_1 = new Button(composite_3_1, SWT.NONE);
		button_1_1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
			}
		});
		button_1_1.setText("...");

		final Composite backgroundColorPlot = new Composite(composite_3_1, SWT.NONE);
		final RowData rd_backgroundColorPlot = new RowData();
		rd_backgroundColorPlot.width = 11;
		rd_backgroundColorPlot.height = 21;
		backgroundColorPlot.setLayoutData(rd_backgroundColorPlot);
		backgroundColorPlot.setLayout(new RowLayout());

		final Label canvasWidthLabel = new Label(composite_1, SWT.NONE);
		canvasWidthLabel.setText("canvas width:");

		final Text canvasWidthEntry = new Text(composite_1, SWT.BORDER);
		canvasWidthEntry.setText("600");
		final GridData gd_canvasWidthEntry = new GridData(SWT.FILL, SWT.CENTER, true, false);
		canvasWidthEntry.setLayoutData(gd_canvasWidthEntry);

		final Label canvasHeightLabel = new Label(composite_1, SWT.NONE);
		canvasHeightLabel.setText("canvas height:");

		final Text canvasHeightEntry = new Text(composite_1, SWT.BORDER);
		canvasHeightEntry.setText("600");
		final GridData gd_canvasHeightEntry = new GridData(SWT.FILL, SWT.CENTER, true, false);
		canvasHeightEntry.setLayoutData(gd_canvasHeightEntry);

		final Label xRangeLabel_1 = new Label(composite_1, SWT.NONE);
		xRangeLabel_1.setText("x range");

		final Composite composite_4_2 = new Composite(composite_1, SWT.NONE);
		composite_4_2.setLayoutData(new GridData());
		composite_4_2.setLayout(new RowLayout());

		final Text text = new Text(composite_4_2, SWT.BORDER);
		text.setText("-5.0");

		final Label toLabel_2 = new Label(composite_4_2, SWT.NONE);
		toLabel_2.setText("to:");

		final Text text_1 = new Text(composite_4_2, SWT.BORDER);
		text_1.setText("5.0");

		final Label yRangeLabel = new Label(composite_1, SWT.NONE);
		yRangeLabel.setText("y range");

		final Composite composite_4_1_1 = new Composite(composite_1, SWT.NONE);
		composite_4_1_1.setLayoutData(new GridData());
		composite_4_1_1.setLayout(new RowLayout());

		final Text text_2 = new Text(composite_4_1_1, SWT.BORDER);
		text_2.setText("-5.0");

		final Label toLabel_1_1 = new Label(composite_4_1_1, SWT.NONE);
		toLabel_1_1.setText("to:");

		final Text text_3 = new Text(composite_4_1_1, SWT.BORDER);
		text_3.setText("5.0");

		final Button showGridButton = new Button(composite_1, SWT.CHECK);
		showGridButton.setLayoutData(new GridData());
		showGridButton.setText("show grid");

		final Composite composite_5 = new Composite(composite_1, SWT.NONE);
		composite_5.setLayout(new RowLayout());

		final Label sizeLabel = new Label(composite_5, SWT.NONE);
		sizeLabel.setText("size:");

		final Text gridSizeEntry = new Text(composite_5, SWT.BORDER);
		gridSizeEntry.setText("0.5");

		final Button showGridButton_1 = new Button(composite_1, SWT.CHECK);
		showGridButton_1.setLayoutData(new GridData());
		showGridButton_1.setText("show X axis");

		final Composite composite_5_1 = new Composite(composite_1, SWT.NONE);
		composite_5_1.setLayoutData(new GridData());
		composite_5_1.setLayout(new RowLayout());

		final Label sizeLabel_1 = new Label(composite_5_1, SWT.NONE);
		sizeLabel_1.setText("tics separation:");

		final Text gridSizeEntry_1 = new Text(composite_5_1, SWT.BORDER);
		gridSizeEntry_1.setText("0.5");

		final Label sizeLabel_1_1 = new Label(composite_5_1, SWT.NONE);
		sizeLabel_1_1.setText("title:");

		final Text gridSizeEntry_1_1 = new Text(composite_5_1, SWT.BORDER);
		final RowData rd_gridSizeEntry_1_1 = new RowData();
		rd_gridSizeEntry_1_1.width = 78;
		gridSizeEntry_1_1.setLayoutData(rd_gridSizeEntry_1_1);
		gridSizeEntry_1_1.setText("x");

		final Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);

		final MenuItem newSubmenuMenuItem = new MenuItem(menu, SWT.CASCADE);
		newSubmenuMenuItem.setText("help");

		final Menu menu_1 = new Menu(newSubmenuMenuItem);
		newSubmenuMenuItem.setMenu(menu_1);

		final MenuItem newItemMenuItem = new MenuItem(menu_1, SWT.NONE);
		newItemMenuItem.setText("about");
		newItemMenuItem.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {				
			}
			public void widgetSelected(SelectionEvent arg0) {		
				MessageBox msg = new MessageBox(Display.getDefault().getActiveShell(), SWT.ICON_INFORMATION|SWT.RESIZE);
				msg.getParent().setSize(600,600);
				msg.setText("About");
				msg.setMessage("Simple function plotter made in java - j2s.\n" +
						"this toy applications try to ilustrate another browser uncommon task example ;)\n"+
						"Author: Sebastian Gurin\n   <sgurin at montevideo.com.uy>\n");
				msg.open();			
			}			
		});

		final MenuItem newItemMenuItem_1 = new MenuItem(menu_1, SWT.NONE);
		newItemMenuItem_1.setText("user manual");
		newItemMenuItem_1.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {				
			}

			public void widgetSelected(SelectionEvent arg0) {
				SWTUtils.showTextMessageDialog(Plotter.this.shell,
						"manual", "just type your f=f(x) expression, choose a plot color and plot it!\n" +
								"supports +, -, *, /, ^, sin, cos, ln, exp, \n" +
								"tan, asin, acos, atan, root, log operators \n" +
								"\nauthor: Sebastian Gurin \n<sgurin@montevideo.com.uy>");
				
//				MessageBox msg = new MessageBox(Display.getDefault().getActiveShell(), SWT.ICON_INFORMATION);
//				msg.getParent().setSize(400,400);
//				msg.setMessage("This is a simple y=f(x) function plotter\n" +
//						"The following mathematical operations are supported:\n" +
//						"+ - * / ( ) ^ root log sin cos tan asin acos atan\n" +
//						"author:sgurin@montevideo.com.uy");
//				msg.setText("little help.");
//				msg.open();
			}
			
		});
		
		
		init();
	}
	
	private void init() {
		_canvas = new PlotterCanvas(200,200,500,500); 
		selectedPlotColor = org.sgx.j2s.widget.base.Color.BLACK; 
	}

	/**
	 * Launch the application
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			final Plotter window = new Plotter();
			window.open();
			window.shell.addShellListener(new ShellListener() {
				public void shellActivated(ShellEvent arg0) {
				}
				public void shellClosed(ShellEvent arg0) {
					window.canvasShell.close();
				}
				public void shellDeactivated(ShellEvent arg0) {
				}
				public void shellDeiconified(ShellEvent arg0) {
				}
				public void shellIconified(ShellEvent arg0) {
				}				
			});
//			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
