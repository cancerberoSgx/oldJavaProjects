package org.sgx.swing.j2s.model.base;
/**
 * clase creada para que el editor muestre todas las font families del sistema
 * @author SGURIN
 *
 */
public class FontFamily {
String fontFamily="courier";

public FontFamily(String fontFamily) {
	super();
	this.fontFamily = fontFamily;
}
public FontFamily(){}
public String getFontFamily() {
	return fontFamily;
}

public void setFontFamily(String fontFamily) {
	this.fontFamily = fontFamily;
}
public String toString(){return fontFamily;}
}
