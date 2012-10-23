package org.sgx.swt.layout;

public class RealRectangle {
public float x,y,width,height;

public RealRectangle(float x, float y, float width, float height) {
	super();
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
}
public String toString () {
	return "Rectangle {" + x + ", " + y + ", " + width + ", " + height + "}"; //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
}
}
