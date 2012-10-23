/*******************************************************************************
 * Copyright (c) 2000, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.sgx.swt.snippets;
 
/*
 * Composite example snippet: create and dispose children of a composite
 *
 * For a list of all SWT example snippets see
 * http://www.eclipse.org/swt/snippets/
 */
import org.eclipse.swt.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class Parentship {

static int pageNum = 0;
static Composite pageComposite;

public static void main(String args[]) {
//	test2();
//	test1();
//	test3();
//	test4();
//	testGrid();
	testFill();
}
private static void testFill() {
	Display display = new Display();
	Shell shell = new Shell(display);
	FillLayout fillLayout = new FillLayout();
	fillLayout.type = SWT.VERTICAL;
	shell.setLayout(fillLayout);
	new Button(shell, SWT.PUSH).setText("B1");
	new Button(shell, SWT.PUSH).setText("Wide Button 2");
	new Button(shell, SWT.PUSH).setText("Button 3");
	
	shell.pack();
	shell.open();

	while (!shell.isDisposed()) {
		if (!display.readAndDispatch()) display.sleep();
	}
}
private static void testGrid() {
	Display display = new Display();
	Shell shell = new Shell(display);
	GridLayout gridLayout = new GridLayout();
	gridLayout.numColumns = 3;
	shell.setLayout(gridLayout);
	new Button(shell, SWT.PUSH).setText("B1");
	new Button(shell, SWT.PUSH).setText("Wide Button 2");
	new Button(shell, SWT.PUSH).setText("Button 3");
	new Button(shell, SWT.PUSH).setText("B4");
	Button button5 = new Button(shell, SWT.PUSH);
	button5.setText("Button 5");
	
	GridData gridData = new GridData();
	gridData.horizontalAlignment = GridData.FILL;
	gridData.horizontalSpan = 2;
	button5.setLayoutData(gridData);
	
	shell.pack();
	shell.open();

	while (!shell.isDisposed()) {
		if (!display.readAndDispatch()) display.sleep();
	}
}

private static void test4() {
	Display display = new Display();
	Shell shell = new Shell(display);
	GridLayout gridLayout = new GridLayout();
	gridLayout.numColumns = 3;
	shell.setLayout(gridLayout);
	new Button(shell, SWT.PUSH).setText("B1");
	new Button(shell, SWT.PUSH).setText("Wide Button 2");
	new Button(shell, SWT.PUSH).setText("Button 3");
	new Button(shell, SWT.PUSH).setText("B4");
	Button button5 = new Button(shell, SWT.PUSH);
	button5.setText("Button 5");
	
	GridData gridData = new GridData();
	gridData.horizontalAlignment = GridData.FILL;
	gridData.horizontalSpan = 2;
	button5.setLayoutData(gridData);
	
	
	shell.pack();
	shell.open();

	while (!shell.isDisposed()) {
		if (!display.readAndDispatch()) display.sleep();
	}
}

private static void test3() {
	Display display = new Display();
	Shell shell = new Shell(display);
	shell.setLayout(new RowLayout());
	Button button1 = new Button(shell, SWT.PUSH);
	button1.setText("Button 1");
	button1.setLayoutData(new RowData(50, 40));
	Button button2 = new Button(shell, SWT.PUSH);
	button2.setText("Button 2");
	button2.setLayoutData(new RowData(50, 30));
	Button button3 = new Button(shell, SWT.PUSH);
	button3.setText("Button 3");
	button3.setLayoutData(new RowData(50, 20));
	shell.pack();
	shell.open();
	while (!shell.isDisposed()) {
		if (!display.readAndDispatch())
			display.sleep();
	}

}

private static void test2() {
	Display display = new Display();
	final Shell shell = new Shell(display);
	GridLayout layout = new GridLayout();
	layout.numColumns=3;

	
	shell.setLayout(layout);
	
	Button button1 = new Button(shell, SWT.PUSH);
	button1.setText("Push");

	Button button2 = new Button(shell, SWT.PUSH);
	button2.setText("Push");
	GridLayout gridLayout = new GridLayout();
	gridLayout.numColumns = 3;
//	GridData gd2 = new GridData();
//	gd2.horizontalSpan=2;
//	gd2.horizontalAlignment = GridData.FILL;
	GridData gridData = new GridData();
	gridData.horizontalAlignment = GridData.FILL;
	gridData.horizontalSpan = 2;

	button2.setLayoutData(gridData);

	Button button3 = new Button(shell, SWT.PUSH);
	button3.setText("Push");
	
	
	shell.open();
	while (!shell.isDisposed()) {
		if (!display.readAndDispatch())
			display.sleep();
	}
	display.dispose();
}

private static void test1() {
	Display display = new Display();
	final Shell shell = new Shell(display);
	shell.setLayout(new GridLayout());
	Button button = new Button(shell, SWT.PUSH);
	button.setText("Push");
	pageComposite = new Composite(shell, SWT.NONE);
	pageComposite.setLayout(new GridLayout());
	pageComposite.setLayoutData(new GridData());

	button.addListener(SWT.Selection, new Listener() {
		public void handleEvent(Event event) {
			if ((pageComposite != null) && (!pageComposite.isDisposed())) {
				pageComposite.dispose();
			}
			pageComposite = new Composite(shell, SWT.NONE);
			pageComposite.setLayout(new GridLayout());
			pageComposite.setLayoutData(new GridData());
			if (pageNum++ % 2 == 0) {
				Table table = new Table(pageComposite, SWT.BORDER);
				table.setLayoutData(new GridData());
				for (int i = 0; i < 5; i++) {
					new TableItem(table, SWT.NONE).setText("table item " + i);
				}
			} else {
				new Button(pageComposite, SWT.RADIO).setText("radio");
			}
			shell.layout(true);
		}
	});

	System.out.println(button.computeSize(SWT.DEFAULT, SWT.FILL));
	shell.open();
	while (!shell.isDisposed()) {
		if (!display.readAndDispatch())
			display.sleep();
	}
	display.dispose();
}
}