package org.sgx.pickturemakeup.transformations.pixel;

import java.awt.Color;
import java.awt.Image;
import java.util.Date;
import java.util.Random;

import org.sgx.pickturemakeup.model.NumericProperty;
import org.sgx.pickturemakeup.model.Property;


/**
 * Una DitherTransformation es una funci�n (pixel, image)->color tal que:
 * 
 * a cada pixel p de la imagen de origen img se le aplica la funci�n
 * 'getDitherTransformation(p,img)' con cierta probabilidad definida en el metodo
 * getDitherProbability(p,img) directamente proporcional 
 * al factor 'pass' tal que si pass==1 todos los pixeles son filtrados
 * y si pass==0 ning�n pixel es filtrado.   
 * 
 * Si getDitherProbability(p,img)<'pass' entonces se aplica getDitherTransformation() 
 * al pixel. getDitherTransformation debe cumplir, an�logamente que getDitherProbability
 * con que la transformaci�n debe ser directamente proporcional al factor 'level' y 
 * utilizando como el "opuesto" al pixel definido en el metodo getDitherObjective(p,img). 
 * En otras palabras,getDitherObjective define a qu� se van a parecer los puntos con error.
 * El mecanismo de interpolaci�n usado puede definirse en interpolateColor() aunque ya se 
 * provee con una implementaci�n linear. 
 * 
 *  ideas: 
 *  - utilizar getDitherProbabilityen que sean curvas en el plano x,y de la imagen 
 *  para definir variaciones de brillo o profundidad. 
 *  - poder definir estas variaciones de brillo no con una probabilidad mediante una imagen
 *  en grises (mas blanco mayor pass). luego el usuario podr� definir la frecuencia del 
 *  ditherin mediante sus propias imagenes/patrones
 *   
 * 
 *  as� como la funci�n getDitherProbability dice con qu� frecuencia 
 * ocurrir� la transformaci�n pixel a pixel, 
 * 
 *  aplica o no seg�n un valor con cierta probabilidad 
 * pass de
 * 
 * tanto getDitherTransformation. 
 * ser elegido y modelando dicha funci�n de proabilidad con la funci�n 
 * getProbPass(pixel, image). En caso de ser elegido, a�n se podr� modelar la
 * probabilidad de que cambie m�s o menos hacia cierto
 * 
 *
 * 
 * @author sgx
 *
 */
public abstract class DitherTransformation extends PixelTransformation {

	public static final float levelMin = 0.0f;
	public static final float levelMax = 1.0f;
	public static final float levelDefault = 0.1f;
	public static final float passMin = 0.0f;
	public static final float passMax = 1.0f;
	public static final float passDefault = 0.5f;
	
	//properties:
	public static final String 
		LEVELALPHA_PROP_ID = "levelAlpha",
		LEVELRED_PROP_ID = "levelRed",
		LEVELGREEN_PROP_ID = "levelGreen",
		LEVELBLUE_PROP_ID = "levelBlue",	
		PASSALPHA_PROP_ID = "passAlpha", 
		PASSRED_PROP_ID = "passRed",
		PASSGREEN_PROP_ID = "passGreen",
		PASSBLUE_PROP_ID = "passBlue";
	static {
		DitherTransformation.props= new Property[] {
			new NumericProperty(LEVELRED_PROP_ID, levelDefault, levelMax, levelMin), 
			new NumericProperty(LEVELGREEN_PROP_ID, levelDefault, levelMax, levelMin),
			new NumericProperty(LEVELBLUE_PROP_ID, levelDefault, levelMax, levelMin),
			new NumericProperty(LEVELALPHA_PROP_ID, levelDefault, levelMax, levelMin),
			new NumericProperty(PASSRED_PROP_ID, passDefault, passMax, passMin),
			new NumericProperty(PASSGREEN_PROP_ID, passDefault, passMax, passMin),
			new NumericProperty(PASSBLUE_PROP_ID, passDefault, passMax, passMin),
			new NumericProperty(PASSALPHA_PROP_ID, passDefault, passMax, passMin)
		};
	}
	
	
	/**level de afectaci�n a acada color RGBA. ambos var�asn entre 0.0 y 1.0*/
	float levelAlpha=levelDefault,  levelRed=levelDefault, levelGreen=levelDefault, levelBlue=levelDefault;
	float passAlpha=passDefault, passRed=passDefault, passGreen=passDefault, passBlue=passDefault;
	
