package org.sgx.pickturemakeup.transformations.morphologic;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;

import org.sgx.utils.ImageUtils;



/**
 *  * @author sgx
 *
 */
public class DilationTrans extends MorphologicTransformation {

	/*public static final int BAND_RED=1;
	public static final int BAND_GREEN=2;
	public static final int BAND_BLUE=3;
	public static final int BAND_ALPHA=4;
	int band=BAND_RED;*/
	Color objective=new Color(0,0,0);
	/** dilation or erosion ? */
	boolean dilation=false;
	int size=2;
	
	public DilationTrans() {
		structElem=getFlatStructElem(size);
		structElemCenter=new Point(size/2,size/2);
	}
	float[][] getFlatStructElem(int size) {
		float[][] se = new float[size][size];
		for(int i=0; i<se[0].length;i++) {
			for(int j = 0; j<se.length;j++) {
				se[i][j]=1.0f;
			}
		}
		return se;
	}
	public int dist(Color c1, Color c2) {
		int dist= (c1.getRed()-c2.getRed())*(c1.getRed()-c2.getRed())+
			(c1.getGreen()-c2.getGreen())*(c1.getGreen()-c2.getGreen())+
			(c1.getBlue()-c2.getBlue())*(c1.getBlue()-c2.getBlue())+
			(c1.getAlpha()-c2.getAlpha())*(c1.getAlpha()-c2.getAlpha());
		return dist;
	}
	@Override
	public void applyStructElem(BufferedImage src, BufferedImage dest, int x_, int y_) {
		int W = structElem[0].length, H=structElem.length, x, y;
		float minDist=Float.MAX_VALUE, dist=minDist;
		Color minColor=objective;
		for(int i=0; i<W;i++) {
			for(int j = 0; j<H;j++) {
				x=i-structElemCenter.x;
				y=j-structElemCenter.y;
				if(validCoords(x_+x,y_+y, src.getWidth(), src.getHeight())){
					Color c = ImageUtils.buildColor(src.getRGB(x_+x,y_+y));					
					dist = dist(c,objective);
					if(dist==0)
						dist=0.0001f;
					if(!dilation)
						dist=1/dist;
					if(dist<minDist) {
						minDist=dist;
						minColor=c;
					}
				}
			}
		}
		if(validCoords(x_,y_, src.getWidth(), src.getHeight()))
			//TODO: usar getColor between origColor and minColor con el factor del elemento de structElem en lugar demultiplyColorBy 
			dest.setRGB(x_,y_, ImageUtils.buildColor(minColor));
	}

	static Color multiplyColorBy(Color c , double x ) {
		return new Color(((int)(c.getRed()*x)),((int)( c.getGreen()*x)), 
				((int)(c.getBlue()*x)), ((int)(c.getAlpha()*x)));
	}
	static boolean validCoords(int x, int y, int w, int h) {
		return x<w&&x>=0&&y>=0&&y<h;
	}
	@Override
	public String shortDescription() {
		return "dilation";
	}

}
