package org.sgx.swing.j2s.model.base;
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

//	public static Rectangle<Integer> one() {
//		return new Rectangle<Integer>(0,0,50,50);
//	}
	
	public Rectangle clone() {
		return new Rectangle(location.clone(), dimension.clone());
	}
	
	public String toString() {
		return "Rectangle("+location.x +","+location.y+","+dimension.width+","+dimension.height+")";
	}
}
