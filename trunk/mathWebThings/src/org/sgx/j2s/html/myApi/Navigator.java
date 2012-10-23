package org.sgx.j2s.html.myApi;

/**
 * html dom Navigator object wrapper.
 * 
 * only compatible properties are available
 * 
 * @author SGURIN
 *
 */
public abstract class Navigator {

	/**
	 * appName Value for netscape / firefox
	 */
	public static final String Netscape = "Netscape";
	/**
	 * appName Value for IE
	 */
	public static final String MSIE = "Microsoft Internet Explorer";


	/**
	 * Returns the name of the browser
	 */
	public String appName;
	public String appCodeName;
	public String appVersion;
	public boolean cookieEnabled;
	public String platform;
	public String userAgent;
	/**
	 *  	Specifies whether or not the browser has Java enabled
	 * @return
	 */
	public abstract boolean javaEnabled();
	/**
	 *  	Specifies whether or not the browser has data tainting enabled
	 * @return
	 */
	public abstract boolean  taintEnabled();

}


