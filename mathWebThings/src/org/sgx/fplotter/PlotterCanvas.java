package org.sgx.fplotter;

import java.util.Vector;

import org.sgx.j2s.bitmap.Bitmap;
import org.sgx.j2s.bitmap.BitmapFactory;
import org.sgx.j2s.bitmap.html.MyBitmap;
import org.sgx.j2s.widget.base.Color;
import org.sgx.math.parser.Function;
import org.sgx.math.parser.Variable;
/**
 * the plotting responsability
 * @author SGURIN
 *
 */
public class PlotterCanvas {

	/** canvas dimensions */
	int canvasX,canvasY,canvasW,canvasH;
	
	/**
	 * plotting limits
	 */
	double x_a=-5.0, x_b=5.0, y_a=-5.0, y_b=5.0;
	
	Bitmap bitmap;
	Color axisColor = new Color(111,111,111), backgroundColor=Color.WHITE;
	
	public PlotterCanvas(int x, int y, int w, int h, double x_a, double x_b,
			double y_a, double y_b) {
		super();
		if(x_a>=x_b || y_a>=y_b)
			throw new RuntimeException("invalid plotting limmits");
		this.canvasX = x;
		this.canvasY = y;
		this.canvasW = w;
		this.canvasH = h;
		this.x_a = x_a;
		this.x_b = x_b;
		this.y_a = y_a;
		this.y_b = y_b;
		init();
	}
	
	public PlotterCanvas(int x, int y, int w, int h) {
		this(x,y,w,h, -5.0, 5.0, -5.0, 5.0);
	}
	private void init() {
		bitmap = BitmapFactory.getDefault(canvasX,canvasY,canvasW,canvasH);
		clean();
		drawAxis();
	}

	public void clean() {
		bitmap.erase(backgroundColor);
		drawAxis();
	}

	private void drawAxis() {
		bitmap.line(canvasW/2, 0, canvasW/2, canvasH-1, axisColor);
		bitmap.line(0, canvasH/2, canvasW-1, canvasH/2, axisColor);
	}

	double x_step = (x_b-x_a)/100;
	public void drawFunction(Function f, Color c) {
		double oldXCanvas=0, oldYCanvas=0,xCanvas=0,yCanvas=0;
		for (double x = x_a; x < x_b; x+=x_step) {
			double y = calculate(f, x);
			oldXCanvas=xCanvas;oldYCanvas=yCanvas;
			 xCanvas = toXCanvas(x);
			 yCanvas = toYCanvas(y);			
//			bitmap.paint((int)xCanvas, (int)yCanvas, c);
			if(x > x_a) {
				bitmap.line((int)xCanvas, (int)yCanvas, (int)oldXCanvas, (int)oldYCanvas, c);
			}
		}
		bitmap.flush();
	}

	private double calculate(Function f, double x) {
		Variable v1 = new Variable('x');
		 v1.setValue(x);		 
		Vector<Variable> vars = new Vector<Variable>();
		vars.add(v1);
		return f.calculate(vars);
	}

	private double toYCanvas(double y) {
		double ySize = y_b-y_a;
		return (-1) * ((y*canvasH)/ySize + (y_a*canvasH)/ySize);	
	}

	private double toXCanvas(double x) {
		double xSize = x_b-x_a;
		return (x*canvasW)/xSize - (x_a*canvasW)/xSize;	//correctly scaled	
	
	}

	public Color getAxisColor() {
		return axisColor;
	}

	public void setAxisColor(Color axisColor) {
		this.axisColor = axisColor;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public int getCanvasX() {
		return canvasX;
	}

	public int getCanvasY() {
		return canvasY;
	}

	public int getCanvasW() {
		return canvasW;
	}

	public int getCanvasH() {
		return canvasH;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}
}
