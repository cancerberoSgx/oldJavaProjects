package org.sgx.j2s.bitmap.html2;

public interface BitmapPoblator {

	public abstract void poblate(Object elem, int width, int height);

	public abstract String getPixelId(int x, int y);

}