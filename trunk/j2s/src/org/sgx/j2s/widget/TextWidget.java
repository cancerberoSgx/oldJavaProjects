package org.sgx.j2s.widget;

import org.sgx.j2s.model.base.Font;

public interface TextWidget extends Widget {
	
	public String getText();
	public void setText(String s);
	
	public Font getFont();
	public void setFont(Font font);
}
