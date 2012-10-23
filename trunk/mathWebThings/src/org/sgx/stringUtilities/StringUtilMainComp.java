package org.sgx.stringUtilities;

import java.util.Map;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.SWT;
import org.sgx.fplotter.Plotter;
import org.sgx.j2s.soundManager.smallPianoApp.gui.PianoKeyboard;
import org.sgx.swt.SWTUtils;
import org.sgx.swt.dialog.Dialog;
import org.sgx.swt.dialog.DialogListener;
import org.sgx.swt.editor.generic.MapEditor_2columns;


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
public class StringUtilMainComp extends org.eclipse.swt.widgets.Composite {
	private Label label1;
	private MenuItem menuItem1;
	private Menu menu2;
	private MenuItem help;
	private Menu menu1;
	private Button button2;
	private MapEditor_2columns optionEditor;
	private Button button1;
	private Combo combo1;
	private Composite composite1;
	private Label label2;
	private Text text1;
	protected String selectedOptionName;
	protected GridData optionEditorGData;

	/**
	* Auto-generated main method to display this 
	* org.eclipse.swt.widgets.Composite inside a new Shell.
	*/
	public static void main(String[] args) {
		showGUI();
	}
		
	/**
	* Auto-generated method to display this 
	* org.eclipse.swt.widgets.Composite inside a new Shell.
	*/
	public static void showGUI() {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		StringUtilMainComp inst = new StringUtilMainComp(shell, SWT.NULL);
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

	public StringUtilMainComp(org.eclipse.swt.widgets.Composite parent, int style) {
		super(parent, style);
		initGUI();
	}

	private void initGUI() {
		try {
			{
				label2 = new Label(this, SWT.NONE);
				label2.setText("select the operation:");
			}
			{
				composite1 = new Composite(this, SWT.NONE);
				RowLayout composite1Layout = new RowLayout(org.eclipse.swt.SWT.HORIZONTAL);
				GridData composite1LData = new GridData();
				composite1LData.horizontalAlignment = GridData.CENTER;
				composite1.setLayoutData(composite1LData);
				composite1.setLayout(composite1Layout);
				{
					combo1 = new Combo(composite1, SWT.NONE);
					combo1.setItems(Options.getOperationNames());
					combo1.setText(Options.getOperationNames()[0]);//"select operation");
					combo1.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent arg0) {
							int ind = combo1.getSelectionIndex();
							selectedOptionName = Options.getOperationNames()[ind];
							optionEditor.dispose();
							optionEditor=new MapEditor_2columns(StringUtilMainComp.this, SWT.BORDER);
							optionEditor.setLayoutData(optionEditorGData);
							Map<String, Object> op = Options.getOperationOptionsFor(selectedOptionName);
							
							if(op!=null) {
								((MapEditor_2columns)optionEditor).setValue(op);
								StringUtilMainComp.this.layout();
							}
						}
						
					});
					
				}
				{
					button1 = new Button(composite1, SWT.PUSH | SWT.CENTER);
					button1.setText("help");
					RowData button1LData = new RowData();
					button1.setLayoutData(button1LData);
					button1.addSelectionListener(new SelectionListener(){
//						@Override
						public void widgetDefaultSelected(SelectionEvent arg0) {}
//						@Override
						public void widgetSelected(SelectionEvent arg0) {
							String desc = Options.getDescriptionFor(selectedOptionName);
							SWTUtils.showTextMessageDialog(StringUtilMainComp.this.getShell(),
									"operation "+selectedOptionName, desc);
							
							
						}						
					});
				}
				{
					button2 = new Button(composite1, SWT.PUSH | SWT.CENTER);
					button2.setText("make it!");
					button2.addSelectionListener(new SelectionAdapter(){
						@Override
						public void widgetSelected(SelectionEvent arg0) {
							Map<String, Object> config = optionEditor.getValue();
							StringOperation op = Options.getOperationFor(selectedOptionName);
							try {
								String input = text1.getText();
								String out = op.make(input, config);
								text1.setText(out);
							}catch (Exception e) {
								SWTUtils.showTextMessageDialog(StringUtilMainComp.this.getShell(), 
										selectedOptionName+" error", "selectedOptionName error: "+e.getLocalizedMessage());
								}
							optionEditor.layout();
						}						
					});
				}
			}
			{
				optionEditor = new MapEditor_2columns(this, SWT.NONE);
				GridLayout optionEditorLayout = new GridLayout();
				optionEditorLayout.makeColumnsEqualWidth = true;
				optionEditor.setLayout(optionEditorLayout);
			}
			optionEditorGData = new GridData();
			optionEditorGData.grabExcessHorizontalSpace=true;
			optionEditorGData.grabExcessVerticalSpace=true;
			optionEditorGData.horizontalAlignment=GridData.FILL;
			optionEditorGData.verticalAlignment=GridData.FILL;
			GridLayout thisLayout = new GridLayout();
			thisLayout.makeColumnsEqualWidth = true;
			this.setLayout(thisLayout);
			this.setSize(426, 279);
			{
				menu1 = new Menu(getShell(), SWT.BAR);
				getShell().setMenuBar(menu1);
				{
					help = new MenuItem(menu1, SWT.CASCADE);
					help.setText("help");
					{
						menu2 = new Menu(help);
						help.setMenu(menu2);
						{
							menuItem1 = new MenuItem(menu2, SWT.PUSH);
							menuItem1.setText("about");
							menuItem1.addSelectionListener(new SelectionAdapter(){
								@Override
								public void widgetSelected(SelectionEvent e) {
									SWTUtils.showTextMessageDialog(getShell(),
											"about", "lots to strings operations easily made thanks\n to java2scipt2\n"+
													"\nauthor: Sebastian Gurin \n<sgurin@montevideo.com.uy>");
								}
							});
						}
					}
				}
			}
			{
				label1 = new Label(this, SWT.NONE);
				label1.setText("Paste your string here:");
			}
			{
				text1 = new Text(this, SWT.BORDER|SWT.MULTI|SWT.SCROLL_PAGE);
				GridData text1LData = new GridData();
				text1LData.verticalAlignment = GridData.FILL;
				text1LData.horizontalAlignment = GridData.FILL;
				text1LData.grabExcessVerticalSpace = true;
				text1LData.grabExcessHorizontalSpace = true;
				text1.setLayoutData(text1LData);
				text1.setText("text1");
			}
			this.layout();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
