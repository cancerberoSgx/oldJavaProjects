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
/**
 * Solve a linear system A x = b and compute the residual norm, ||b - A x||.
 * 
 * @author nati
 *
 */
public class SolveLinearPanel extends Composite {

	private Label label = null;
	private Button button = null;
	private Button button1 = null;
	private Browser matrixA_browser = null;
	private Browser matrixB_browser = null;
	private Composite composite = null;
	private Browser matrixX_browser = null;
	private Label label2 = null;
	private Text normEntry = null;
	protected MatrixDialog matrixDialog_A;
	protected String[][] matrix_a;
	protected String[][] matrix_b;
	protected MatrixDialog matrixDialog_B;
	private Composite composite3 = null;
	private Button button2 = null;
	private Label label1 = null;


	public SolveLinearPanel(Composite parent, int style) {
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
				matrixDialog_A = new MatrixDialog(SolveLinearPanel.this.getShell(), "define matrix A");
				matrixDialog_A.addDialogListener(new DialogListener() {
					public void notifyDialogEvent(Dialog d, int event) {
						//TODO: check dialog and event type
						String[][]M1=((MatrixDialog)d).getValue();
						SolveLinearPanel.this.matrix_a = M1;
						String html = Logic.matrixToHtml(M1);
						SolveLinearPanel.this.matrixA_browser.setText(SWTUtils.htmlizeElement(html));
					}					
				});
				matrixDialog_A.open();
			}
		});
		button1 = new Button(this, SWT.NONE);
		button1.setText("define b");
		button1.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				matrixDialog_B = new MatrixDialog(SolveLinearPanel.this.getShell(), "define matrix A");
				matrixDialog_B.addDialogListener(new DialogListener() {
					public void notifyDialogEvent(Dialog d, int event) {
						//TODO: check dialog and event type
						String[][]M1=((MatrixDialog)d).getValue();
						SolveLinearPanel.this.matrix_b = M1;
						String html = Logic.matrixToHtml(M1);
						SolveLinearPanel.this.matrixB_browser.setText(SWTUtils.htmlizeElement(html));
					}					
				});
				matrixDialog_B.open();				
			}
		});
		this.setLayout(gridLayout);
		createComposite3();
		createMatrixA_browser();
		createMatrixB_browser();
		createComposite();
		this.setSize(new Point(548, 392));
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
		composite.setLayout(new GridLayout());
//		composite.setLayout(new RowLayout(SWT.VERTICAL));
		createMatrixX_browser();
		label2 = new Label(composite, SWT.NONE);
		label2.setText("and the residual norm\n||b - A x|| is");
		normEntry = new Text(composite, SWT.BORDER);
	}

	private void createMatrixX_browser() {
		GridData gd = new GridData();
		gd.verticalSpan=2;
		gd.heightHint=130;
		gd.widthHint=100;
		matrixX_browser = new Browser(composite, SWT.NONE);
		matrixX_browser.setLayoutData(gd);
	}

	
	
	/**
	 * This method initializes composite3	
	 *
	 */
	private void createComposite3() {
		GridLayout gridLayout1 = new GridLayout();
		gridLayout1.numColumns = 1;
		composite3 = new Composite(this, SWT.NONE);
		composite3.setLayout(gridLayout1);
		button2 = new Button(composite3, SWT.NONE);
		button2.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				double[][] A = Logic.toDoubleMatrix(SolveLinearPanel.this.matrix_a);
				double[][] B = Logic.toDoubleMatrix(SolveLinearPanel.this.matrix_b);
				Object[] results = Logic.solveSystem(A,B);
				if(results[0]==null) { //cannot compute solution
					MessageBox messageBox = 
						  new MessageBox(SolveLinearPanel.this.getShell(), SWT.OK|SWT.ICON_ERROR);
						if (messageBox.open() == SWT.OK)	{
						  System.out.println("Ok is pressed.");
						}
				}
				double[][]X=(double[][]) results[0];
				String norm = results[1]+"";
				String html = Logic.matrixToHtmlTable(X);
				SolveLinearPanel.this.matrixX_browser.setText(SWTUtils.htmlizeElement(html));
				normEntry.setText(norm);
			}
			
		});

		button2.setText("compute!");
		button2.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				System.out.println("widgetSelected()"); // TODO Auto-generated Event stub widgetSelected()
			}
		});
		label1 = new Label(composite3, SWT.NONE);
		label1.setText("and the result X is :");
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
		new SolveLinearPanel(shell, SWT.NONE);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}


}  //  @jve:decl-index=0:visual-constraint="10,10"
