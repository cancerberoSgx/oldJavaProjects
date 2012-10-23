package org.sgx.stringUtilities;

//import gnu.crypto.MyTests;

import java.util.Map;

import org.sgx.j2s.utils.Properties2JavaBean;
import org.sgx.j2s.utils.StringUtils;
import org.sgx.j2s.utils.Utils;
import org.sgx.j2s.widget.base.ListSelection;

public class Options {

	private static final String CASE_ID = "case";
	private static final String REPLACE_ID = "replace";
	private static final String QUOTE_ID = "quote";
	private static final String ESCAPE_URL_ID = "URL encode";
	private static final String BYTE_ARRAY_REPR_ID = "Byte array representation";
	private static final String MESSAGE_DIGEST_ID = "message digest (hash) methods";
	private static final String CIPHER_ID = "ciphers / encryption algoritmns";
	
	
	
	//operations parameters [id, des
	public static final String REPLACE_PARAM_OLD = "Regexp to match", 
		REPLACE_PARAM_NEW = "Replacement - String to replace matched regexp", 
		REPLACE_PARAM_FIRST = "Replace only first or all matchings of regexp?";
	
	public static final String CASE_TYPE = "Type of case to change", 
		CASE_TYPE_TITLE = "Title Type", CASE_TYPE_SENTENCE="Sentence type", 
		CASE_TYPE_MINUS="minus type", CASE_TYPE_MAYUS="MAYUS TYPE", 
		CASE_TYPE_INVERSE="iNVERSE tYPE";
	public static final String[] CASE_OPTS = new String[]
	      {CASE_TYPE_TITLE, CASE_TYPE_SENTENCE, CASE_TYPE_MINUS, CASE_TYPE_MAYUS, CASE_TYPE_INVERSE};
	
	private static final Object ESCAPE_URL_UNESCAPE = "Unescape ?", 
		QUOTE_STRING_CHAR = "String literal wrapper char", 
		QUOTE_PRETTY_PRINT_NEW_LINES = "pretty print new lines?", 
		QUOTE_CONCAT_OP = "String concatenation operator";
	protected static final String BYTE_ARRAY_REPR_LINE_WRAP = "line wrapping length. -1 for no wrap";
	protected static final String BYTE_ARRAY_REPR_SEPARATOR = "array item separator";
	public static final String PROPERTY_TO_JAVABEAN_REPR_ID = ".properties to java bean class";
//	private static final String PROPERTY_TO_JAVABEAN_CODE = "PROPERTY_TO_JAVABEAN_CODE";
	

	private static final String TEXT_JUSTIFICATION_REPR_ID = "justification";
//	private static final String TEXT_JUSTIFICATION_WIDTH_TYPE = null;
	private static final String TEXT_JUSTIFICATION_WIDTH = "width";
	private static final String TEXT_JUSTIFICATION_TYPE = "type";
	private static final String TEXT_JUSTIFICATION_TYPE_FULL = "full",
	TEXT_JUSTIFICATION_TYPE_CENTER = "center",TEXT_JUSTIFICATION_TYPE_LEFT = "left",
		TEXT_JUSTIFICATION_TYPE_RIGHT = "right",TEXT_JUSTIFICATION_TYPE_FULL2 = "full2";	
	private static final String[] TEXT_JUSTIFICATION_TYPES = new String[]{
		TEXT_JUSTIFICATION_TYPE_FULL,
		TEXT_JUSTIFICATION_TYPE_CENTER, TEXT_JUSTIFICATION_TYPE_LEFT,
		TEXT_JUSTIFICATION_TYPE_RIGHT, TEXT_JUSTIFICATION_TYPE_FULL2};
	protected static final int TEXT_JUSTIFICATION_DEFAULT_WIDTH = 80;
//	private static final String ESCAPE_SQL_REPR_ID = null;
	private static final String ESCAPE_LANGUAGE_ID = "escape language literals";
	private static final String ESCAPE_LANGUAGE_LANG = "language";
	private static final String ESCAPE_LANGUAGE_LANG_HTML = "html",
		ESCAPE_LANGUAGE_LANG_JAVA = "java", ESCAPE_LANGUAGE_LANG_SQL = "sql" ;
	private static final String[] ESCAPE_LANGUAGE_LANGS = new String[]{
		ESCAPE_LANGUAGE_LANG_HTML,ESCAPE_LANGUAGE_LANG_JAVA,ESCAPE_LANGUAGE_LANG_SQL};
	
