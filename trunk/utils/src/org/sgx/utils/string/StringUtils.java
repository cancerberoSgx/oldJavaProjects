package org.sgx.utils.string;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


/**
 * @author sgurin
 */
public class StringUtils {

	public static String caseTitle(String strInput) {
		String s = changeFirstLetterMayus( strInput,  true);
		return replaceRegexpGrop(s,"(\\s+\\w)",  new StringOperation() {
			public String make(String src, Map<String, Object> config) {
				return src.toUpperCase();
			}			
		});
	}
	public static String caseSentence(String src) {
		String s = changeFirstLetterMayus( src,  true);
		return replaceRegexpGrop(s,"(\\.\\s*\\w)",  new StringOperation() {
			public String make(String src, Map<String, Object> config) {
				return src.toUpperCase();
			}			
		});
	}
	public static String caseInvert(String src) {
		return replaceRegexpGrop(src,"(\\w)",  new StringOperation() {
			public String make(String src, Map<String, Object> config) {
				if(src==null || src.length()==0)
					return "";
				char c = src.charAt(0);
				if(Character.isLowerCase(c)){					
					return ""+Character.toUpperCase(c);
				}
				else if(Character.isUpperCase(c)) {
					return ""+Character.toLowerCase(c);
				}
				else
					return ""+c;
			}			
		});
	}
	public static String changeFirstLetterMayus(String strInput, boolean mayus) {
		if(mayus)
			return strInput.substring(0,1).toUpperCase()+strInput.substring(1,strInput.length());
		else
			return strInput.substring(0,1).toLowerCase()+strInput.substring(1,strInput.length());
	}
	private static String replaceRegexpGrop(String strInput, String groupRegexp,
			StringOperation stringOperation) {

		Pattern pattern = Pattern.compile(groupRegexp);
		Matcher matcher = pattern.matcher(strInput);

		boolean found = false;
		int i=0, j=0, groupLength=0;
		String s="";
		while (matcher.find()) {
			found=true;
			j=matcher.start();
			String matchedGr = matcher.group();
			s+=strInput.substring(i,j);
			s+=stringOperation.make(matchedGr, null);//matchedGr.toUpperCase();
			i=matcher.end();
			groupLength=i-j;
		}
		if(found)
			s+=strInput.substring(j+groupLength, strInput.length());
		else{
			s=strInput;
		}		
		return s;
	}

	
	/**
	 * @j2sNative
	 * return escape(s);
	 */
	public static String escapeUrl(String s) {
		return java.net.URLEncoder.encode(s);
	}
	/**
	 * @j2sNative
	 * return unescape(s);
	 */
	public static String unescapeUrl(String s) {		
		//taken from http://www.w3.org/International/O-URL-code.html
		StringBuffer sbuf = new StringBuffer () ;
		int l  = s.length() ;
		int ch = -1 ;
		int b, sumb = 0;
		for (int i = 0, more = -1 ; i < l ; i++) {
			/* Get next byte b from URL segment s */
			switch (ch = s.charAt(i)) {
			case '%':
				ch = s.charAt (++i) ;
				int hb = (Character.isDigit ((char) ch) 
						? ch - '0'
								: 10+Character.toLowerCase((char) ch) - 'a') & 0xF ;
				ch = s.charAt (++i) ;
				int lb = (Character.isDigit ((char) ch)
						? ch - '0'
								: 10+Character.toLowerCase ((char) ch)-'a') & 0xF ;
				b = (hb << 4) | lb ;
				break ;
			case '+':
				b = ' ' ;
				break ;
			default:
				b = ch ;
			}
			/* Decode byte b as UTF-8, sumb collects incomplete chars */
			if ((b & 0xc0) == 0x80) {			// 10xxxxxx (continuation byte)
				sumb = (sumb << 6) | (b & 0x3f) ;	// Add 6 bits to sumb
				if (--more == 0) sbuf.append((char) sumb) ; // Add char to sbuf
			} else if ((b & 0x80) == 0x00) {		// 0xxxxxxx (yields 7 bits)
				sbuf.append((char) b) ;			// Store in sbuf
			} else if ((b & 0xe0) == 0xc0) {		// 110xxxxx (yields 5 bits)
				sumb = b & 0x1f;
				more = 1;				// Expect 1 more byte
			} else if ((b & 0xf0) == 0xe0) {		// 1110xxxx (yields 4 bits)
				sumb = b & 0x0f;
				more = 2;				// Expect 2 more bytes
			} else if ((b & 0xf8) == 0xf0) {		// 11110xxx (yields 3 bits)
				sumb = b & 0x07;
				more = 3;				// Expect 3 more bytes
			} else if ((b & 0xfc) == 0xf8) {		// 111110xx (yields 2 bits)
				sumb = b & 0x03;
				more = 4;				// Expect 4 more bytes
			} else /*if ((b & 0xfe) == 0xfc)*/ {	// 1111110x (yields 1 bit)
				sumb = b & 0x01;
				more = 5;				// Expect 5 more bytes
			}
			/* We don't test if the UTF-8 encoding is well-formed */
		}
		return sbuf.toString() ;

	}

