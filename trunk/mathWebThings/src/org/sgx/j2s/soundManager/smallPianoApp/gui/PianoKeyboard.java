package org.sgx.j2s.soundManager.smallPianoApp.gui;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Text;
import org.sgx.j2s.smallPianoApp.Note;
import org.sgx.j2s.smallPianoApp.Options;
import org.sgx.j2s.soundManager.Sound;
import org.sgx.j2s.soundManager.SoundManager;
import org.sgx.j2s.utils.Utils;
import org.sgx.swt.SWTUtils;
import org.sgx.swt.dialog.Dialog;
import org.sgx.swt.dialog.DialogListener;


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
public class PianoKeyboard extends Composite {
	//public static final String mp3Base = "/mathWebThings/static/res/";

	private static final String APP_TITLE = "my js piano";
	
	

private SoundManager sm;

private Text text;
private Label label1;
private Composite composite2;
private Composite composite1;
private List combo1;
private Label label3;
private MenuItem menuItem3;
private MenuItem menuItem2;
private Menu menu2;
private MenuItem menuItem1;
private Menu menu1;
private Label label2;
private Slider slider1;

private boolean soundsLoaded=false;

protected int volumen;


/** in this context an instrument is simple a mapping of keyboard keys to sounds */
Map<String,Sound> currentInstrument;
private Map<String, Map<String,Sound>> instruments= new HashMap<String, Map<String,Sound>>();

private String[] instrumentNames= new String[]{"piano1", "violin1"};

private String defaultInstrumentName;

private PlayBackPanel playBackPanel;

/**
 * Create the shell
 * @param display
 * @param style
 */
public PianoKeyboard(Composite parent, int style) {
	super(parent, style);
	createContents();
	init();
}

public static String getMp3NameFor(String note, int octave) {
	return Options.RESOURCE_BASE_URL+note.toUpperCase()+octave+".mp3";
}
private void init() {
	//init sound manager
	sm = SoundManager.getInstance();
//	sm.setDebugMode(false);
	if(!sm.supported())
		System.err.println("SoundManager not supported");
	sm.setBaseUrl("");	
	
}


private void loadSounds(){
	soundsLoaded=true;
	
	Map piano1 = (Map)Utils.toMap(new Object[][]{
			{"z", sm.createSound("piano1-C4", Options.getSoundUrl("piano1","C",false,4))},
			{"s", sm.createSound("piano1-Db4", Options.getSoundUrl("piano1","D",true,4))}, 
			{"x", sm.createSound("piano1-D4", Options.getSoundUrl("piano1","D",false,4))},//TODO: the rest 
			{"d", sm.createSound("piano1-Eb4", Options.getSoundUrl("piano1","E",true,4))},
			{"c", sm.createSound("piano1-E4", Options.getSoundUrl("piano1","E",false,4))},
			{"v", sm.createSound("piano1-F4", Options.getSoundUrl("piano1","F",false,4))},
			{"g", sm.createSound("piano1-Gb4", Options.getSoundUrl("piano1","G",true,4))},
			{"b", sm.createSound("piano1-G4", Options.getSoundUrl("piano1","G",false,4))},
			{"h", sm.createSound("piano1-Ab4", Options.getSoundUrl("piano1","A",true,4))},
			{"n", sm.createSound("piano1-A4", Options.getSoundUrl("piano1","A",false,4))},
			{"j", sm.createSound("piano1-Bb4", Options.getSoundUrl("piano1","B",true,4))},
			{"m", sm.createSound("piano1-B4", Options.getSoundUrl("piano1","B",false,4))},
			
			{"q", sm.createSound("piano1-C5", Options.getSoundUrl("piano1","C",false,5))},
			{"2", sm.createSound("piano1-Db5", Options.getSoundUrl("piano1","D",true,5))},
			{"w", sm.createSound("piano1-D5", Options.getSoundUrl("piano1","D",false,5))},
			{"3", sm.createSound("piano1-Eb5", Options.getSoundUrl("piano1","E",true,5))},
			{"e", sm.createSound("piano1-E5", Options.getSoundUrl("piano1","E",false,5))},
			{"r", sm.createSound("piano1-F5", Options.getSoundUrl("piano1","F",false,5))},
			{"5", sm.createSound("piano1-Gb5", Options.getSoundUrl("piano1","G",true,5))},
			{"t", sm.createSound("piano1-G5", Options.getSoundUrl("piano1","G",false,5))},
			{"6", sm.createSound("piano1-Ab5", Options.getSoundUrl("piano1","A",true,5))},
			{"y", sm.createSound("piano1-A5", Options.getSoundUrl("piano1","A",false,5))},
			{"7", sm.createSound("piano1-Bb5", Options.getSoundUrl("piano1","B",true,5))},
			{"u", sm.createSound("piano1-B5", Options.getSoundUrl("piano1","B",false,5))},
	});
	instruments.put("piano1",piano1);
	
	Map violin1 = (Map)Utils.toMap(new Object[][]{
			{"z", sm.createSound("violin1-C4", Options.getSoundUrl("violin1","C",false,4))},
			{"s", sm.createSound("violin1-Db4", Options.getSoundUrl("violin1","D",true,4))},
			{"x", sm.createSound("violin1-D4", Options.getSoundUrl("violin1","D",false,4))},
			{"d", sm.createSound("violin1-Eb4", Options.getSoundUrl("violin1","E",true,4))},
			{"c", sm.createSound("violin1-E4", Options.getSoundUrl("violin1","E",false,4))},
			{"v", sm.createSound("violin1-F4", Options.getSoundUrl("violin1","F",false,4))},
			{"g", sm.createSound("violin1-Gb4", Options.getSoundUrl("violin1","G",true,4))},
			{"b", sm.createSound("violin1-G4", Options.getSoundUrl("violin1","G",false,4))},
			{"h", sm.createSound("violin1-Ab4", Options.getSoundUrl("violin1","A",true,4))},
			{"n", sm.createSound("violin1-A4", Options.getSoundUrl("violin1","A",false,4))},
			{"j", sm.createSound("violin1-Bb4", Options.getSoundUrl("violin1","B",true,4))},
			{"m", sm.createSound("violin1-B4", Options.getSoundUrl("violin1","B",false,4))},
			   
			{"q", sm.createSound("violin1-C5", Options.getSoundUrl("violin1","C",false,5))},
			{"2", sm.createSound("violin1-Db5", Options.getSoundUrl("violin1","D",true,5))},
			{"w", sm.createSound("violin1-D5", Options.getSoundUrl("violin1","D",false,5))},
			{"3", sm.createSound("violin1-Eb5", Options.getSoundUrl("violin1","E",true,5))},
			{"e", sm.createSound("violin1-E5", Options.getSoundUrl("violin1","E",false,5))},
			{"r", sm.createSound("violin1-F5", Options.getSoundUrl("violin1","F",false,5))},
			{"5", sm.createSound("violin1-Gb5", Options.getSoundUrl("violin1","G",true,5))},
			{"t", sm.createSound("violin1-G5", Options.getSoundUrl("violin1","G",false,5))},
			{"6", sm.createSound("violin1-Ab5", Options.getSoundUrl("violin1","A",true,5))},
			{"y", sm.createSound("violin1-A5", Options.getSoundUrl("violin1","A",false,5))},
			{"7", sm.createSound("violin1-Bb5", Options.getSoundUrl("violin1","B",true,5))},
			{"u", sm.createSound("violin1-B5", Options.getSoundUrl("violin1","B",false,5))},
	});
	instruments.put("violin1",violin1);
	
	System.out.println(piano1);
	setInstrument("piano1");
}

/**
 * Create contents of the window
 */
protected void createContents() {
//	setText("SWT Application");
	setSize(500, 375);
	GridLayout thisLayout = new GridLayout();
	thisLayout.numColumns=2;
	thisLayout.makeColumnsEqualWidth = true;
	this.setLayout(thisLayout);
	{
		GridData playBackPanelLData = new GridData();
		playBackPanelLData.horizontalSpan = 2;
		playBackPanelLData.horizontalAlignment = GridData.FILL;
		playBackPanelLData.grabExcessHorizontalSpace = true;
		playBackPanel = new PlayBackPanel(this, SWT.BORDER);		
		playBackPanel.setLayoutData(playBackPanelLData);
	}
	{
		label3 = new Label(this, SWT.NONE);
		GridData label3LData = new GridData();
		label3LData.horizontalAlignment = GridData.END;
		label3.setLayoutData(label3LData);
		label3.setText("instrument:");
	}
	{
		combo1 = new List(this, SWT.NONE);
		combo1.setItems(instrumentNames);
//		combo1.setText(defaultInstrumentName);		
		combo1.addSelectionListener(new SelectionListener(){
//			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
//			@Override
			public void widgetSelected(SelectionEvent arg0) {
				 String instr = PianoKeyboard.this.instrumentNames[combo1.getSelectionIndex()];
				 setInstrument(instr);
			}
			
		});
	}
	

	{
		menu1 = new Menu(getShell(), SWT.BAR);
		getShell().setMenuBar(menu1);
		{
			menuItem1 = new MenuItem(menu1, SWT.CASCADE);
			menuItem1.setText("help");
			{
				menu2 = new Menu(menuItem1);
				menuItem1.setMenu(menu2);
				{
					menuItem2 = new MenuItem(menu2, SWT.PUSH);
					menuItem2.setText("about");
					menuItem2.addSelectionListener(new SelectionListener(){
//						@Override
						public void widgetDefaultSelected(SelectionEvent arg0) {
						}
//						@Override
						public void widgetSelected(SelectionEvent arg0) {
							SWTUtils.showTextMessageDialog(PianoKeyboard.this.getShell(),
									"about", "this a toy application showing the use of soundmanager2\n" +
											"library inside a java2script program.\n" +
											"\nauthor: Sebastian Gurin \n<sgurin@montevideo.com.uy>");
						}
						
					});
				}
				{
					menuItem3 = new MenuItem(menu2, SWT.PUSH);
					menuItem3.setText("usage");
					menuItem3.addSelectionListener(new SelectionListener(){
//						@Override
						public void widgetDefaultSelected(SelectionEvent arg0) {}
//						@Override
						public void widgetSelected(SelectionEvent arg0) {
							SWTUtils.showTextMessageDialog(PianoKeyboard.this.getShell(),
									"about", "you have two octaves for playing:\n" +
											"first is:  z-s-x-d-c-v-g-b-h-n-j-m\n" +
											"second is: q-2-w-3-e-r-5-t-6-y-7-u\n" +
											"\nfor example, try typying z-z-z-z-x-c-c-x-c-v-b\n" +
											"or: q-q-w-q-r-e-q-q-w-q-t-r" +
											"\nauthor: Sebastian Gurin \n<sgurin@montevideo.com.uy>");
						}
						
					});
				}
			}
		}
	}
	{
		label1 = new Label(this, SWT.NONE);
		GridData label1LData = new GridData();
		label1LData.horizontalAlignment = GridData.END;
		label1.setLayoutData(label1LData);
		label1.setText("volume:");
	}
	{
		slider1 = new Slider(this, SWT.NONE);
		slider1.setMaximum(110);
		slider1.setMinimum(0);
		slider1.setIncrement(1);
		slider1.setSelection(volumen);
		
		slider1.addSelectionListener(new SelectionListener(){
//			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
//			@Override
			public void widgetSelected(SelectionEvent arg0) {
				volumen=slider1.getSelection();
				updateVolumen();
			}			
		});
		
	}
	{
		label2 = new Label(this, SWT.NONE);
		label2.setText("type inside this entry for playing notes:");
	}
	{
		text = new Text(this, SWT.BORDER);
		GridData textLData = new GridData();
		textLData.verticalAlignment = GridData.FILL;
		textLData.verticalSpan = 2;
		textLData.horizontalSpan = 2;
		textLData.horizontalAlignment = GridData.FILL;
		textLData.grabExcessVerticalSpace = true;
		text.setLayoutData(textLData);
		text.setText("focus this entry");
		text.addKeyListener(new KeyListener() {
//			@Override
			public void keyPressed(KeyEvent arg0) {
					if(!soundsLoaded)
						loadSounds();
					String key = arg0.character+"";	
					int ret = Options.pressNote(currentInstrument, key);
					if (ret==1) { //BUFFERING
						SWTUtils.showTextMessageDialog(PianoKeyboard.this.getShell(),
								"sound still loading...", "please wait until it finnish");
					}
			}
//			@Override
			public void keyReleased(KeyEvent arg0) {
					String key = arg0.character+"";
					Options.releaseNote(currentInstrument, key);
			}				
		});
		
		//my stuff 
		playBackPanel.setKeyInput(text);
	}

}


	public void setInstrument(String instr) {
		 currentInstrument=PianoKeyboard.this.instruments.get(instr);
		 playBackPanel.setInstrument(currentInstrument);
		 playBackPanel.setCurrentInstrumentName(instr);
	}

	protected void updateVolumen() {
		System.out.println("updateVolumen:"+volumen+" - ");	for(String key : currentInstrument.keySet()) {
			Sound s = currentInstrument.get(key);
			if(s!=null)
				System.out.print(key+" vol = "+s.getVolume());
				s.setVolume(volumen);
		}
	}

	/**
	 * Launch the application
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			Shell shell = new Shell(display, SWT.SHELL_TRIM);
			shell.setText("my small piano");
			shell.setLayout(new FillLayout());
			PianoKeyboard piano = new PianoKeyboard(shell, SWT.NONE);
			
			shell.open();
			shell.layout();
			shell.setSize(400,400);
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch())
					display.sleep();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