	//TODO:; agregar channel dependence and y or
	boolean independentChannels=false;
	
	public DitherTransformation(float alpha, float blue, float green, float red, float alpha2, float blue2, float green2, float red2) {
		super();
		// TODO Auto-generated constructor stub
		levelAlpha = alpha;
		levelBlue = blue;
		levelGreen = green;
		levelRed = red;
		passAlpha = alpha2;
		passBlue = blue2;
		passGreen = green2;
		passRed = red2;
		random=new Random(new Date().getTime());
	}
	public DitherTransformation() {
		this(levelDefault,levelDefault,levelDefault,levelDefault,passDefault,passDefault,passDefault,passDefault);
	}
	@Override
	/**
	 * hacer que el random radio incluya en el radioXXX
	 */
	public Color getPixelTransf(Pixel p, Image img) {	
		//TODO:; agregar channel dependence and y or
		if(independentChannels) {
			int r=p.c.getRed(), g=p.c.getGreen(), b=p.c.getBlue(), a=p.c.getAlpha();		
			Color out = interpolate(p,img);
			float [] pass=getDitherProbability(p,img);
			if(pass[0]<passRed) 		
				r=out.getRed();//		in.setRed(out.getRed());
			if(pass[1]<passGreen) 		
				g=out.getGreen();//transPosta(g,levelGreen);
			if(pass[2]<passBlue) 		
				b=out.getBlue();//b=transPosta(b,levelBlue);
			if(pass[3]<passAlpha) 		
				a=out.getAlpha();
			return new Color(r,g,b,255);
		}
		else { //se tomar� solo el de red:
			//TODO:; agregar channel dependence and y or
			int r=p.c.getRed(), g=p.c.getGreen(), b=p.c.getBlue(), a=p.c.getAlpha();		
			Color out = interpolate(p,img);
			float [] pass=getDitherProbability(p,img);
			if(pass[0]<passRed) 		
				return out;
			else return p.c;
		}
	}
	
	/**
	 * llamada desde interpolate. ver los comments de interpolate
	 * @param p
	 * @param img
	 * @return
	 */
	public abstract Color getDitherOposite(Pixel p, Image img);
	/**
	 * define el nivel de interpolacion proporcional al valor de this.level. Es
	 * decir, cuanto mayor sea level m�s parecido ser� la salida a getDitherOposite
	 * y cuanto mas peque�o mas parecido al pixel fuente. level var�a entre [0,1]. 
	 * Aqu� se define una interpolaci�n lineal por defecto pero las subclases pueden 
	 * definir las propias suyas. dado que level es en realidad un vector para cada 
	 * componente rgba interpolate debera trabajar con cada banda independientemente.
	 * 
	 * @return
	 */
	public Color interpolate(Pixel p, Image img) {
		Color src=p.c, dest = getDitherOposite(p,img);
		int deltaRed = (int)(levelRed*((float)dist(src.getRed(),dest.getRed()))),
			deltaGreen = (int)(levelGreen*((float)dist(src.getGreen(),dest.getGreen()))),
			deltaBlue = (int)(levelBlue*((float)dist(src.getBlue(),dest.getBlue()))), 
			deltaAlpha = (int)(levelAlpha*((float)dist(src.getAlpha(),dest.getAlpha()))), 
			r=p.c.getRed(), g=p.c.getGreen(), b=p.c.getBlue(), a=p.c.getAlpha(); 
;
		return new Color(
				sumColorBand(r,dest.getRed(),deltaRed), 
				sumColorBand(g,dest.getGreen(),deltaGreen), 
				sumColorBand(b,dest.getBlue(),deltaBlue),
				sumColorBand(a,dest.getAlpha(),deltaAlpha));
	}
	/**
	 * @return le suma x a 'a' para que se aproxime a b
	 */
	int sumColorBand(int a, int b, int x) {
		if(a>b)
			return a-x;
		else 
			return a+x;
	}
	
	
	/** generador de num aleats para el radio y pasage	  de riodo*/
	Random random;
	/**
	 * define el metodo de probabilidad proporcional al valor de this.pass. 
	 * Se define aqu� por defecto una distribuci�n uniforme proporcioanl a pass
	 * @return un array de 4 elementos  con los passings para cada canal rgba, 
	 * cada uno proporcional al valor passXXX
	 */	
	public float[] getDitherProbability(Pixel p,Image img) {				
		float []r =new float[4]; 
		for(int i=0; i<4; i++)
			r[i]=random.nextFloat();
		return r;
	}
	
	
	int transMult(int x, float level) {
		float mult = 1.0f+levelAlpha-0.5f;
		int newX = ((int)(((float)x)*mult));	
		if(x>=0&&x<=255)
			return newX;
		else return x; 
	}
	
