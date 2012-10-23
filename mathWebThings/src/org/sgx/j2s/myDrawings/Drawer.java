package org.sgx.j2s.myDrawings;

import org.sgx.j2s.raphael.Paper;
import org.sgx.j2s.widget.base.Color;

/**
 * I drow concepts of this package in a Raphael's Paper
 * @author SGURIN
 *
 */
public class Drawer {
	org.sgx.j2s.raphael.Paper paper;
	Color fillColor = Color.BLUE, strokeColor = Color.BLACK, backgroundColor=Color.WHITE;
	
	
public Drawer(Paper paper) {
		super();
		this.paper = paper;
	}
public void drawPoint(Point p){
	paper.rect((int)p.x, (int)p.y,1,1).setAttr("stroke", strokeColor.toCSS());
}
public void erasePoint(Point p) {
	Color strokeColor_ = strokeColor;
	strokeColor=backgroundColor;
	drawPoint(p);
	setStrokeColor(strokeColor_);
}
public void drawSegment(LineSegment l, Color c){
	
}
public org.sgx.j2s.raphael.Paper getPaper() {
	return paper;
}
public void setPaper(org.sgx.j2s.raphael.Paper paper) {
	this.paper = paper;
}
public Color getFillColor() {
	return fillColor;
}
public void setFillColor(Color fillColor) {
	this.fillColor = fillColor;
}
public Color getStrokeColor() {
	return strokeColor;
}
public void setStrokeColor(Color strokeColor) {
	this.strokeColor = strokeColor;
}
}
