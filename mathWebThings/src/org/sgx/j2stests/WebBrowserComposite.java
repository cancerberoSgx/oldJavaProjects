package org.sgx.j2stests;

/*
SWT/JFace in Action
GUI Design with Eclipse 3.0
Matthew Scarpino, Stephen Holder, Stanford Ng, and Laurent Mihalkovic

ISBN: 1932394273

Publisher: Manning
*/


import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.sgx.j2s.soundManager.smallPianoApp.gui.PlayBackPanel;

public class WebBrowserComposite extends Composite {
  private Browser browser;

  public WebBrowserComposite(Composite parent) {
    super(parent, SWT.NONE);

    GridLayout layout = new GridLayout(2, true);
    setLayout(layout);

    browser = new Browser(this, SWT.NONE);
    GridData layoutData = new GridData(GridData.FILL_BOTH);
    layoutData.horizontalSpan = 2;
    layoutData.verticalSpan = 2;
    browser.setLayoutData(layoutData);
    browser.setUrl("http://www.slashdot.org");

    final Text text = new Text(this, SWT.BORDER|SWT.SINGLE);
    layoutData = new GridData(GridData.FILL_HORIZONTAL);
    text.setLayoutData(layoutData);

    Button openButton = new Button(this, SWT.PUSH);
    openButton.setText("Open");
    openButton.addSelectionListener(new SelectionListener() {
      public void widgetSelected(SelectionEvent e) {
        browser.setUrl(text.getText());
      }

      public void widgetDefaultSelected(SelectionEvent e) {
      }
    });

    Button backButton = new Button(this, SWT.PUSH);
    backButton.setText("Back");
    backButton.addSelectionListener(new SelectionListener() {
      public void widgetSelected(SelectionEvent e) {
        browser.back();
      }

      public void widgetDefaultSelected(SelectionEvent e) {
      }
    });

    Button forwardButton = new Button(this, SWT.PUSH);
    forwardButton.setText("Forward");
    forwardButton.addSelectionListener(new SelectionListener() {
      public void widgetSelected(SelectionEvent e) {
        browser.forward();
      }

      public void widgetDefaultSelected(SelectionEvent e) {
      }
    });
  }
  
  public static void main(String[] args) {
	  Display display = Display.getDefault();
	  Shell shell = new Shell(display);
	  shell.setLayout(new FillLayout());
	  shell.setSize(new Point(300, 200));
	  new WebBrowserComposite(shell);
	  shell.open();

	  while (!shell.isDisposed()) {
		  if (!display.readAndDispatch())
			  display.sleep();
	  }
	  display.dispose();
  }
 
}
