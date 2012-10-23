package org.sgx.j2s.fractals;

import org.sgx.j2s.bitmap.Bitmap;

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
public class MandelBrot2 {
int max_iteration=10;
	Bitmap b;
	
	public int doMandel(double x, double y) {
		int iteration = 0;
		 
		  while ( x*x + y*y <= (2*2)  &&  iteration < max_iteration ) 		  {
		    double x0=x, y0=y, xtemp;
			xtemp = x*x - y*y + x0;
		    y = 2*x*y + y0;
		    x = xtemp;
		    iteration++;
		  }		 
		  if ( iteration == max_iteration )
			  return 0;
		  else 
		    return iteration;
	}
	public static void main(String[] args) {
		
	}

}
