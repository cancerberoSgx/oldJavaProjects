package org.sgx.swt.editor.piano;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.sgx.swt.SWTUtils;

public class Key extends Composite{
static final Color WHITE_COLOR = SWTUtils.colorToSWT(org.sgx.j2s.widget.base.Color.WHITE); 
static final Color BLACK_COLOR = SWTUtils.colorToSWT(org.sgx.j2s.widget.base.Color.BLACK);
	boolean white;
	public Key(Composite parent, int style, boolean white) {
		super(parent, SWT.BORDER);
		this.white=white;
		init();
	}
	private void init() {
		if(white)
			this.setBackground(WHITE_COLOR);
		else			
			this.setBackground(BLACK_COLOR);
	}

}

