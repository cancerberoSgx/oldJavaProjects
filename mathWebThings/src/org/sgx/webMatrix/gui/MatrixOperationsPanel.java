package org.sgx.webMatrix.gui;
import com.cloudgarden.resource.SWTResourceManager;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.graphics.Color;
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
import org.sgx.swt.editor.basic.StringTable;
import org.sgx.webMatrix.logic.Logic;
import org.sgx.webMatrix.logic.OperationBuilder;
import org.sgx.webMatrix.logic.ResultsHtmlPrinter;
import org.eclipse.swt.widgets.Combo;

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
/**
 * Solve a linear system A x = b and compute the residual norm, ||b - A x||.
 * 
 * @author nati
 *
 */
public class MatrixOperationsPanel extends Composite {

	{
		//Register as a resource user - SWTResourceManager will
		//handle the obtaining and disposing of resources
		SWTResourceManager.registerResourceUser(this);
	}
	

	private Label label = null;
	private Button button = null;
	private Button button1 = null;
	private Browser matrixA_browser = null;
	private Browser matrixB_browser = null;
	private Browser matrixX_browser = null;
	protected MatrixDialog matrixDialog_A;
	protected String[][] matrix_a;
	protected String[][] matrix_b;
	protected MatrixDialog matrixDialog_B;
	private Composite composite1;
	private Label label5;
	private Label label4;
	private Text text1;
	private Label label2;
	private Button button2 = null;
	private Combo operationsCombo = null;
	private Label label1 = null;
	
	public MatrixOperationsPanel(Composite parent, int style) {
		super(parent, style);
		initialize();
	}

	private void initialize() {
		GridLayout thisLayout = new GridLayout();
		thisLayout.makeColumnsEqualWidth = true;
		thisLayout.numColumns=2;
		this.setLayout(thisLayout);
		
		label = new Label(this, SWT.NONE);
		label.setText("step 1) choose some matrix operation below\nstep 2) define matrix operands");
//		label.setBounds(10, 5, 367, 26);
		{
			label1 = new Label(this, SWT.NONE);
			GridData gd1 = new GridData(SWT.FILL, SWT.FILL, true, true);
			gd1.verticalSpan=2;
			label1.setLayoutData(gd1);
			label1.setText("description:");
//			label1.setBounds(202, 43, 286, 63);
			label1.setForeground(new Color(getDisplay(), 30,130,50));
		}
		{
			operationsCombo = new Combo(this, SWT.NONE);
			operationsCombo.setItems(Logic.getOperationNames()); 
			operationsCombo.setBounds(15, 43, 181, 21);
			operationsCombo.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
				public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
					int ind = operationsCombo.getSelectionIndex();
					String desc = Logic.getOperationDesc(ind);
					label1.setText(desc);
//					label1.getParent().getShell().pack();
//					System.out.println("widgetSelected()"); // TODO Auto-generated Event stub widgetSelected()
				}
				public void widgetDefaultSelected(
						org.eclipse.swt.events.SelectionEvent e) {
				}
			});
			operationsCombo.select(0);
		}
