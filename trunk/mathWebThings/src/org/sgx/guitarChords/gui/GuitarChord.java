package org.sgx.guitarChords.gui;

import org.eclipse.swt.widgets.Composite;

public class GuitarChord extends Composite {

	/**
	 * Create the composite
	 * @param parent
	 * @param style
	 */
	public GuitarChord(Composite parent, int style) {
		super(parent, style);
		//
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
