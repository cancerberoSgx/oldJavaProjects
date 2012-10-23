package org.sgx.webMatrix.gui;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
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
public class CopyOfMatrixOperationsPanel extends Composite {

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
	private Text text1;
	private Label label2;
	private Button button2 = null;
	private Combo operationsCombo = null;
	private Label label1 = null;
	
	public CopyOfMatrixOperationsPanel(Composite parent, int style) {
		super(parent, style);
		initialize();
	}

	private void initialize() {
		this.setLayout(null);
		label = new Label(this, SWT.NONE);
		label.setText("step 1) choose some matrix operation below\nstep 2) define matrix operands");
		label.setBounds(10, 5, 367, 26);
		{
			label1 = new Label(this, SWT.NONE);
			label1.setText("description:");
			label1.setBounds(202, 43, 286, 63);
			label1.setForeground(new Color(getDisplay(), 30,130,50));
		}
		{
			button = new Button(this, SWT.NONE);
			button.setText("define A operand");
			button.setBounds(12, 112, 95, 23);
			button.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
				public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
					matrixDialog_A = new MatrixDialog(CopyOfMatrixOperationsPanel.this.getShell(), "define matrix A");
					matrixDialog_A.addDialogListener(new DialogListener() {
						public void notifyDialogEvent(Dialog d, int event) {
							//TODO: check dialog and event type
							String[][]M1=((MatrixDialog)d).getValue();
						CopyOfMatrixOperationsPanel.this.matrix_a = M1;
						String html = Logic.matrixToHtml(M1);
						CopyOfMatrixOperationsPanel.this.matrixA_browser.setText(SWTUtils.htmlizeElement(html));
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
		button1.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				matrixDialog_B = new MatrixDialog(CopyOfMatrixOperationsPanel.this.getShell(), "define matrix A");
				matrixDialog_B.addDialogListener(new DialogListener() {
					public void notifyDialogEvent(Dialog d, int event) {
						//TODO: check dialog and event type
						String[][]M1=((MatrixDialog)d).getValue();
						CopyOfMatrixOperationsPanel.this.matrix_b = M1;
						String html = Logic.matrixToHtml(M1);
						CopyOfMatrixOperationsPanel.this.matrixB_browser.setText(SWTUtils.htmlizeElement(html));
					}					
				});
				matrixDialog_B.open();				
			}
		});
		Label filler4 = new Label(this, SWT.NONE);
		createMatrixA_browser();
		createMatrixB_browser();
		Label filler = new Label(this, SWT.NONE);
		createComposite3();
		Label filler2 = new Label(this, SWT.NONE);
		Label filler3 = new Label(this, SWT.NONE);
		createMatrixX_browser();
		this.setSize(new Point(548, 392));
	}

	
	private void createMatrixA_browser() {	
		matrixA_browser = new Browser(this, SWT.NONE);
		matrixA_browser.setBounds(11, 141, 181, 80);
	}


	private void createMatrixB_browser() {
		matrixB_browser = new Browser(this, SWT.NONE);
		matrixB_browser.setBounds(198, 141, 181, 80);
	}

	
	private void createMatrixX_browser() {
//		gridData1.verticalAlignment = GridData.FILL;
//		gridData1.heightHint=150;
//		gridData1.verticalSpan=2;
//		gridData1.minimumWidth = 150;
//		gridData1.minimumHeight = 150;
		matrixX_browser = new Browser(this, SWT.NONE);
		matrixX_browser.setBounds(12, 255, 372, 125);
		{
			operationsCombo = new Combo(this, SWT.NONE);
			operationsCombo.setItems(Logic.getOperationNames()); 
			operationsCombo.setBounds(15, 43, 181, 21);
			operationsCombo.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
				public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
					int ind = operationsCombo.getSelectionIndex();
					String desc = Logic.getOperationDesc(ind);
					label1.setText(desc);
					label1.getParent().getShell().pack();
					System.out.println("widgetSelected()"); // TODO Auto-generated Event stub widgetSelected()
				}
				public void widgetDefaultSelected(
						org.eclipse.swt.events.SelectionEvent e) {
				}
			});
			operationsCombo.select(0);
		}
		{
			button2 = new Button(this, SWT.NONE);
			button2.setText("compute!");
			button2.setBounds(12, 227, 57, 23);	
			button2.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {				
				public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
					double[][] A = Logic.toDoubleMatrix(CopyOfMatrixOperationsPanel.this.matrix_a);
					double[][] B = Logic.toDoubleMatrix(CopyOfMatrixOperationsPanel.this.matrix_b);
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
					CopyOfMatrixOperationsPanel.this.matrixX_browser.setText(SWTUtils.htmlizeElement(resultHtml));
					
				}
				
			});
		}
		{
			label2 = new Label(this, SWT.NONE);
			label2.setText("digits:");
			label2.setBounds(107, 227, 43, 22);
		}
		{
			text1 = new Text(this, SWT.NONE);
			text1.setText(""+(Logic.maximDigitLength+1));
			text1.setBackground(new  org.eclipse.swt.graphics.Color(CopyOfMatrixOperationsPanel.this.getDisplay(), 255,255,255));
			text1.setBounds(150, 227, 52, 22);
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
		final Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		shell.setSize(new Point(500, 300));
		new CopyOfMatrixOperationsPanel(shell, SWT.NONE);
		shell.open();
		shell.addControlListener(new ControlListener() {

			public void controlMoved(ControlEvent arg0) {
				shell.pack();
			}

			public void controlResized(ControlEvent arg0) {
				shell.pack();
			}
			
		});
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}


}  //  @jve:decl-index=0:visual-constraint="10,10"
