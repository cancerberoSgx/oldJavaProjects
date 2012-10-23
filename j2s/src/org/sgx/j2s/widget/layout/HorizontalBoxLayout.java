package org.sgx.j2s.widget.layout;

import org.sgx.j2s.model.base.Rectangle;
import org.sgx.j2s.util.Iterator;
import org.sgx.j2s.util.List;
import org.sgx.j2s.widget.Widget;

public class HorizontalBoxLayout extends AbstractLayout{
	
	public static  int defaultStep = 5;
	int step;

	public HorizontalBoxLayout(int topPadding, int leftPadding,
			int rightPadding, int bottomPadding, int step) {
		super(topPadding, leftPadding, rightPadding, bottomPadding);
		this.step = step;
	}
	public HorizontalBoxLayout(int step) {
		super();
		this.step = step;
	}
	public HorizontalBoxLayout() {
		this(defaultStep);
	}
	public  int getStep() {		
		return step;
	}
	public void layout() {
		doLayout(this, host);		
	}
	public static void doLayout(HorizontalBoxLayout layout, Widget host) {
		System.out.println("horizontal layouting");
		Rectangle bounds = host.getBounds();
		List<Widget> childs = host.getChildren();
		int x = layout.getLeftPadding();
		int step = layout.getStep();
		int y = layout.getTopPadding(); 
		int w = (bounds.getDimension().width-layout.getLeftPadding()-layout.getRightPadding()-(childs.size()-1)*step)/childs.size();
		int h =  bounds.getDimension().height-layout.getTopPadding()-layout.getBottomPadding();
		Iterator<Widget> i = childs.iterator();
		while(i.hasNext()) {
			Widget c = i.next();
			c.setBounds(new Rectangle(x,y,w,h));
			x+=w+step;
		}
	}
	

}
