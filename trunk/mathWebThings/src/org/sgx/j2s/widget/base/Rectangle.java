package org.sgx.j2s.widget.base;
/**
 * @author sgurin
 *
 */
public class Rectangle {
	
	public Point location;
	public Dimension dimension;
	
	public Rectangle(Point location, Dimension dimension) {
		this.location = location;
		this.dimension = dimension;
	}
	
	public Rectangle(int x, int y, int width, int height) {		
		this(new Point(x,y), new Dimension(width, height));
	}
	
	public Rectangle plus(Rectangle p) {
		return new Rectangle(location.plus(p.location), dimension.plus(p.dimension));
	}
	public Rectangle translate(Point p) {
		return new Rectangle(location.plus(p), dimension);
	}
	public Dimension getDimension() {
		return dimension;
	}

	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public Rectangle clone() {
		return new Rectangle(location.clone(), dimension.clone());
	}
	
	public String toString() {
		return "Rectangle("+location.getX() +","+location.getY()+","+dimension.width+","+dimension.height+")";
	}
	
	
	public int getWidth(){
		return dimension.width;
	}
	public int getX(){
		return location.getX();
	}
	public int getY(){
		return location.getY();
	}
	public int getHeight(){
		return dimension.height;
	}
//	//// for use as a up-right-bottom-left type ////
//	public int getUp(){
//		
//	}
//	public int getRight(){
//			
//		}
//	public int getBottom(){
//		
//	}
//	public int getLeft(){
//		
//	}
//	public static Rectangle buildURBL(int up, String right, String bottom, String )

	public static Rectangle parseRectangle(String s) {
		if(s.startsWith("rect(") && s.endsWith(")")){
			try{
				String[] a = s.substring(5,s.length()-1).split(",");
				return new Rectangle(
						new Point(Integer.parseInt(a[0]),Integer.parseInt(a[1])),
						new Dimension(Integer.parseInt(a[2]), Integer.parseInt(a[3])));
			} catch (Exception e) {
//				throw new RuntimeException("color format error: "+s);
				return null;
			}
		}
		else
			return null;
	}

}