	private static final String END_LINE_TYPE_WIN = "windows";
	private static final String END_LINE_TYPE_UNIX = "unix";
	private static final String END_LINE_TYPE_MAC = "macintosh";
	private static final String END_LINE_TYPE_CURRENT = "current system";
	private static final String[] END_LINE_TYPES = new String[]{END_LINE_TYPE_WIN, END_LINE_TYPE_UNIX, END_LINE_TYPE_MAC, END_LINE_TYPE_CURRENT};
	private static final String END_LINE_TO_TYPE = "to";
	private static final String END_LINE_FROM_TYPE = "from";
	private static final String END_LINE_ID = "newline character conversor";
	
//	public static final String[] operationNames = {CASE_ID, REPLACE_ID, ESCAPE_URL_ID, 
//		QUOTE_ID, BYTE_ARRAY_REPR_ID, BYTE_ARRAY_REPR_ID, MESSAGE_DIGEST_ID, CIPHER_ID,
//			PROPERTY_TO_JAVABEAN_REPR_ID, TEXT_JUSTIFICATION_REPR_ID};
	
	public static String[] _operationNames=null;
	public static String[] getOperationNames() {
		if(_operationNames==null) {
			_operationNames=new String[operations.length];
			for (int i = 0; i < operations.length; i++) {
				_operationNames[i]=(String) operations[i][0];
			}
		}
		return _operationNames;		
	}
	
