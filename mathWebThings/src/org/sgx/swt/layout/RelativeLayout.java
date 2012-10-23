package org.sgx.swt.layout;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;
/**
 * 
 * @author SGURIN
 *
 */
public class RelativeLayout extends Layout {

	private Composite comp;
	int childCount;
	Map<Integer, RealRectangle> childBounds;
	Rectangle parentBounds;
	
	public RelativeLayout(Composite composite) {
		comp=composite;
		childBounds=new HashMap<Integer, RealRectangle>();
		updateDims(composite);
	}
	private void updateDims(Composite composite) {
		parentBounds = composite.getBounds();
		childCount=composite.getChildren().length;
		childBounds.clear();
		Control[] childs = composite.getChildren();
		for (int i = 0; i < childs.length; i++) {
			RealRectangle b=divide(childs[i].getBounds(),parentBounds);
			childBounds.put(i, b);
		}
	}
	
	private RealRectangle divide(Rectangle r1, Rectangle r2) {
		return new RealRectangle (r2.width==0?0:((float)r1.x)/((float)r2.width), r2.height==0?0:((float)r1.y)/((float)r2.height), 
				r2.width==0?0:((float)r1.width)/((float)r2.width), r2.height==0?0:((float)r1.height)/((float)r2.height));
	}

	private Rectangle multiply(Rectangle r1, RealRectangle realRectangle) {
		return new Rectangle ((int) (realRectangle.width==0?0:r1.width*realRectangle.x), (int)(realRectangle.height==0?0:r1.height*realRectangle.y), 
				(int) (realRectangle.width==0?0:r1.width*realRectangle.width), (int)(realRectangle.height==0?0:r1.height*realRectangle.height));
	}
	private Rectangle resolve(Rectangle r1, RealRectangle realRectangle) {
		/* los primas (') son los originales:
		r2 = divide ( childrenBounds', parentBounds')
		r1 = parentBounds
		resolve(r1,r2)== childrenBounds. by "rule of three":
		pb' -- cb'
		pb  -- x		
		x=pb*cb' / pb' == multiply(r1, divide(cb', pb')) == multiply(r1, r2);
		*/
		return multiply(r1, realRectangle);
		
	}
	
	@Override
	protected Point computeSize(Composite composite, int w, int h,
			boolean flushCache) {
		return composite.getSize();
	}

	@Override
	protected void layout(Composite composite, boolean flushCache) {
		Control[] childs = composite.getChildren();	
		if(!parentBounds.equals(composite.getBounds())) {
			for (int i = 0; i < childs.length; i++) {
				Control c = childs[i];
				Rectangle b = resolve(composite.getBounds(), childBounds.get(i));
				c.setBounds(b);
			}
		}
	}
}
