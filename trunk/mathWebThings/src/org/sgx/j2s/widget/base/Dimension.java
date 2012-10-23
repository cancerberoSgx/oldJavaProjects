package org.sgx.j2s.widget.base;
/**
 * 
 * @author sgurin
 *
 */
public class Dimension extends Magnitude {
	public Dimension(String unit, int width, int height) {
		super(unit);
		this.width = width;
		this.height = height;
	}

	public int width, height;
	
	public Dimension(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}
	public Dimension plus(Dimension p) {
		return new Dimension(width+p.width, height+p.height);
	}
	public boolean equals(Dimension p) {
		return width==p.width && height==p.height;
	}
	
	public Dimension clone() {
		return new Dimension(width, height);
	}
	
	public static Dimension parseDimension(String s) {
		if(s.startsWith("(") && s.endsWith(")")){
			try{
				String[] a = s.substring(5,s.length()-1).split(",");
				return new Dimension(Integer.parseInt(a[0]),Integer.parseInt(a[1]));
			} catch (Exception e) {
//				throw new RuntimeException("color format error: "+s);
				return null;
			}
		}
		else
			return null;
	}
}
