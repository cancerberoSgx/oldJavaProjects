package org.sgx.j2s.raphael.test;

import org.sgx.j2s.utils.Utils;

public class NativeTest1 {

	public static void main(String[] args) {
		Utils.getInstance().loadScript("raphael.js");
		/**
		 * @j2sNative
// Creates canvas 320 × 200 at 10, 50
var paper = Raphael(10, 50, 320, 200);
// Creates circle at x = 50, y = 40, with radius 10
var circle = paper.circle(50, 40, 10);
// Sets the fill attribute of the circle to red (#f00)
circle.attr("fill", "#f00");
// Sets the stroke attribute of the circle to white (#fff)
circle.attr("stroke", "rgb(2,2,2)");
		 */{}
	}

}
