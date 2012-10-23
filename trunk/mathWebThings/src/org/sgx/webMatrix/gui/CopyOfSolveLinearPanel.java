package org.sgx.webMatrix.gui;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Text;
import org.sgx.math.Jama.Matrix;
import org.sgx.swt.SWTUtils;
import org.sgx.swt.dialog.Dialog;
import org.sgx.swt.dialog.DialogListener;
import org.sgx.webMatrix.logic.Logic;
import org.eclipse.swt.layout.RowData;
/**
 * Solve a linear system A x = b and compute the residual norm, ||b - A x||.
 * 
 * @author nati
 *
 */
public class CopyOfSolveLinearPanel extends Composite {

	private Label label = null;
	private Button button = null;
	private Button button1 = null;
	private Browser matrixA_browser = null;
	private Browser matrixB_browser = null;
	private Button button2 = null;
	private Composite composite = null;
	private Label label1 = null;
	private Browser matrixX_browser = null;
	private Label label2 = null;
	private Text normEntry = null;
	protected MatrixDialog matrixDialog_A;
	protected String[][] matrix_a;
	protected String[][] matrix_b;
	protected MatrixDialog matrixDialog_B;
	private Composite composite1 = null;
	private Composite composite2 = null;


	public CopyOfSolveLinearPanel(Composite parent, int style) {
		super(parent, style);
		initialize();
	}

	private void initialize() {
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		label = new Label(this, SWT.NONE);
		label.setText("Solve a linear system A x = b and compute the residual norm ||b - A x||.\nIf A is not square give a least square solution\n");
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.horizontalSpan=3;
		label.setLayoutData(gridData);
//		Label filler = new Label(this, SWT.NONE);
//		Label filler1 = new Label(this, SWT.NONE);
		button = new Button(this, SWT.NONE);
		button.setText("define A");
		button.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				matrixDialog_A = new MatrixDialog(CopyOfSolveLinearPanel.this.getShell(), "define matrix A");
				matrixDialog_A.addDialogListener(new DialogListener() {
					public void notifyDialogEvent(Dialog d, int event) {
						//TODO: check dialog and event type
						String[][]M1=((MatrixDialog)d).getValue();
						CopyOfSolveLinearPanel.this.matrix_a = M1;
						String html = Logic.matrixToHtml(M1);
						CopyOfSolveLinearPanel.this.matrixA_browser.setText(SWTUtils.htmlizeElement(html));
					}					
				});
				matrixDialog_A.open();
			}
		});
		button1 = new Button(this, SWT.NONE);
		button1.setText("define b");
		button1.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				matrixDialog_B = new MatrixDialog(CopyOfSolveLinearPanel.this.getShell(), "define matrix A");
				matrixDialog_B.addDialogListener(new DialogListener() {
					public void notifyDialogEvent(Dialog d, int event) {
						//TODO: check dialog and event type
						String[][]M1=((MatrixDialog)d).getValue();
						CopyOfSolveLinearPanel.this.matrix_b = M1;
						String html = Logic.matrixToHtml(M1);
						CopyOfSolveLinearPanel.this.matrixB_browser.setText(SWTUtils.htmlizeElement(html));
					}					
				});
				matrixDialog_B.open();				
			}
		});
		this.setLayout(gridLayout);
		button2 = new Button(this, SWT.NONE);
		button2.setText("compute!");
		button2.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				double[][] A = Logic.toDoubleMatrix(CopyOfSolveLinearPanel.this.matrix_a);
				double[][] B = Logic.toDoubleMatrix(CopyOfSolveLinearPanel.this.matrix_b);
				Object[] results = Logic.solveSystem(A,B);
				if(results[0]==null) { //cannot compute solution
					MessageBox messageBox = 
						  new MessageBox(CopyOfSolveLinearPanel.this.getShell(), SWT.OK|SWT.ICON_ERROR);
						if (messageBox.open() == SWT.OK)	{
						  System.out.println("Ok is pressed.");
						}
				}
				double[][]X=(double[][]) results[0];
				String norm = results[1]+"";
				String html = Logic.matrixToHtmlTable(X);
				CopyOfSolveLinearPanel.this.matrixX_browser.setText(SWTUtils.htmlizeElement(html));
				normEntry.setText(norm);
			}
			
		});
		createMatrixA_browser();
		createMatrixB_browser();
		createComposite();
		this.setSize(new Point(429, 200));
	}

	
	private void createMatrixA_browser() {	
		matrixA_browser = new Browser(this, SWT.NONE);
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.widthHint=130;
		gridData.heightHint=130;
//		gridData.horizontalSpan = 2;
		matrixA_browser.setLayoutData(gridData);	
	}


	private void createMatrixB_browser() {
		matrixB_browser = new Browser(this, SWT.NONE);
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.widthHint=130;
		gridData.heightHint=130;
		matrixB_browser.setLayoutData(gridData);
	}

	
	private void createComposite() {
		composite = new Composite(this, SWT.NONE);
		composite.setLayout(new RowLayout(SWT.VERTICAL));
		label1 = new Label(composite, SWT.NONE);
		label1.setText("x=");
		createMatrixX_browser();
		label2 = new Label(composite, SWT.NONE);
		label2.setText("and the residual norm ||b - A x|| is");
		normEntry = new Text(composite, SWT.BORDER);
		createComposite1();
		createComposite2();
	}

	private void createMatrixX_browser() {
		RowData rowData = new org.eclipse.swt.layout.RowData();
		rowData.height = 120;
		rowData.width = 80;
		matrixX_browser = new Browser(composite, SWT.NONE);
		matrixX_browser.setLayoutData(rowData);
	}

	
	
	/**
	 * This method initializes composite1	
	 *
	 */
	private void createComposite1() {
		composite1 = new Composite(composite, SWT.NONE);
		composite1.setLayout(new RowLayout());
	}

	/**
	 * This method initializes composite2	
	 *
	 */
	private void createComposite2() {
		composite2 = new Composite(composite, SWT.NONE);
		composite2.setLayout(new RowLayout());
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
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		shell.setSize(new Point(500, 300));
		new CopyOfSolveLinearPanel(shell, SWT.NONE);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}


}  //  @jve:decl-index=0:visual-constraint="10,10"