    /**
     * leftJustify left-justifies a String value, space padding to width
     * characters if the string is less than width.
     *
     * @param value The value to left-justify
     * @param width The width to left-justify to
     * @return The left-justified value
     */
    public static String leftJustify(String value, int width) {
        StringBuffer sb = new StringBuffer(width);
        sb.append(value);
        for (int i = sb.length(); i < width; i++) {
            sb.append(' ');
        }
        
        if (sb.length() > width) {
            sb.setLength(width);
        }
        return sb.toString();
    }

    /**
     * rightJustify right-justifies a String value, space padding to
     * width characters if the string is less than width.
     *
     * @param value The value to right-justify
     * @param width The width to right-justify to
     * @return The right-justified value
     */
    public static String rightJustify(String value, int width) {
        StringBuffer sb = new StringBuffer(width);
        sb.append(value);
        for (int i = sb.length(); i < width; i++) {
            sb.insert(0, ' ');
        }
        
        if (sb.length() > width) {
            return sb.toString().substring(sb.length() - width);
        } else {
            return sb.toString();
        }
    }
    
	public static String centerJustify(String s, int length){
		return midpad(s, length, ' ');
	}

	/**
	 * Pad the beginning and end of the given String with the given character
	 * until the result is  the desired length.  The result is that the original
	 * String is centered in the middle of the new string.
	 * <p>
	 * If the number of characters to pad is even, then the padding
	 * will be split evenly between the beginning and end, otherwise,
	 * the extra character will be added to the end.
	 * <p>
	 * If a String is longer than the desired length,
	 * it will not be truncated, however no padding
	 * will be added.
	 *
	 * @param s String to be padded.
	 * @param length desired length of result.
	 * @param c padding character.
	 * @return padded String.
	 * @throws NullPointerException if s is null.
	 */
	public static String midpad(String s, int length, char c){
		int needed = length - s.length();
		if (needed <= 0){
			return s;
		}
		int beginning = needed / 2;
		int end = beginning + needed % 2;
		char prepadding[] = new char[beginning];
		java.util.Arrays.fill(prepadding, c);
		char postpadding[] = new char[end];
		java.util.Arrays.fill(postpadding, c);
		StringBuffer sb = new StringBuffer(length);
		sb.append(prepadding);
		sb.append(s);
		sb.append(postpadding);
		return sb.toString();
	}
	
	
    /**
     * very poor performance, not well tested, full justification 
     * text paragraph algorithm. @author: sgurin
     * @return
     */
    public static String fullJustify(String s, int width){
    	String [] words = s.split("\\s");
    	int actualWord=0;int count=0;
    	List <Integer> lineBreaks= new LinkedList<Integer>();
    	while(actualWord<words.length) {
    		String word = words[actualWord];
    		if(count+word.length()+1<=width){
    			actualWord++;
    			count+=word.length()+1;
    		}
    		else{
    			lineBreaks.add(actualWord);
    			count=0;
    		}
    	}
    	
    	//armamos el parrafo
    	int initWord=0, endWord=0;
    	String dest="";
    	for (Iterator iterator = lineBreaks.iterator(); iterator.hasNext();) {
    		initWord=endWord;
			endWord = (Integer) iterator.next();
			dest+=_concat(words, initWord, endWord, " ")+"\n";
		}
    	System.out.println("lineBreaks: "+lineBreaks);
    	return dest;
    }
	
