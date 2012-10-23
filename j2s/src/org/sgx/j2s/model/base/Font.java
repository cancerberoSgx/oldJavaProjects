package org.sgx.j2s.model.base;
/**
 * simple font repr
 * @author sg
 *
 */
public class Font {
	public static final int STYLE_ITALIC = 1;
	public static final int STYLE_BOLD = 2;
	public static final int STYLE_UNDERLINE = 4;
	
String fontFamily;
int size;
int style;
Color color;
public String getFontFamily() {
	return fontFamily;
}
public void setFontFamily(String fontFamily) {
	this.fontFamily = fontFamily;
}
public int getSize() {
	return size;
}
public void setSize(int size) {
	this.size = size;
}
public int getStyle() {
	return style;
}
public void setStyle(int style) {
	this.style = style;
}
public Color getColor() {
	return color;
}
public void setColor(Color color) {
	this.color = color;
}
}
