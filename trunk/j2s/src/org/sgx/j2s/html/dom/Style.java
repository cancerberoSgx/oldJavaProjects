package org.sgx.j2s.html.dom;
/**
 * direct wrapper between java and html dom objects
 * property defined here should be a subset of w3c dom
 * supported by all browsers
 * @author sgurin
 *
 */
public class Style {

	public String backgroundColor;
	public String position;
	public String width;
	public String height;
	public String top;
	public String left;

	public static Style buildFrom(String css) {
		String [][] prop = new String[][]{
			{"backgroundColor","background-color"}
		};
		String[] s = css.split(";");
		return null;
	}
}
