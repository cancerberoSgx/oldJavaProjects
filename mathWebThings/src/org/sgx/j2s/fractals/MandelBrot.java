package org.sgx.j2s.fractals;

import org.sgx.j2s.math.complex.Complex;

/**
 * 
 * @author sgurin
 *
 */
public class MandelBrot {
 double N = 10.0;
 int M = 10;
//Complex c = new Complex(-0.5, 1);
//public MandelBrot(Complex c) {
//	super();
//	this.c = c;
//}

/**
 * @see doMandel()
 * @param n amount
 * @param m amounts of times to iterate. note that the points inside mandel will have to iterate this amount.
 * @param c
 */
public MandelBrot(double n, int m, Complex c) {
	super();
	N = n;
	M = m;
//	this.c = c;
}
public MandelBrot() {
	// TODO Auto-generated constructor stub
}
/**
 * this is the mandel function z_n=z_{n-1}+c but also take the responsability if where to stop the iteration (resolve the "distance" of c from mandel)
 * @return 0 if z is in mandel or a positive integer representing the "attraction magnitude"
 * (greater means more attracted (near) to mandel .  close to 0 is very far from mandel)
 * 
 */
public int doMandel(Complex c) {
	Complex z = new Complex(0,0);

	boolean wentFar=false, wentNear=false;
	double zNorm , zNormOld=-1;
	boolean goingFar=false;
	
	for (int i = 0; i < M; i++) {
		
		z=(z.mul(z)).add(c);	
		
		zNorm = z.norm();
//		System.out.println("iteration: "+i+", z: "+z+", norm: "+zNorm+", "+goingFar);
		if(zNormOld!=-1) {
			goingFar = (zNorm - zNormOld)>0 ;			
			if(zNorm>N && goingFar) { //go out of mandel to inf
				return i;
			}
		}
		
		if(i>2  ) { //mandel belonging detection
			if(goingFar){
				wentFar=true;
			}
			else {
				wentNear=true;
			}			
			if(wentFar && wentNear){
				return 0;
			}
		}
		zNormOld=zNorm;
		
	}
	//we haven'f left the Mandel M contorno. mmm very strange... 2 things could happen: we are in mandel but always
	return 0;
}



public static void test1() {
	MandelBrot f  = new MandelBrot();
	
	Complex c = new Complex(-0.5,1.0);
	int i = f.doMandel(c);
	System.out.println("doMandel : "+c+" = "+i);
	
//	System.out.println("\n\n");
//	c = new Complex(.03,.03);
//	i = f.doMandel(c);
//	System.out.println("doMandel : "+c+" = "+i);
	
	
}	



public static void main(String[] args) {
	 test1() ;
}
}