	static String _concat(String [] a, int begin, int end, String sep) {
		String s = "";
		for(int i=begin; i<end; i++){
			s+=a[i];
			if(i!=end-1){
				s+=sep;
			}
		}
		return s;
	}
	
	
	
	/**
	 * Replaces characters that may be confused by a HTML
	 * parser with their equivalent character entity references.
	 * <p>
	 * Any data that will appear as text on a web page should
	 * be be escaped.  This is especially important for data
	 * that comes from untrusted sources such as Internet users.
	 * A common mistake in CGI programming is to ask a user for
	 * data and then put that data on a web page.  For example:<pre>
	 * Server: What is your name?
	 * User: &lt;b&gt;Joe&lt;b&gt;
	 * Server: Hello <b>Joe</b>, Welcome</pre>
	 * If the name is put on the page without checking that it doesn't
	 * contain HTML code or without sanitizing that HTML code, the user
	 * could reformat the page, insert scripts, and control the the
	 * content on your web server.
	 * <p>
	 * This method will replace HTML characters such as &gt; with their
	 * HTML entity reference (&amp;gt;) so that the html parser will
	 * be sure to interpret them as plain text rather than HTML or script.
	 * <p>
	 * This method should be used for both data to be displayed in text
	 * in the html document, and data put in form elements. For example:<br>
	 * <code>&lt;html&gt;&lt;body&gt;<i>This in not a &amp;lt;tag&amp;gt;
	 * in HTML</i>&lt;/body&gt;&lt;/html&gt;</code><br>
	 * and<br>
	 * <code>&lt;form&gt;&lt;input type="hidden" name="date" value="<i>This data could
	 * be &amp;quot;malicious&amp;quot;</i>"&gt;&lt;/form&gt;</code><br>
	 * In the second example, the form data would be properly be resubmitted
	 * to your CGI script in the URLEncoded format:<br>
	 * <code><i>This data could be %22malicious%22</i></code>
	 *
	 * @param s String to be escaped
	 * @return escaped String
	 * @throws NullPointerException if s is null.
	 *
	 * @since ostermillerutils 1.00.00
	 */
	public static String escapeHTML(String s){
		int length = s.length();
		int newLength = length;
		boolean someCharacterEscaped = false;
		// first check for characters that might
		// be dangerous and calculate a length
		// of the string that has escapes.
		for (int i=0; i<length; i++){
			char c = s.charAt(i);
			int cint = 0xffff & c;
			if (cint < 32){
				switch(c){
					case '\r':
					case '\n':
					case '\t':
					case '\f':{
						// Leave whitespace untouched
					} break;
					default: {
						newLength -= 1;
						someCharacterEscaped = true;
					}
				}
			} else {
				switch(c){
					case '\"':{
						newLength += 5;
						someCharacterEscaped = true;
					} break;
					case '&':
					case '\'':{
						newLength += 4;
						someCharacterEscaped = true;
					} break;
					case '<':
					case '>':{
						newLength += 3;
						someCharacterEscaped = true;
					} break;
				}
			}
		}
		if (!someCharacterEscaped){
			// nothing to escape in the string
			return s;
		}
		StringBuffer sb = new StringBuffer(newLength);
		for (int i=0; i<length; i++){
			char c = s.charAt(i);
			int cint = 0xffff & c;
			if (cint < 32){
				switch(c){
					case '\r':
					case '\n':
					case '\t':
					case '\f':{
						sb.append(c);
					} break;
					default: {
						// Remove this character
					}
				}
			} else {
				switch(c){
					case '\"':{
						sb.append("&quot;");
					} break;
					case '\'':{
						sb.append("&#39;");
					} break;
					case '&':{
						sb.append("&amp;");
					} break;
					case '<':{
						sb.append("&lt;");
					} break;
					case '>':{
						sb.append("&gt;");
					} break;
					default: {
						sb.append(c);
					}
				}
			}
		}
		return sb.toString();
	}

