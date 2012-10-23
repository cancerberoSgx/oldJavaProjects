package org.sgx.j2s.yui;
/**
 *  static  Class YAHOO.env.ua
Do not fork for a browser if it can be avoided. Use feature detection when you can. Use the user agent as a last resort. YAHOO.env.ua stores a version number for the browser engine, 0 otherwise. This value may or may not map to the version number of the browser using the engine. The value is presented as a float so that it can easily be used for boolean evaluation as well as for looking for a particular range of versions. Because of this, some of the granularity of the version info may be lost (e.g., Gecko 1.8.0.9 reports 1.8).
 * @author SGURIN
 *
 */
public class YuiUa {
	
	 
	/**Gecko engine revision number. Will evaluate to 1 if Gecko is detected but the revision could not be found. Other browsers will be 0. Example: 1.8

Firefox 1.0.0.4: 1.7.8   <-- Reports 1.7
Firefox 1.5.0.9: 1.8.0.9 <-- Reports 1.8
Firefox 2.0.0.3: 1.8.1.3 <-- Reports 1.8
Firefox 3 alpha: 1.9a4   <-- Reports 1.9

	 * @j2sNative
	 * return YAHOO.env.ua.gecko;
	 */
	public static float geckoVersion() {return 0.0f;}
	/**Internet Explorer version number or 0. Example: 6
	 * @j2sNative
	 * return YAHOO.env.ua.gecko;
	 */
	public static float ieVersion() {return 0.0f;}
	/**The mobile property will be set to a string containing any relevant user agent information when a modern mobile browser is detected. Currently limited to Safari on the iPhone/iPod Touch, Nokia N-series devices with the WebKit-based browser, and Opera Mini.
	 * @j2sNative
	 * return YAHOO.env.ua.gecko;
	 */
	public static String mobileVersion() {return "";}
	/**Opera version number or 0. Example: 9.2	
	 * @j2sNative
	 * return YAHOO.env.ua.gecko;
	 */
	public static float operaVersion() {return 0.0f;}
	/**
	 * AppleWebKit version. KHTML browsers that are not WebKit browsers will evaluate to 1, other browsers 0. Example: 418.9.1

Safari 1.3.2 (312.6): 312.8.1 <-- Reports 312.8 -- currently the 
latest available for Mac OSX 10.3.
Safari 2.0.2:         416     <-- hasOwnProperty introduced
Safari 2.0.4:         418     <-- preventDefault fixed
Safari 2.0.4 (419.3): 418.9.1 <-- One version of Safari may run
different versions of webkit
Safari 2.0.4 (419.3): 419     <-- Tiger installations that have been
updated, but not updated
to the latest patch.
Webkit 212 nightly:   522+    <-- Safari 3.0 precursor (with native SVG
and many major issues fixed).
Safari 3.0.4 (523.12):523.12  <-- First Tiger release - automatic update
from 2.x via the 10.4.11 OS patch
Webkit nightly 1/2008:525+    <-- Supports DOMContentLoaded event

http://developer.apple.com/internet/safari/uamatrix.html

	 * @j2sNative
	 * return YAHOO.env.ua.gecko;
	 */
	public static float webkitVersion() {return 0.0f;}
}
