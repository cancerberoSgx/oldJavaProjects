package org.sgx.j2s.raphael;

public class Path extends Element{
//	private Object _data;

	public Path(Object _data) {
		super(_data);
//		this._data = _data;
	}

	/**
	 * Sets trigger to count all following units as absolute ones, unless said otherwise. [on by default]
	 * @j2sNative
	 * this._data.absolutely();
	 * return this;
	 */
	public Path absolutely() {return null;}

	/**
	 * Sets trigger to count all following units as relative ones, unless said otherwise.
	 * @j2sNative
	 * this._data.relatively();
	 * return this;
	 */
	public Path relatively () {return null;}
	/**
	 * Redraws the path. Make sense to use when points has been changed. 
	 * This is possible via accessing path  property of the object. 
	 * This property is array of objects representing commands given to path.
	 * 
	 * @j2sNative
	 * this._data.redraw();
	 * return this;
	 */
	public Path redraw () {return null;}
	/**
	 * accessing path  property of the object. This property is array of objects representing commands given to path.
	 * 
	 * @j2sNative
	 * this._data.path[cmdIndex].arg[argIndex]=value;
	 * return this;
	 */
	public void setCommandAttr(int cmdIndex, int argIndex, Object value) {}

	/**
	 * accessing path  property of the object. This property is array of objects representing commands given to path.
	 * 
	 * @j2sNative
	 * this._data.path[cmdIndex].arg[argIndex];
	 * return this;
	 */
	public Object getCommandAttr(int cmdIndex, int argIndex) {return null;}

	/**
	 * Moves drawing point to given coordinates.
	 * 
	 * @j2sNative
	 * this._data.moveTo(x,y);
	 * return this;
	 */
	public Path moveTo(int x, int y) {return null;}
	/**
	 * Draws straight line to given coordinates.
	 * 
	 * @j2sNative
	 * this._data.lineTo(x,y);
	 * return this;
	 */
	public Path lineTo(int x, int y) {return null;}

	/**
	 * Draws curved line to given coordinates. Line will have horizontal anchors on start and on finish.
	 * 
	 * @j2sNative
	 * this._data.cplineTo(x,y);
	 * return this;
	 */
	public Path cplineTo(int x, int y) {return null;}


	/**
	 *Draws curved line to given coordinates. 
	 * 
	 * @j2sNative
	 * this._data.curveTo(x1,y1,x2,y2,x3,y3);
	 * return this;
	 */
	public Path curveTo(int x1, int y1,int x2, int y2,int x3, int y3) {return null;}

	/**
	 * Draws quarter of circle form current point.
	 * Possible dir values
	lu    left up
	ld    left down
	ru    right up
	rd    right down
	ur    up right
	ul    up left
	dr    down right
	dl    down left 
	 * 
	 * @j2sNative
	 * this._data.addRoundedCorner(r,dir);
	 * return this;
	 */
	public Path addRoundedCorner(int r, String dir) {return null;}
	/**
	 * closes the path
	 * @j2sNative
	 * this._data.andClose();
	 * return this;
	 */
	public Path andClose(){return null;}
}