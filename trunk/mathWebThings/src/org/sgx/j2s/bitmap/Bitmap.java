package org.sgx.j2s.bitmap;

import org.sgx.j2s.widget.base.Color;

public interface Bitmap {

	public abstract void paint(int x, int y, Color c);
	
	public abstract void line(int x1, int y1, int x2, int y2, Color c);

	public abstract void erase(Color white);
	
	public abstract void flush();
	
	/**it must:
	 * 
	 * if in java returns a composite. if in html returns the html dom element representing the bitmap
	 */
	Object getRepresentation();
	
}