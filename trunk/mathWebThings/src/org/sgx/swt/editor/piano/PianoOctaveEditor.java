package org.sgx.swt.editor.piano;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.sgx.j2s.utils.JsUtils;
import org.sgx.j2s.utils.Utils;
import org.sgx.j2s.widget.base.Color;
import org.sgx.swt.SWTUtils;
import org.sgx.swt.editor.AbstractEditor;

public abstract class PianoOctaveEditor<T> extends AbstractEditor<T>{

	Key[] keys = new Key[12];
	int[] whiteKeyIndexes = new int[]{0,2,4,5,7,9,11};
	int[] blackKeyIndexes = new int[]{1,3,6,8,10};
	Map<Integer, String> keyNames;
	
//	int[] keysZIndexes = new int[]{1,3,6,8,10,0,2,4,5,7,9,11};
//	private Composite whiteKeysContainer;
//	private Composite blackKeysContainer;
	
	public PianoOctaveEditor(Composite parent, int style) {
		super(parent, style);
		keys = new Key[12];
		keyNames = (Map)Utils.toMap(new Object[][]{
				{0, "Do"},	{1, "Do#"},	{2, "Re"},	{3, "Re#"},	{4, "Mi"},	{5, "Fa"},
				{6, "Fa#"},	{7, "Sol"},	{8, "Sol#"},{9, "La#"},	{10, "La#"},{11, "Si"}
		});
		init();
	}

	private void init() {
		setLayout(null);
		addControlListener(new ControlListener(){
			public void controlMoved(ControlEvent arg0) {
			}
			public void controlResized(ControlEvent arg0) {
				drawPiano();				
			}			
		});
		drawPiano();
		updatePiano();
	}

	private void updatePiano() {
		Point size = PianoOctaveEditor.this.getSize();
		int noteW = (size.x - (7*noteMargin))/7; //7 equal width white notes
	}

	int noteMargin = 4;
	double blackHeightRatio=0.5;
	/**will create the piano keys. use updatePiano for resize updating
	 * call this only once!
	 */
	protected void drawPiano() {
//		int[] zindexes = null;		
		if(JsUtils.isJs()) {//fixing java2script bug 2538784
//			zindexes=new int[]{1,3,6,8,10,0,2,4,5,7,9,11};
			for (int i = 0; i < blackKeyIndexes.length; i++) {
				
			}
		}else {
//			zindexes=new int[]{0,2,4,5,7,9,11,1,3,6,8,10};
		}
//		for (int i = 0; i < zindexes.length; i++) {
//			int index = zindexes[i];
//			keys[index]=new Key
//		}
	}

}
