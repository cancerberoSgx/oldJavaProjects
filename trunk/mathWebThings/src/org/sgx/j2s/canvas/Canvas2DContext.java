package org.sgx.j2s.canvas;
/**
 * wrapper class!
 * 
 * @author SGURIN
 *
 */
public abstract class Canvas2DContext {
public String fillStyle, strokeStyle;

public abstract void clearRect(int x, int y, int w, int h);
public abstract void fillRect(int x, int y, int w, int h);
public abstract void strokeRect(int x, int y, int w, int h);



public abstract void beginPath( );
public abstract void closePath( );
public abstract void stroke( );
public abstract void moveTo(int x, int y);
public abstract void lineTo(int x, int y);
public abstract void quadraticCurveTo(float cpx,float cpy,float x,float y);
public abstract void bezierCurveTo(float cp1x,float cp1y,float cp2x,float cp2y,float x,float y);
public abstract void arcTo(float x1,float y1,float x2,float y2,float radius);
public abstract void rect(float x,float y,float w,float h);
public abstract void arc(float x,float y,float radius,float startAngle,float endAngle,boolean anticlockwise);
public abstract void fill();
public abstract void clip();
public abstract boolean isPointInPath(float x,float y);


// state
public abstract void save(); // push state on state stack
public abstract void restore(); // pop state stack and restore state

//transformations (default transform is the identity matrix)
public abstract void scale(float x,  float y);
public abstract void rotate(float angle);
public abstract void translate( float x,  float y);
public abstract void transform( float m11,float m12,float m21,float m22,
		float dx,float dy);
public abstract void setTransform(float m11,float m12,float m21,float m22,
		float dx,float dy);

/** (default 1.0)*/
public float globalAlpha; 
/**(default source-over)*/
public String globalCompositeOperation; 


public abstract void createLinearGradient(float x0,float y0,float x1,float y1);
public abstract void createRadialGradient(float x0,float y0,float r0,float x1,
		float y1,float r1);
/**
 * @param image an html image element
 * @param repetition a css background repeat value
 */
public abstract void createPattern(Object image,String repetition);

/**(default 1)*/
public float lineWidth;
/**"butt", "round", "square" (default "butt")*/
public String lineCap; 
/**"round", "bevel", "miter" (default "miter")*/
public String lineJoin; 
/**(default 10)*/
public float miterLimit; 

public float shadowOffsetX; // (default 0)
public float shadowOffsetY; // (default 0)
public float shadowBlur; // (default 0)
public String shadowColor; // (default transparent black)



//text
public String font; // (default 10px sans-serif)
public String textAlign; // "start", "end", "left", "right", "center" (default: "start")
public String textBaseline; // "top", "hanging", "middle", "alphabetic", "ideographic", "bottom" (default: "alphabetic")
public abstract void fillText(String text,float x,float y);
public abstract void fillText(String text,float x,float y,float maxWidth);
public abstract void strokeText(String text,float x,float y);
public abstract void strokeText(String text,float x,float y,float maxWidth);
public abstract TextMetrics measureText(String text);

// drawing images
public abstract void drawImage(Object image,float dx,float dy);
public abstract void drawImage(Object image,float dx,float dy,float dw,float dh);
public abstract void drawImage(Object image,float sx,float sy,float sw,float sh,
		float dx,float dy,float dw,float dh);

//pixel manipulation
public abstract ImageData createImageData(float sw, float sh);
public abstract ImageData getImageData(float sx, float sy, float sw, float sh);
public abstract void putImageData(ImageData imagedata, float dx, float dy);
public abstract void putImageData(ImageData imagedata, float dx, float dy, 
		float dirtyX, float dirtyY, float dirtyWidth, float dirtyHeight);

}

