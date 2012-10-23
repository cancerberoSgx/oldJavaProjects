package org.sgx.webMatrix.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
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
import org.sgx.editor.Editor;
import org.sgx.editor.EditorListener;
import org.sgx.swt.SWTUtils;
import org.sgx.swt.dialog.AbstractDialog;
import org.sgx.swt.editor.basic.StringTable;


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
public class MatrixDialog extends AbstractDialog implements Editor<String[][]>{

	private Point shellDefaultSize=new Point(280, 378);
	
	public static final int DIALOG_ACCEPTED = 1;
	
//	private Shell shell = null;  //  @jve:decl-index=0:visual-constraint="125,48"
	private Composite main = null;
	private Composite composite = null;
	private Menu menu1;
	private Label label = null;
	private Label filler = null;
	private Label label1 = null;
	private Text columns = null;
	private Label label2 = null;
	private Text rows = null;
	private TabFolder tabFolder = null;
	private Composite composite1 = null;
	private Button button = null;
	private Composite matrixEditorContainer = null;
	private Composite composite2 = null;
	private Label label3 = null;
	private Text textArea = null;
	protected StringTable matrixEditor;
	private Shell parentShell;
	private String title;

	private Button button1 = null;

	private Composite comp1;  //  @jve:decl-index=0:
	
	public MatrixDialog(Shell parentShell_, String title_) {
		super(parentShell_);
		parentShell=parentShell_;
		title = title_;
		createSShell(); 
	}
	
	public int[] getSelection() {
		int c, r;
		try {
			c=Integer.parseInt(columns.getText());
			r=Integer.parseInt(rows.getText());
		} catch (Exception e) {
			return null;
		}
		return new int[]{c,r};
	}
	
	public void createSShell() {
		shell.setText(title);
		shell.setLayout(new RowLayout(SWT.VERTICAL));
		
		shell.setSize(shellDefaultSize);
		createMain();
//		shell.pack();
	}
	
	private void createMain() {
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		main = new Composite(getShell(), SWT.NONE);
		{			
			menu1 = new Menu(getShell(), SWT.BAR);
			getShell().setMenuBar(menu1);
			MenuItem i1 = new MenuItem(menu1, SWT.NONE);
			i1.setText("unig this editor"); 
			i1.addSelectionListener(new SelectionListener() {
				public void widgetDefaultSelected(SelectionEvent arg0) {					
				}
				public void widgetSelected(SelectionEvent arg0) {

					MessageBox messageBox = new MessageBox(shell, SWT.OK|SWT.HELP);
					messageBox.setMessage("1) give desired matrix dimensions (rows, columns)\n" +
							"2)click \"update\" button and fill the matrix");
						messageBox.open();
				}				
			});
		}
		createComposite();
		main.setLayout(gridLayout);
//		main.setLayout(new RowLayout(SWT.VERTICAL));
		createTabFolder();
		button1 = new Button(main, SWT.NONE);

		comp1=new Composite(main, SWT.NONE);

		comp1.setLayout(new FillLayout());
		button1.setText("accept");
		button1.addSelectionListener(new SelectionListener(){
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
			public void widgetSelected(SelectionEvent arg0) {
				
				MatrixDialog.this.notifyListeners(MatrixDialog.this, MatrixDialog.DIALOG_ACCEPTED);
				MatrixDialog.this.getShell().dispose();
			}			
		});
	}

	private void createComposite() {
		GridLayout gridLayout1 = new GridLayout();
		gridLayout1.numColumns = 2;
		composite = new Composite(main, SWT.NONE);
		composite.setLayout(gridLayout1);
		label = new Label(composite, SWT.NONE);
		label.setText("Enter matrix dimensions");
		filler = new Label(composite, SWT.NONE);
		label1 = new Label(composite, SWT.RIGHT);
		label1.setText("rows:");
		rows = new Text(composite, SWT.BORDER);
		label2 = new Label(composite, SWT.RIGHT);
		label2.setText("columns:");
		columns = new Text(composite, SWT.BORDER);
	}

