package org.sgx.j2s.widget.base;

public class Point extends Magnitude {
	
	public int x, y;
	
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public Point(int x, int y, String unit) {		
		super(unit);
		this.x = x;
		this.y = y;
	}
	public Point plus(Point p) {
		return new Point(x+p.x, y+p.y);
	}
	public Point negate() {
		return new Point(-x, -y);
	}
	public boolean equals(Point p) {
		return x==p.x && y==p.y;
	}
	
	public Point clone() {
		return new Point(x,y);
	}
	
	public String toString() {
		return "("+x+","+y+")";
	}
//	/**
//	 * 
//	 * @param s it if supposed to be of the form '(' Magnitude ',' int ')'
//	 * @return
//	 */
//	public static Point parsePoint(String s){
//		
//	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public static Point parsePoint(String s) {
		if(s.startsWith("(") && s.endsWith(")")){
			try{
				String[] a = s.substring(5,s.length()-1).split(",");
				return new Point(Integer.parseInt(a[0]),Integer.parseInt(a[1]));
			} catch (Exception e) {
//				throw new RuntimeException("color format error: "+s);
				return null;
			}
		}
		else
			return null;
	}
}
