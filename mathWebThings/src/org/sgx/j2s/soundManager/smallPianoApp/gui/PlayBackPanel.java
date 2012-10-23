package org.sgx.j2s.soundManager.smallPianoApp.gui;

import java.util.Map;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Button;
import org.sgx.j2s.smallPianoApp.Recorder;
import org.sgx.j2s.soundManager.Sound;
import org.sgx.swt.SWTUtils;
import org.sgx.swt.dialog.AskForTextDialog;
import org.sgx.swt.dialog.Dialog;
import org.sgx.swt.dialog.DialogListener;
import org.eclipse.swt.widgets.Label;

public class PlayBackPanel extends Composite {
	//my stuff (not visual)
	Text keyInput;
	Recorder recorder;
	Map<String, Sound> currentInstrument;  //  @jve:decl-index=0:
	
	public Map<String, Sound> getInstrument() {
		return currentInstrument;
	}
	public void setInstrument(Map<String, Sound> instrument) {
		this.currentInstrument = instrument;
	}
	public Text getKeyInput() {
		return keyInput;
	}
	public void setKeyInput(Text keyInput) {
		this.keyInput = keyInput;
	}
	

	private Button button = null;
	private Button record = null;
	private Button button1 = null;
	private Button button2 = null;
	private Button save = null;
	private Label statusLabel = null;
	protected String currentInstrumentName;  //  @jve:decl-index=0:



	public PlayBackPanel(Composite parent, int style) {
		super(parent, style);
		
		//my stuff
		recorder = new Recorder();
		
		initialize();
	}


	private void initialize() {
		RowLayout rowLayout = new RowLayout();
		rowLayout.spacing = 8;
		this.setLayout(rowLayout);
		button = new Button(this, SWT.NONE);
		button.setText("play");
		button.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
//				recorder.setCurrentInstrumentName(currentInstrumentName);
				recorder.playRecordedWith(currentInstrumentName);
//				instrument
			}
		});
		record = new Button(this, SWT.NONE);
		record.setText("stop");
		record.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {				
				recorder.stopRecording(keyInput);	
				statusLabel.setText("/* buffer ready */");
			}
		});
		button1 = new Button(this, SWT.NONE);
		button1.setText("record");
		button1.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				
				statusLabel.setText("/* recording... */");
				recorder.startRecording(keyInput);	
			}
		});
		button2 = new Button(this, SWT.NONE);
		button2.setText("load");
		button2.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				
				SWTUtils.askForTextDialog(getShell(),
						"load song", "paste a song here:", "",
						new DialogListener(){
//							@Override
							public void notifyDialogEvent(Dialog d,
									int event) {
								loadRecorder(((AskForTextDialog)d).getSelectedText());
								
							}									
						
					}
				);
			}
		});
	
		save = new Button(this, SWT.NONE);
		save.setText("save");
		save.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				SWTUtils.askForTextDialog(getShell(),
						"save song", "this is your song",recorder.saveCurrentRecord(),null);
			}
		});
		statusLabel = new Label(this, SWT.NONE);
		statusLabel.setText("/* empty */");
	}

	private void loadRecorder(String s) {
		try {
			recorder.loadFrom(s);
		} catch (Exception e) {
			SWTUtils.showTextMessageDialog(PlayBackPanel.this.getShell(),
					"error", "there was an error loading the song:\n"+
					e.getLocalizedMessage()+"\nsee the console for more information");
			
		}
	}

	public String getCurrentInstrumentName() {
		return currentInstrumentName;
	}
	public void setCurrentInstrumentName(String currentInstrumentName) {
		this.currentInstrumentName = currentInstrumentName;
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
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		shell.setSize(new Point(300, 200));
		new PlayBackPanel(shell, SWT.NONE);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

}
