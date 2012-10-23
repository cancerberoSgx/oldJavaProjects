package org.sgx.j2s.widget.layout;

import org.sgx.j2s.widget.Widget;

public abstract class AbstractLayout implements Layout{
	public static  int defaultBottomPadding = 0;
	public static  int defaultRightPadding = 0;
	public static  int defaultLeftPadding = 0;
	public static  int defaultTopPadding = 0;

Widget host;
int topPadding, leftPadding, rightPadding, bottomPadding;

public AbstractLayout(int topPadding, int leftPadding,
		int rightPadding, int bottomPadding) {
	super();
//	this.host = host;
	this.topPadding = topPadding;
	this.leftPadding = leftPadding;
	this.rightPadding = rightPadding;
	this.bottomPadding = bottomPadding;
}

public AbstractLayout() {
	this( defaultTopPadding, defaultLeftPadding, defaultRightPadding, defaultBottomPadding);
//	this.host = host;
}

public Widget getHost() {
	return host;
}

public int getTopPadding() {
	return topPadding;
}

public void setTopPadding(int topPadding) {
	this.topPadding = topPadding;
}

public int getLeftPadding() {
	return leftPadding;
}

public void setLeftPadding(int leftPadding) {
	this.leftPadding = leftPadding;
}

public int getRightPadding() {
	return rightPadding;
}

public void setRightPadding(int rightPadding) {
	this.rightPadding = rightPadding;
}

public int getBottomPadding() {
	return bottomPadding;
}

public void setBottomPadding(int bottomPadding) {
	this.bottomPadding = bottomPadding;
}

public void setHost(Widget host) {
	this.host = host;
}
}
