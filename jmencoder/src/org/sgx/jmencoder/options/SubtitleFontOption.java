package org.sgx.jmencoder.options;

import org.sgx.j2s.model.base.Color;
import org.sgx.j2s.model.base.FontFamily;

public class SubtitleFontOption {
	
//	TODO: bgcolor, blur-radio. borderColor, alpha, etc
	
	private FontFamily fontFamily=new FontFamily("courier");
	String subScale="1";
	Color color=Color.BLACK;
	
	public SubtitleFontOption(FontFamily subvfont, String subScale, Color color) {
		super();
		this.fontFamily = subvfont;
		this.subScale = subScale;
		this.color = color;
	}
	public SubtitleFontOption() {}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public String getSubScale() {
		return subScale;
	}
	public void setSubScale(String subScale) {
		this.subScale = subScale;
	}
	public FontFamily getFontFamily() {
		return fontFamily;
	}
	public void setFontFamily(FontFamily subvfont) {
		this.fontFamily = subvfont;
	}
}