	private void createTabFolder() {
		GridData tabFolderLayoutData = new GridData();
		tabFolderLayoutData.horizontalSpan =2;
		tabFolder = new TabFolder(main, SWT.NONE);
		tabFolder.setLayoutData(tabFolderLayoutData);
		createComposite1();
		createComposite2();
		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("visual");
		tabItem.setControl(composite1);
		TabItem tabItem1 = new TabItem(tabFolder, SWT.NONE);
		tabItem1.setText("text");
		tabItem1.setControl(composite2);
	}

	private void createComposite1() {
		GridLayout gridLayout2 = new GridLayout();
		gridLayout2.numColumns = 1;
		composite1 = new Composite(tabFolder, SWT.NONE);
		composite1.setLayout(gridLayout2);
		button = new Button(composite1, SWT.NONE);
		button.setText("update dimensions");
		button.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				int r=0, c=0;
				try {
					r= Integer.parseInt(rows.getText());
					c= Integer.parseInt(columns.getText());
				} catch (Exception e2) {
					MessageBox messageBox = new MessageBox(shell, SWT.OK);
					messageBox.setMessage("invalid matrix dimensions: "+e2.getLocalizedMessage());
						messageBox.open();
						return;
				}
				SWTUtils.compositeDestroyAllChildrens(matrixEditorContainer);
				String[][] data = new String[r][c];
				
				matrixEditor = new StringTable(matrixEditorContainer, SWT.NONE, data);
				matrixEditor.getShell().pack();
			}
		});
		createMatrixEditorContainer();
	}


	private void createMatrixEditorContainer() {
		matrixEditorContainer = new Composite(composite1, SWT.NONE);
		matrixEditorContainer.setLayout(new FillLayout());
	}

	private void createComposite2() {
//		GridData d = new GridData();
//		d.widthHint=200;
//		d.heightHint=150;
//		d.horizontalAlignment=GridData.FILL;
//		d.verticalAlignment=GridData.FILL;
//		d.grabExcessHorizontalSpace =true;
//		d.grabExcessVerticalSpace =true;
		composite2 = new Composite(tabFolder, SWT.NONE);
		composite2.setLayout(new FillLayout());
//		composite2.setLayoutData(d);
//		GridLayout l = new GridLayout();
//		l.numColumns=1;
//		composite2.setLayout(l);
//		label3 = new Label(composite2, SWT.NONE);
		String header = "enter comma separated matrix field values,\nfrom left to right and bottom to top";
		
//		label3.setText("enter comma separated matrix field values,\nfrom left to right and bottom to top");
		textArea = new Text(composite2, SWT.MULTI | SWT.V_SCROLL);
		textArea.setToolTipText(header);
	}


	public void open() {
		Display display = shell.getDisplay();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

	public Shell getShell() {
		return shell;
	}
	
	public String[][] getValue() {
		return matrixEditor.getValue();
	}

	
	public void setValue(Object val) {
		String[][] value = (String[][])val;
		matrixEditor.setValue(value);
		rows.setText(matrixEditor.getRows()+"");
		columns.setText(matrixEditor.getColumns()+"");
	}


	public void addEditorListener(EditorListener l) {
		// TODO Auto-generated method stub
		
	}
	public void removeEditorListener(EditorListener l) {
		// TODO Auto-generated method stub
		
	}

	public String getLastError() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/* Before this is run, be sure to set up the launch configuration (Arguments->VM Arguments)
		 * for the correct SWT library path in order to run with the SWT dlls. 
		 * The dlls are located in the SWT plugin jar.  
		 * For example, on Windows the Eclipse SWT 3.1 plugin jar is:
		 *       installation_directory\plugins\org.eclipse.swt.win32_3.1.0.jar
		 */
		Display display = Display.getDefault();
		Shell shell = new Shell (display);
		MatrixDialog thisClass = new MatrixDialog(shell, "probe");
		thisClass.createSShell();
		thisClass.shell.open();

		while (!thisClass.shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}



}
