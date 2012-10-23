package org.sgx.webMatrix.tests;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.layout.FillLayout;
import org.sgx.swt.SWTUtils;
import org.sgx.swt.editor.basic.StringTable;

public class CopyOfMatrixDialog {

	private Shell shell = null;  //  @jve:decl-index=0:visual-constraint="125,48"
	private Composite main = null;
	private Composite composite = null;
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
	

	/**
	 * This method initializes sShell
	 */
	public void createSShell() {
		shell = new Shell(SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setText("Shell");
		shell.setLayout(new FillLayout());
		shell.setSize(new Point(280, 278));
	}


	public Shell getShell() {
		return shell;
	}
	
	
	/**
	 * This method initializes main	
	 *
	 */
	private void createMain() {
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		main = new Composite(getShell(), SWT.NONE);
		main.setLayout(gridLayout);
		createTabFolder();
	}


	/**
	 * This method initializes composite	
	 *
	 */
	private void createComposite() {
		GridLayout gridLayout1 = new GridLayout();
		gridLayout1.numColumns = 2;
		composite = new Composite(main, SWT.NONE);
		composite.setLayout(gridLayout1);
		label = new Label(composite, SWT.NONE);
		label.setText("Enter matrix dimensions");
		filler = new Label(composite, SWT.NONE);
		label1 = new Label(composite, SWT.RIGHT);
		label1.setText("columns:");
		columns = new Text(composite, SWT.BORDER);
		label2 = new Label(composite, SWT.RIGHT);
		label2.setText("rows:");
		rows = new Text(composite, SWT.BORDER);
	}


	/**
	 * This method initializes tabFolder	
	 *
	 */
	private void createTabFolder() {
		tabFolder = new TabFolder(main, SWT.NONE);
		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("visual");
		TabItem tabItem1 = new TabItem(tabFolder, SWT.NONE);
		tabItem1.setText("text");
	}


	/**
	 * This method initializes composite1	
	 *
	 */
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
					e2.printStackTrace();
					//TODO
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
		composite2 = new Composite(tabFolder, SWT.NONE);
		composite2.setLayout(new RowLayout(SWT.VERTICAL));
		label3 = new Label(composite2, SWT.NONE);
		label3.setText("enter comma separated matrix field values,\nfrom left to right and bottom to top");
		textArea = new Text(composite2, SWT.MULTI | SWT.V_SCROLL);
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
		CopyOfMatrixDialog thisClass = new CopyOfMatrixDialog();
		thisClass.createSShell();
		thisClass.shell.open();

		while (!thisClass.shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

}