	int dist(int x,int y) {
		if(x<y) return y-x;
		else return x-y;
	}
	
	int transInvNoLevel(int x, float level) {
		return 255-x;
	}
	int transInv(int x, float level) {
		int inv = 255-x, delta = (int)(level*((float)dist(x,inv))), ret;
		if(x>inv)
			ret=x-delta;
		else 
			ret=x+delta;
		
//		if(random.nextFloat()>0.97)
//		System.out.println("x = "+x+" inv = "+inv+ "--- pr "+passRed+" pg "+passGreen+" pb "+passRed+" delta="+delta+" result="+ret);
		/*
		 * 1 - dist(x,inv)
		 * level - X
		 * ==> */
		return ret;
	}
	

//	private int trans(int r, float levelAlpha2) {
//		return transInv(r, levelAlpha2);
//	}

//	// This method is called for every pixel in the image
//	public int filterRGB(int x, int y, int rgb) {
//		return buildColor(getDitherRandom(buildColor(rgb)));
//	}
	
	public float getLevelAlpha() {
		return levelAlpha;
	}
	public void setLevelAlpha(Object v) {
		setLevelAlpha(castValueToFloat(v));
	}
	public void setLevelAlpha(float levelAlpha) {
		this.levelAlpha = levelAlpha;
	}
	
	public float getLevelBlue() {
		return levelBlue;
	}
	public void setLevelBlue(Object v) {
		setLevelBlue(castValueToFloat(v));
	}
	public void setLevelBlue(float levelBlue) {
		this.levelBlue = levelBlue;
	}
	
	public float getLevelGreen() {
		return levelGreen;
	}
	public void setLevelGreen(Object v) {
		setLevelGreen(castValueToFloat(v));
	}
	public void setLevelGreen(float levelGreen) {
		this.levelGreen = levelGreen;
	}
	
	public float getLevelRed() {
		return levelRed;
	}
	public void setLevelRed(Object v) {
		setLevelRed(castValueToFloat(v));
	}
	public void setLevelRed(float levelRed) {
		this.levelRed = levelRed;
	}
	
	public float getPassAlpha() {
		return passAlpha;
	}
	public void setPassAlpha(Object v) {
		setPassAlpha(castValueToFloat(v));
	}
	public void setPassAlpha(float passAlpha) {
		this.passAlpha = passAlpha;
	}
	
	public float getPassBlue() {
		return passBlue;
	}
	public void setPassBlue(Object v) {
		setPassBlue(castValueToFloat(v));
	}
	public void setPassBlue(float passBlue) {
		this.passBlue = passBlue;
	}
	
	public float getPassGreen() {
		return passGreen;
	}
	public void setPassGreen(Object v) {
		setPassGreen(castValueToFloat(v));
	}
	public void setPassGreen(float passGreen) {
		this.passGreen = passGreen;
	}
	
	public float getPassRed() {
		return passRed;
	}
	public void setPassRed(Object v) {
		setPassRed(castValueToFloat(v));
	}
	public void setPassRed(float passRed) {
		this.passRed = passRed;
	}
	
	@Override
	public abstract String shortDescription();
	
		
}