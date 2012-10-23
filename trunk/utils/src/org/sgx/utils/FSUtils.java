package org.sgx.utils;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.sgx.utils.zip.UnZip;
import org.sgx.utils.zip.UnZipListener;
import org.sgx.utils.zip.Zip;
import org.sgx.utils.zip.ZipListener;



public class FSUtils {

	public final static String iso_8859_1 = "ISO-8859-1";
	public final static String utf_8 = "UTF-8";
	public final static String utf_16 = "UTF-16";
	public final static String ascii = "ASCII";
	
	public static final String[] charsets = {iso_8859_1, utf_8, utf_16, ascii};

	//object persistence
	/**
	 * @param o must be a javabean or collections of javabeans
	 * @param f the serialized o
	 * @throws FileNotFoundException 
	 */
	public static void serialize(Object o, File f) throws FileNotFoundException {
		XMLEncoder enc = new XMLEncoder(new FileOutputStream(f));
		enc.writeObject(o);
		enc.close();
	}

	public static String serialize(Object o) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		XMLEncoder enc = new XMLEncoder(baos);
		enc.writeObject(o);
		enc.close();
		baos.close();
		return baos.toString();		
		
	}
	public static Object deserialize(File f) throws FileNotFoundException {
		XMLDecoder dec = new XMLDecoder(new FileInputStream(f));
		Object o = dec.readObject();
		dec.close();
		return o;		
	}
	public static Object deserialize(String s) {
		ByteArrayInputStream bais = new ByteArrayInputStream(s.getBytes());
		XMLDecoder dec = new XMLDecoder(bais);
		Object o = dec.readObject();
		dec.close();
		return o;	
	}

	public static String toCharset(String charsetName, String s) throws CharacterCodingException {
		  // ISO-8859-1, UTF-8, latin1		
	    Charset charset = Charset.forName(charsetName);
	    CharsetDecoder decoder = charset.newDecoder();
	    CharsetEncoder encoder = Charset.forName(charsetName).newEncoder();	    
	    encoder.onUnmappableCharacter(CodingErrorAction.REPLACE);
	    ByteBuffer bbuf = encoder.encode(CharBuffer.wrap(s));    
	    CharBuffer cbuf = decoder.decode(bbuf);
	    String out= cbuf.toString();
	    return out.replace('?', notSupCharsetReplaceChar);        
	}

	/**
	 * don't do nothing if file name is in charset c
	 * @param c
	 * @param f
	 * @throws CharacterCodingException
	 */
	public static void renameToCharset(String c, File f) throws CharacterCodingException {
		String name = f.getName();
		String newName = toCharset(c, name);
		if(!name.equals(newName)) {
			f.renameTo(new File(newName));
		}
	}

	public static boolean isCharset(String charSet, String str ) {
		try {
			toCharset(charSet, str);
		}catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean isCharset(String charSet, List<String> l ) {
		for(String s : l)
			if(!isCharset(charSet, s))
				return false;
		return true;
	}
	public static String detectCharset(String s) {
		for(int i=0; i<charsets.length; i++) {
			if(isCharset(charsets[i], s))
				return charsets[i];
		}
		return "unkknown";
	}
	/**
	 * use with caution
	 * @param charset
	 * @param f
	 * @throws CharacterCodingException
	 */
	public static void renameRecursiveToCharset(String charset, File f) throws CharacterCodingException {
		String name = f.getName();
		String newName = toCharset(charset, name);
		if(!name.equals(newName)) {
			if(f.exists()) 
				f.renameTo(new File(newName));
			File parent = f.getParentFile();
			if(parent.exists())
				renameRecursiveToCharset(charset, parent);
		}
	}

	//file system 
	public static String getFileExtension(String filename) {
		return filename.substring(filename.lastIndexOf('.')+1, filename.length());
	}

	public static String getFileName(String filename) {
		return filename.substring(0, filename.lastIndexOf('.'));
	}

	public static String getFileParentName(String filename) {
		if(filename.lastIndexOf(File.separator)!=-1)
			return filename.substring(0, filename.lastIndexOf(File.separator));
		else return null;
	}

	/**
	 * le saca el File.separator que pueda tener al final
	 */
	public static String normalizePath(String path) {
		if(path.endsWith(File.separator))
			return path.substring(0, path.length()-1);
		else return path;
	}
	public static String getRootDir(String path) {
		int i = path.lastIndexOf(File.separator);
		if(i!=-1) {
			return path.substring(0,i);
		}
		else return null;
	}
	



	private static final char notSupCharsetReplaceChar = '_';
	
	
	
	public static InputStream stringToInputStream(String s) {
		StringBuffer StringBuffer1 = new StringBuffer(s);
		ByteArrayInputStream Bis1 = new ByteArrayInputStream(StringBuffer1.toString().getBytes());
		return Bis1;
	}
	
 	/** @param filePath the name of the file to open. Not sure if it can accept URLs or just filenames. Path handling could be better, and buffer sizes are hardcoded
    */ 
    public static String readFileAsString(String filePath) throws java.io.IOException{
    	return readFileAsString(new FileInputStream(filePath));
//        StringBuffer fileData = new StringBuffer(1000);
//        BufferedReader reader = new BufferedReader(
//                new FileReader(filePath));
//        char[] buf = new char[1024];
//        int numRead=0;
//        while((numRead=reader.read(buf)) != -1){
//            String readData = String.valueOf(buf, 0, numRead);
//            fileData.append(readData);
//            buf = new char[1024];
//        }
//        reader.close();
//        return fileData.toString();
    }

    
    public static String inputStreamAsString(InputStream in)
    throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(in));
	    StringBuilder sb = new StringBuilder();
	    String line = null;	
	    while ((line = br.readLine()) != null) {
	    	sb.append(line + "\n");
	    }	
	    br.close();
	    return sb.toString();
    }
    
	public static void readFile(InputStream in, StringBuffer sb)
			throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line = null;
		while ((line = br.readLine()) != null) {
			sb.append(line + "\n");
		}
		br.close();
	}
	public static String readFile(FileInputStream fis) throws IOException {
		StringBuffer sb = new StringBuffer();
		readFile(fis, sb);
		return sb.toString();
	}
	public static String readFileAsString(InputStream in) throws IOException {
		StringBuffer sb = new StringBuffer();
		readFile(in, sb);
		return sb.toString();
	}
	
	public static void writeStringToFile(String s, File f) throws IOException {
		FileOutputStream fos = new FileOutputStream(f);
		fos.write(s.getBytes());
		fos.close();
	}
	
	
	
	//zip files:
	public static void zipFile(File in, File out, Predicate<File> predicate, ZipListener l) throws IOException {
		Zip u = new Zip(in.getAbsolutePath(), out.getAbsolutePath(), predicate);
		u.addListener(l);
		u.zip();
	}
	
	public static void unzipFile(File in, File out, UnZipListener l) throws IOException {
		new UnZip(in, out, l).unzip();
	}

	public static File[] pathToFiles(String[] args) {
		File[] fs = new File[args.length];
		for(int i=0;i<args.length; i++) {
			fs[i]=new File(args[i]);
		}
		return fs;
	}
	
	
	public static boolean dirContains(File dir, String name) {
		String[] childs = dir.list();
		if(childs==null)
			return false;
		for (int i = 0; i < childs.length; i++) {
			if(childs[i].equals(name))
				return true;
		}
		return false;
	}
	public static String whichFolder(String commandName) {
		String pathEnv = System.getenv("PATH");
		String []paths = pathEnv.split(File.pathSeparator);
		for (int i = 0; i < paths.length; i++) {
			if(dirContains(new File(paths[i]), commandName))
				return paths[i];
		}
		return null;
	}

	public static String which(String commandName, String execExtension) {
		return whichFolder(commandName)+File.separator+commandName+
			(execExtension==null || execExtension.equals("") ? "" : "."+execExtension);
	}

	public static String transformValidFileName(String s, String replacement) {
		return s.replaceAll("[^a-zA-Z0-9\\s\\-\\_]", replacement);
	}
	
	public static void copyDirAndReplaceRecursively(File src, File dest, String filter, 
			String str, String replacement) throws FileNotFoundException, IOException {
		if(src.isDirectory()) {
			dest.mkdirs();
			File[] childs = src.listFiles();
			for (int i = 0; i < childs.length; i++) {
				File child = childs[i];
				File destChild = new File(dest.getAbsolutePath()+File.separator+child.getName());
				copyDirAndReplaceRecursively(child, destChild, filter, str, replacement);
			}
		}
		else {
			if(src.getName().contains(filter)) {				
				copyFileAnReplacetext(src, dest, str, replacement);
			}
			else {
				copyFileAnReplacetext(src, dest, null, null);
			}				
		}
			
	}
	public static void copyFileAnReplacetext(File srcFile, File destFile, String str, 
			String replacement) throws FileNotFoundException, IOException {
		StringBuffer sb = new StringBuffer();
		InputStream is = new FileInputStream(srcFile);
		readFile(is, sb);
		is.close();
		String s = sb.toString();
		if(str!=null && replacement!=null)
			s = s.replace(str, replacement);
		FileOutputStream os = new FileOutputStream(destFile);
		os.write(s.getBytes());
		os.close();
	}


}
