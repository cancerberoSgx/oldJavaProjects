package org.sgx.j2s.yui.widget;

import org.sgx.j2s.model.base.Font;
import org.sgx.j2s.widget.TextWidget;
import org.sgx.j2s.widget.Widget;
import org.sgx.j2s.yui.YuiDom;
import org.sgx.j2s.yui.YuiElement;

public class TextWidgetImpl extends WidgetImpl implements TextWidget{

	public TextWidgetImpl(String id) {
		super(id);
	}

	Font font;
	String text;
	
	public Font getFont() {		
		return font;
	}

	public String getText() {
		return text;
	}

	public void setFont(Font font) {
		this.font=font;
		Object el = getHTMLEl();
		YuiDom.setStyle(el, YuiDom.Sty_FontFamily, font.getFontFamily());
		YuiDom.setStyle(el, YuiDom.Sty_FontSize, font.getSize()+"px");
//		YuiDom.setStyle(el, YuiDom.Sty_FontFamily, font.getFontFamily());
		//TODO: demas props
	}

	public void setText(String s) {
		this.text=s;
		YuiElement ye =  getYUIEl();
		ye.set(YuiElement.ATTR_TEXT_CONTENT, s);
	}

}
