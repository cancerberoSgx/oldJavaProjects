package org.sgx.j2s.canvas;

import org.sgx.j2s.bitmap.DomUtils;
import org.sgx.j2s.utils.Utils;

public class CanvasTest {

	public static void main(String[] args) {

		Utils.getInstance().loadScript("excanvas.js");
		
		System.out.println("0");
		Canvas canvas = Canvas.create(50,50,200,200);
		System.out.println(1);
		Canvas2DContext ctx = canvas.getContext();
		ctx.fillStyle = "green";
		ctx.fillRect(5, 5, 25, 25);
		ctx.strokeStyle = "red";
		ctx.strokeRect(20, 20, 25, 25);

//		ctx.beginPath();
//		ctx.lineWidth = 1;
//		ctx.moveTo(1,1);
//		ctx.lineTo(80,80);
//		ctx.lineTo(100,20);
//		ctx.closePath();
//		ctx.stroke();
//
//		ctx.strokeStyle = "blue";
//		ctx.fillStyle = "black";
//
//		ctx.beginPath();
//		ctx.moveTo(120,50);
//		ctx.lineTo(150,70);
//		ctx.lineTo(150,50);
//
//		ctx.quadraticCurveTo(150, 150, 80, 80);
//		ctx.bezierCurveTo(85,25,75,37,75,40);
//		ctx.closePath();
//		ctx.fill();
		
	}

}
