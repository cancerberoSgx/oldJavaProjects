package org.sgx.j2s.myDrawings;

import org.sgx.j2s.raphael.Paper;

public class DrawerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Paper p = new Paper(1,1,200,200);
		Drawer drawer = new Drawer(p);
		
		drawer.drawPoint(new Point(20,20));
	}

}