	public static final Object[][] operations = {
		
		{REPLACE_ID, 
			"Replaces substrings that matches the given regular \nexpression with the given replacement",
			new StringOperation() {
				public String make(String src, Map<String, Object>config) {
					String oldExpr = (String) config.get(REPLACE_PARAM_OLD ), 
						newExpr = (String) config.get(REPLACE_PARAM_NEW);
					Boolean first = (Boolean)config.get(REPLACE_PARAM_FIRST);
					System.out.println(first.booleanValue());
					if(first.toString().equals("true")) 
						return src.replaceFirst(oldExpr, newExpr);					
					else
						return src.replaceAll(oldExpr, newExpr);
				}			
			}, 
			Utils.toMap(new Object[][]{
					{REPLACE_PARAM_OLD, ""},
					{REPLACE_PARAM_NEW, ""},
					{REPLACE_PARAM_FIRST, false}
			})
		}, 
		
		{CASE_ID, 
			"Convert given string to upper/lower case",
			new StringOperation() {
			public String toString(){return CASE_ID+" operation";}
				public String make(String src, Map<String, Object>config) {
					ListSelection ls = (ListSelection) config.get(CASE_TYPE );
					Object type = ls.getSelection().get(0);
					if(type.equals(CASE_TYPE_MAYUS)){
						return src.toUpperCase();
					}
					if(type.equals(CASE_TYPE_MINUS)){
						return src.toLowerCase();
					}
					else if(type.equals(CASE_TYPE_SENTENCE)){
						return StringUtils.caseSentence(src);
					}
					else if(type.equals(CASE_TYPE_TITLE)){
						return StringUtils.caseTitle(src);
					}
					else if(type.equals(CASE_TYPE_INVERSE)){
						return StringUtils.caseInvert(src);
					}
					return src;
				}			
			}, 
			Utils.toMap(new Object[][]{
					{CASE_TYPE, new ListSelection(CASE_OPTS, new String[]{CASE_TYPE_TITLE}, false)}
			})
			
		}, 
		
		{ESCAPE_URL_ID, 
			"convert a string into valid URL format",
			new StringOperation() {
				public String toString(){return ESCAPE_URL_ID+" operation";}
				public String make(String src, Map<String, Object>config) {
					Boolean unescape = (Boolean) config.get(ESCAPE_URL_UNESCAPE);
					System.out.println(unescape);
					if(unescape.toString().equals("false")) {
						return StringUtils.escapeUrl(src);
					}
					else
						return StringUtils.unescapeUrl(src);
				}			
			}, 
			Utils.toMap(new Object[][]{
					{ESCAPE_URL_UNESCAPE, new Boolean(false)}
			})			
		}, 
		
		{ESCAPE_LANGUAGE_ID, 
			"Replaces characters that may be confused by selected language parser with their equivalent character entity references.",
			new StringOperation() {
				public String make(String src, Map<String, Object>config) {
					try {
						String type = ((ListSelection<String>) config.get(ESCAPE_LANGUAGE_LANG)).getSelection().get(0);
						if(type.equals(ESCAPE_LANGUAGE_LANG_HTML)){
							return StringUtils.escapeHTML(src);
						}
						else if(type.equals(ESCAPE_LANGUAGE_LANG_SQL)){
							return StringUtils.escapeSQL(src);
						}
						else if(type.equals(ESCAPE_LANGUAGE_LANG_JAVA)){
							return StringUtils.escapeJavaLiteral(src);
						}
						else return src;
					}catch (Exception e) {
						e.printStackTrace();
						return "";
					}
				}			
			}, 
			Utils.toMap(new Object[][]{
					{ESCAPE_LANGUAGE_LANG, new ListSelection<String>(ESCAPE_LANGUAGE_LANGS, new String[]{ESCAPE_LANGUAGE_LANG_HTML}, false)}
			})
		}, 		
		
		{QUOTE_ID, 
			"Quote a string ready for being included as \na literal in source files.",
			new StringOperation() {
				public String make(String src, Map<String, Object>config) {
					Boolean pretty = (Boolean) config.get(QUOTE_PRETTY_PRINT_NEW_LINES);
					String C = (String)config.get( QUOTE_STRING_CHAR);
					String OP = (String)config.get( QUOTE_CONCAT_OP);
					src=src.replace("\\","\\\\");
					src=src.replace("\"", "\\\"");
					src=C+src+C;	
					if(pretty.toString().equals("true"))
						src=src.replace("\n", "\\n"+C+OP+"\n"+C);
					else
						src=src.replace("\n", "\\n");
					
					return src;
				}			
			}, 
			Utils.toMap(new Object[][]{
					{QUOTE_STRING_CHAR,"\""},
					{QUOTE_PRETTY_PRINT_NEW_LINES, new Boolean(true)},
					{QUOTE_CONCAT_OP, "+"}
			})			
		}, 
		
		{BYTE_ARRAY_REPR_ID, 
			"prints the byte array literal that conforms current string",
			new StringOperation() {
				public String make(String src, Map<String, Object>config) {
					Integer pretty = (Integer) config.get(BYTE_ARRAY_REPR_LINE_WRAP);
					String C = (String)config.get(BYTE_ARRAY_REPR_SEPARATOR);
					String s = "";
					byte[] b = src.getBytes();
					for (int i = 0; i < b.length; i++) {
						s+=b[i]+C;						
					}
					return s;
				}			
			}, 
			Utils.toMap(new Object[][]{
					{BYTE_ARRAY_REPR_LINE_WRAP,new Integer(80)},
					{BYTE_ARRAY_REPR_SEPARATOR, ", "}
			})			
		}, 
		
		{PROPERTY_TO_JAVABEAN_REPR_ID, 
			"builds a javabean class code representing the .properties given content. ",
			new StringOperation() {
				public String make(String src, Map<String, Object>config) {
					try {
					Properties2JavaBean conversor = new Properties2JavaBean();
					conversor.load(src);
					String beanstr = conversor.propertiesToBean("ConfigBean", "org.sgx.claroscuro");
					return beanstr;
					}catch (Exception e) {
						e.printStackTrace();
						return "";
					}
				}			
			}, 
			Utils.toMap(new Object[][]{
					//{PROPERTY_TO_JAVABEAN_CODE, "attrName1=3.21\nattrName2=hello world"},
			})			
		}, 
		
		
		{TEXT_JUSTIFICATION_REPR_ID, 
			"do left, right, center or full justification to a text",
			new StringOperation() {
				public String make(String src, Map<String, Object>config) {
					try {
						ListSelection type = (ListSelection) config.get(TEXT_JUSTIFICATION_TYPE);
						
						Integer width = (Integer) config.get(TEXT_JUSTIFICATION_WIDTH);
						Object selection = type.getSelection().get(0);
						if(selection.equals(TEXT_JUSTIFICATION_TYPE_FULL)){
							return StringUtils.fullJustify(src, width);
						}
						else if(type.equals(TEXT_JUSTIFICATION_TYPE_RIGHT)){
							return StringUtils.rightJustify(src, width);
						}  
						else if(type.equals(TEXT_JUSTIFICATION_TYPE_CENTER)){
							return StringUtils.centerJustify(src, width);					
						} 
						else if(type.equals(TEXT_JUSTIFICATION_TYPE_LEFT)){
							return StringUtils.leftJustify(src, width);
						} 
						else{
							return src;	
						}
					}catch (Exception e) {
						e.printStackTrace();
						return "";
					}
				}			
			}, 
			Utils.toMap(new Object[][]{
					{TEXT_JUSTIFICATION_TYPE, 
						new ListSelection(TEXT_JUSTIFICATION_TYPES, new String[]{TEXT_JUSTIFICATION_TYPE_FULL}, false)},
					{TEXT_JUSTIFICATION_WIDTH, new Integer(80)}
			})			
		}, 
		
		{END_LINE_ID, 
			"change endline character encoding dos<->mac<->unix",
			new StringOperation() {
				public String make(String src, Map<String, Object>config) {
					try {
						String from = (String) ((ListSelection) config.get(END_LINE_FROM_TYPE)).getSelection().get(0);
						String to=(String) ((ListSelection) config.get(END_LINE_TO_TYPE)).getSelection().get(0);
						Map<Object, Object> NLRegexpMap=Utils.toMap(new Object[][]{
								{END_LINE_TYPE_WIN,"\\\\n\\\\r"},
								{END_LINE_TYPE_UNIX,"\\\\n"},
								{END_LINE_TYPE_MAC,"\\\\r"},
								{END_LINE_TYPE_CURRENT,System.getProperty( "line.separator" )},
						});
						Map<Object, Object> NLReplacementMap=Utils.toMap(new Object[][]{
								{END_LINE_TYPE_WIN,"\\n\\r"},
								{END_LINE_TYPE_UNIX,"\\n"},
								{END_LINE_TYPE_MAC,"\\r"},
								{END_LINE_TYPE_CURRENT,System.getProperty( "line.separator" )},
						});
						String out = src.replaceAll((String)NLRegexpMap.get(from), (String)NLReplacementMap.get(to));
						
						return out;
					}catch (Exception e) {
						e.printStackTrace();
						return "";
					}
				}			
			}, 
			Utils.toMap(new Object[][]{
					{END_LINE_FROM_TYPE, new ListSelection(END_LINE_TYPES, new String[]{END_LINE_TYPE_WIN}, false)},
					{END_LINE_TO_TYPE, new ListSelection(END_LINE_TYPES, new String[]{END_LINE_TYPE_UNIX}, false)}
			})			
		}, 
		
//		{MESSAGE_DIGEST_ID, 
//			"apply selected message digest algorithm to given string",
//			new StringOperation() {
//				public String make(String src, Map<String, Object>config) {
//					ListSelection<String> list = (ListSelection<String>) config.get("message digest method");
//					String digestName = list.getSelection().get(0);
//					return new String(MyTests.digest(digestName, src));					
//				}			
//			}, 
//			Utils.toMap(new Object[][]{
//					{"message digest method", new ListSelection<String>(MyTests.messageDigestMethods, new String[]{MyTests.messageDigestMethods[0]},false)}
//			})			
//		}, 
		
//		{CIPHER_ID, 
//			"apply selected message digest algorithm to given string",
//			new StringOperation() {
//				public String make(String src, Map<String, Object>config) {
//					
//					ListSelection<String> list = (ListSelection<String>) config.get("cipher method");
//					String digestName = list.getSelection().get(0);
//					return new String(MyTests.digest(digestName, src));					
//				}			
//			}, 
//			Utils.toMap(new Object[][]{
//					{"cipher method", new ListSelection<String>(MyTests.messageDigestMethods, new String[]{MyTests.messageDigestMethods[0]},false)}
//			})			
//		}
	};
//	/**
//	 * return the first object in column colNumTarget wich its row have the 
//	 * value equals() to key in item corresponding to colNumToMatch
//	 */
//	public static Object getArray2DProperty(String[][] a, int colNumToMatch, Object key,
//			int colNumTarget) {
//		
//		for (int i = 0; i < a.length; i++) {
//			String id = (String) a[i][colNumToMatch];
//			if(id.equals(key))
//				return  a[i][2];
//		}
//		return null;
//	}
	public static StringOperation getOperationFor(String opId) {
		for (int i = 0; i < operations.length; i++) {
			String id = (String) operations[i][0];
			if(id.equals(opId))
				return (StringOperation) operations[i][2];
		}
		return null;
	}
	
	public static Map<String, Object> getOperationOptionsFor(String opId) {
		for (int i = 0; i < operations.length; i++) {
			String id = (String) operations[i][0];
			if(id.equals(opId))
				return (Map<String, Object>) operations[i][3];
		}
		return null;
	}
	
	public static String getDescriptionFor(String opId) {
		for (int i = 0; i < operations.length; i++) {
			String id = (String) operations[i][0];
			if(id.equals(opId))
				return (String) operations[i][1];
		}
		return null;
	}

	
	
}
