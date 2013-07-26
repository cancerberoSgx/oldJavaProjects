package org.sgx.picturemakeup.transformations.colors;

import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.util.LinkedList;
import java.util.List;

import org.sgx.picturemakeup.model.ImageTransformation;
import org.sgx.picturemakeup.model.ListProperty;
import org.sgx.picturemakeup.model.NumericProperty;
import org.sgx.picturemakeup.model.Property;
import org.sgx.picturemakeup.transformations.BlurTransformation;
import org.sgx.picturemakeup.utils.ImageFormat;
import org.sgx.utils.ImageUtils;




/**
 * transformación para el ajuste de brillo
 * @author sgx
 *
 */
public class BrightTransformation extends ImageTransformation {
	
	public static String BAND_RED="red";
	public static String BAND_GREEN="green";
	public static String BAND_BLUE="blue";
	public static String BAND_ALPHA="blue";
	
	public static final float FACTOR_MAX = 5.0f;
	public static final float FACTOR_MIN =0.0f;
	public static final float FACTOR_DEFAULT = 1.0f;
	
	float factor=FACTOR_DEFAULT;
	
	List<String> band=getBandList();
	
	//	properties:
	public static final String FACTOR_PROP_ID = "factor",
		BAND_PROP_ID = "band";
	static {
		props= new Property[] {
				new NumericProperty(FACTOR_PROP_ID, FACTOR_DEFAULT, FACTOR_MAX, FACTOR_MIN),
				new ListProperty(BAND_PROP_ID, "color band to apply trandformation", getBandList())
		};
	};
	static List<String> getBandList() {
		 List<String> l = new LinkedList<String>();
		 l.add(BAND_RED);l.add(BAND_GREEN); l.add(BAND_BLUE); l.add(BAND_ALPHA); 
		 return l;
	}

	public BrightTransformation(float factor) {
		this.factor = factor;
	}
	
	public BrightTransformation() {
		factor=FACTOR_DEFAULT;
	}
	
	public BufferedImage applyTransformation(BufferedImage src) {
		float r = 1.f, g = 1.f, b=1.f;		
		if(band.contains(BAND_RED))
			r=factor;
		if(band.contains(BAND_GREEN))
			g=factor;
		if(band.contains(BAND_BLUE))
			b=factor;		
		System.out.println("transforming: r="+r+" - g="+g+" - b="+b+"    bands: "+band);
		
		return ImageUtils.applyOp(ImageFormat.makeRGB(src), 
				new RescaleOp(new float[] {r,g,b}, new float[]{0,0,0}, null));
	}
	public String shortDescription() {
		return "TODO";
	}

	public void setScaleFactor(float x) {
		this.factor=x;
	}
	
	public void setFactor(Object value) {
		setFactor(castValueToFloat(value));
	}
	public void setFactor(float factor) {
		this.factor = factor;
	}
	
	public void setBand(Object o) {
		if(o instanceof List) {
			this.band=(List)o;
			System.out.println("new band: "+o);
		}
		else {
			System.out.println("not a list "+o.getClass());
			throw new RuntimeException("incorrect type");
		}
	}

}
