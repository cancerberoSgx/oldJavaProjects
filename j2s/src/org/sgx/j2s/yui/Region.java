package org.sgx.j2s.yui;


/**
 * representation of a YUI Region Object
 * @author SGURIN
 *
 */
public abstract class Region {

	public int bottom, left, right, top;

	public Region(int top, int right, int bottom, int left) {
		super();
		this.bottom = bottom;
		this.left = left;
		this.right = right;
		this.top = top;
	}
	
	public abstract boolean contains(Region r) ;
	public abstract int getArea();
	
	/**
	 * @j2sNative
	 * return YAHOO.util.Region.getRegion(htmlElement);
	 */
	public static Region getRegion(Object htmlElement){return null;}
	
	public abstract Region intersect(Region r);
	
	public abstract String toString();
	
	public abstract Region union(Region r);
}