	/**
	 * Replaces characters that may be confused by an SQL
	 * parser with their equivalent escape characters.
	 * <p>
	 * Any data that will be put in an SQL query should
	 * be be escaped.  This is especially important for data
	 * that comes from untrusted sources such as Internet users.
	 * <p>
	 * For example if you had the following SQL query:<br>
	 * <code>"SELECT * FROM addresses WHERE name='" + name + "' AND private='N'"</code><br>
	 * Without this function a user could give <code>" OR 1=1 OR ''='"</code>
	 * as their name causing the query to be:<br>
	 * <code>"SELECT * FROM addresses WHERE name='' OR 1=1 OR ''='' AND private='N'"</code><br>
	 * which will give all addresses, including private ones.<br>
	 * Correct usage would be:<br>
	 * <code>"SELECT * FROM addresses WHERE name='" + StringHelper.escapeSQL(name) + "' AND private='N'"</code><br>
	 * <p>
	 * Another way to avoid this problem is to use a PreparedStatement
	 * with appropriate place holders.
	 *
	 * @param s String to be escaped
	 * @return escaped String
	 * @throws NullPointerException if s is null.
	 *
	 */
	public static String escapeSQL(String s){
		int length = s.length();
		int newLength = length;
		// first check for characters that might
		// be dangerous and calculate a length
		// of the string that has escapes.
		for (int i=0; i<length; i++){
			char c = s.charAt(i);
			switch(c){
				case '\\':
				case '\"':
				case '\'':
				case '\0':{
					newLength += 1;
				} break;
			}
		}
		if (length == newLength){
			// nothing to escape in the string
			return s;
		}
		StringBuffer sb = new StringBuffer(newLength);
		for (int i=0; i<length; i++){
			char c = s.charAt(i);
			switch(c){
				case '\\':{
					sb.append("\\\\");
				} break;
				case '\"':{
					sb.append("\\\"");
				} break;
				case '\'':{
					sb.append("\\\'");
				} break;
				case '\0':{
					sb.append("\\0");
				} break;
				default: {
					sb.append(c);
				}
			}
		}
		return sb.toString();
	}

	/**
	 * Replaces characters that are not allowed in a Java style
	 * string literal with their escape characters.  Specifically
	 * quote ("), single quote ('), new line (\n), carriage return (\r),
	 * and backslash (\), and tab (\t) are escaped.
	 *
	 * @param s String to be escaped
	 * @return escaped String
	 * @throws NullPointerException if s is null.
	 *
	 */
	public static String escapeJavaLiteral(String s){
		int length = s.length();
		int newLength = length;
		// first check for characters that might
		// be dangerous and calculate a length
		// of the string that has escapes.
		for (int i=0; i<length; i++){
			char c = s.charAt(i);
			switch(c){
				case '\"':
				case '\'':
				case '\n':
				case '\r':
				case '\t':
				case '\\':{
					newLength += 1;
				} break;
			}
		}
		if (length == newLength){
			// nothing to escape in the string
			return s;
		}
		StringBuffer sb = new StringBuffer(newLength);
		for (int i=0; i<length; i++){
			char c = s.charAt(i);
			switch(c){
				case '\"':{
					sb.append("\\\"");
				} break;
				case '\'':{
					sb.append("\\\'");
				} break;
				case '\n':{
					sb.append("\\n");
				} break;
				case '\r':{
					sb.append("\\r");
				} break;
				case '\t':{
					sb.append("\\t");
				} break;
				case '\\':{
					sb.append("\\\\");
				} break;
				default: {
					sb.append(c);
				}
			}
		}
		return sb.toString();
	}







	
}