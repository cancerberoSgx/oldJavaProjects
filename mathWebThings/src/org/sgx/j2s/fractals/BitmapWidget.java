package org.sgx.j2s.fractals;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.sgx.j2s.bitmap.Bitmap;
import org.sgx.j2s.html.myApi.Element;
import org.sgx.j2s.utils.JsUtils;
import org.sgx.swt.widgets.HTMLComposite;

public class BitmapWidget extends Composite{

	private Bitmap bitmap;
	private Composite holder;

	public BitmapWidget(Composite parent, int style, Bitmap bitmap) {
		super(parent, style);
		this.bitmap = bitmap;
		init();
	}

	private void init() {
		this.setLayout(new FillLayout());
		if(JsUtils.isJs()) {
			holder = new HTMLComposite(this, SWT.NONE);
			((HTMLComposite)holder).setHtmlElement( (Element) bitmap.getRepresentation());			
		} else {
			holder = (Composite) bitmap.getRepresentation();
		}
	}

}