//		{
//			label4 = new Label(this, SWT.NONE);
//		}
		{
			button = new Button(this, SWT.NONE);
			button.setText("define A operand");
			button.setBounds(12, 112, 95, 23);
			button.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
				public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
					matrixDialog_A = new MatrixDialog(MatrixOperationsPanel.this.getShell(), "define matrix A");
					matrixDialog_A.addDialogListener(new DialogListener() {
						public void notifyDialogEvent(Dialog d, int event) {
							//TODO: check dialog and event type
							String[][]M1=((MatrixDialog)d).getValue();
						MatrixOperationsPanel.this.matrix_a = M1;
						String html = Logic.matrixToHtml(M1);
						MatrixOperationsPanel.this.matrixA_browser.setText(SWTUtils.htmlizeElement(html));
						}					
					});
					matrixDialog_A.open();
				}
			});
		}
		//		Label filler = new Label(this, SWT.NONE);
		createOperationsCombo();
		createComposite();
		button1 = new Button(this, SWT.NONE);
		button1.setText("define B operand");
		button1.setBounds(197, 112, 94, 23);
		{
			matrixA_browser = new Browser(this, SWT.NONE);
			GridData matrixA_browserLData = new GridData();
			matrixA_browserLData.horizontalAlignment = GridData.FILL;
			matrixA_browserLData.grabExcessHorizontalSpace = true;
			matrixA_browserLData.grabExcessVerticalSpace = true;
			matrixA_browserLData.verticalAlignment = GridData.FILL;
			matrixA_browserLData.minimumWidth=60;
			matrixA_browser.setLayoutData(matrixA_browserLData);
		}
		button1.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				matrixDialog_B = new MatrixDialog(MatrixOperationsPanel.this.getShell(), "define matrix A");
				matrixDialog_B.addDialogListener(new DialogListener() {
					public void notifyDialogEvent(Dialog d, int event) {
						//TODO: check dialog and event type
						String[][]M1=((MatrixDialog)d).getValue();
						MatrixOperationsPanel.this.matrix_b = M1;
						String html = Logic.matrixToHtml(M1);
						MatrixOperationsPanel.this.matrixB_browser.setText(SWTUtils.htmlizeElement(html));
					}					
				});
				matrixDialog_B.open();				
			}
		});
		{
			matrixB_browser = new Browser(this, SWT.NONE);
			GridData matrixB_browserLData = new GridData();
			matrixB_browserLData.horizontalAlignment = GridData.FILL;
			matrixB_browserLData.verticalAlignment = GridData.FILL;
			matrixB_browserLData.grabExcessVerticalSpace=true;
			matrixB_browserLData.grabExcessHorizontalSpace = true;
			matrixB_browserLData.minimumWidth=60;
			matrixB_browser.setLayoutData(matrixB_browserLData);
//			matrixB_browser.setBounds(198, 141, 181, 80);
		}
		createMatrixA_browser();
		createMatrixB_browser();
		Label filler = new Label(this, SWT.NONE);
		{
			Label filler2 = new Label(this, SWT.NONE);
		}
		{
			button2 = new Button(this, SWT.NONE);
			button2.setText("compute!");
			button2.setBounds(12, 227, 57, 23);	
			button2.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {				
				public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
					double[][] A = Logic.toDoubleMatrix(MatrixOperationsPanel.this.matrix_a);
					double[][] B = Logic.toDoubleMatrix(MatrixOperationsPanel.this.matrix_b);
					String operationNameString=Logic.getOperationName(operationsCombo.getSelectionIndex());
					
					OperationBuilder b = Logic.getOperationBuilderFor(operationNameString);
					b.clearResults();
					b.setA(Logic.toMatrix(A));
					b.setB(Logic.toMatrix(B));
					
					try{
						b.buildResults();
					}catch (Exception e1) {
						MessageBox messageBox = new MessageBox(getShell(), SWT.OK);
						messageBox.setMessage("error computing operation. cause: "+e1.getMessage());
						messageBox.open();
						return;
					}
					ResultsHtmlPrinter printer = new ResultsHtmlPrinter();
					String resultHtml = printer.printHtmlResults(b.getResults());
					MatrixOperationsPanel.this.matrixX_browser.setText(SWTUtils.htmlizeElement(resultHtml));
					
				}
				
			});
		}
		{
			composite1 = new Composite(this, SWT.NONE);
			composite1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
			RowLayout composite1Layout = new RowLayout(org.eclipse.swt.SWT.HORIZONTAL);
			composite1.setLayout(composite1Layout);
			{
				label2 = new Label(composite1, SWT.NONE);
				label2.setText("digits:");
				RowData label2LData = new RowData();
				label2.setLayoutData(label2LData);
			}
			{
				text1 = new Text(composite1, SWT.BORDER);
				text1.setText("" + (Logic.maximDigitLength + 1));
				text1.setBackground(new org.eclipse.swt.graphics.Color(MatrixOperationsPanel.this.getDisplay(),255,255,255));
				RowData text1LData = new RowData();
				text1.setLayoutData(text1LData);
				text1.addKeyListener(new KeyListener() {
					public void keyPressed(KeyEvent arg0) {
						String s = text1.getText()+arg0.character;
						try {
							Logic.maximDigitLength=Integer.parseInt(s);
						} catch (Exception e) {
						}
					}
					public void keyReleased(KeyEvent arg0) {					
					}				
				}); 
			}
		}
		{
			GridData matrixX_browserLData = new GridData();
			matrixX_browserLData.verticalAlignment = GridData.FILL;
			matrixX_browserLData.horizontalAlignment = GridData.FILL;
			matrixX_browserLData.grabExcessHorizontalSpace = true;
			matrixX_browserLData.grabExcessVerticalSpace=true;
			matrixX_browserLData.minimumWidth=60;
			matrixX_browser = new Browser(this, SWT.BORDER);
			matrixX_browser.setLayoutData(matrixX_browserLData);
//			matrixX_browser.setBounds(12, 255, 372, 125);
		}
		{
			label5 = new Label(this, SWT.NONE);
		}
		createComposite3();
		createMatrixX_browser();
		this.setSize(new Point(548, 392));
	}

	
	private void createMatrixA_browser() {	
//		matrixA_browser.setBounds(11, 141, 181, 80);
	}


	private void createMatrixB_browser() {
	}

	
	private void createMatrixX_browser() {
//		gridData1.verticalAlignment = GridData.FILL;
//		gridData1.heightHint=150;
//		gridData1.verticalSpan=2;
//		gridData1.minimumWidth = 150;
//		gridData1.minimumHeight = 150;
	}
	
	private void createComposite3() {
		

	}

	private void createOperationsCombo() {

	}

	/**
	 * This method initializes composite	
	 *
	 */
	private void createComposite() {
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
		final Shell shell = new Shell(display, SWT.MAX|SWT.MIN|SWT.RESIZE);
		shell.setLayout(new FillLayout());
		shell.setSize(new Point(300, 300));
		new MatrixOperationsPanel(shell, SWT.NONE);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}


}  //  @jve:decl-index=0:visual-constraint="10,10"
