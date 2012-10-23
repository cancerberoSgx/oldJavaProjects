package org.sgx.j2s.fractals;

import org.sgx.j2s.bitmap.Bitmap;
import org.sgx.j2s.math.complex.Complex;

/**
 For each pixel on the screen do:
{
  x = x0 = x co-ordinate of pixel
  y = y0 = y co-ordinate of pixel

  iteration = 0
  max_iteration = 1000
 
  while ( x*x + y*y <= (2*2)  AND  iteration < max_iteration ) 
  {
    xtemp = x*x - y*y + x0
    y = 2*x*y + y0

    x = xtemp

    iteration = iteration + 1
  }
 
  if ( iteration == max_iteration ) 
  then 
    color = black
  else 
    color = iteration

  plot(x0,y0,color)
}

 * @author nati
 *
 */
public class MandelBrot2__ {
int max_iteration=20;
	Bitmap b;

	
	Complex z0 ;
	
	public int doMandel(Complex c) {
		int iteration = 0;
		Complex z = z0;
		  while ( (z.norm()<=4)  &&  iteration < max_iteration ) 		  {
			  z=(z.mul(z)).add(c);	
		    iteration++;
		  }		 
		  if ( iteration == max_iteration )
			  return 0;
		  else 
		    return iteration;
	}
	public static void main(String[] args) {
		
	}
	public MandelBrot2__(Complex z0) {
		super();
		this.z0 = z0;
	}

}
