package org.sgx.pickturemakeup.transformations.morphologic;

import java.awt.image.BufferedImage;
/**
 * opening / close image topologic transformations
 * @author sgx
 *
 */
public class OpenCloseTrans extends DilationTrans{
	/** opening or closing operation ? */
	boolean opening=true;

	@Override
	public void applyStructElem(BufferedImage src, BufferedImage dest, int x_, int y_) {
		if(opening) {
			this.dilation=false;
			super.applyStructElem(src, dest, x_, y_);
			this.dilation=true;
			super.applyStructElem(src, dest, x_, y_);
		}
		else {
			this.dilation=true;
			super.applyStructElem(src, dest, x_, y_);
			this.dilation=false;
			super.applyStructElem(src, dest, x_, y_);
		}		
		
	}
	
}
