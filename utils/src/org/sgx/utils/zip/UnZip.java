package org.sgx.utils.zip;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.*;

import org.sgx.utils.FSUtils;

/**
 * 
 * @author sgurin
 *
 */
public class UnZip {
	List<UnZipListener> listeners;
	
	File zipFile;
	File outputFolder;

	private ZipInputStream zin;
	
	public UnZip(File zipFile, File outputFolder) {
		super();
		this.zipFile = zipFile;
		this.outputFolder = outputFolder;
		listeners=new LinkedList<UnZipListener>();
	}
	public UnZip(File in, File out, UnZipListener l) {
		this(in,out);
		addListener(l);
	}
	public  void addListener(UnZipListener object) {
		listeners.add(object);
	}			
	private void notifyAll(String f){
		for(UnZipListener l : listeners)
			l.notifyFileZip(f);
	}
	
	public void unzip() throws IOException {
		if(!outputFolder.isDirectory())
			throw new RuntimeException("Unzip errpr: outputFolder "+ outputFolder+" does not exists");
		InputStream in = new BufferedInputStream(new FileInputStream(zipFile));
		zin = new ZipInputStream(in);
		ZipEntry e;
		while((e=zin.getNextEntry())!= null) 			
			unzip(e);
		zin.close();
		in.close();
	}
	
	public void unzip(ZipEntry ze) throws IOException {
		String e = ze.getName();		
		String fs = FSUtils.normalizePath(outputFolder.getAbsolutePath())+File.separator+e;
		String rootPath = FSUtils.getRootDir(fs);
		if(rootPath!=null) {
			File r = new File(rootPath);
			if(!r.exists())
				r.mkdirs();
		}
		if(ze.isDirectory())
			new File(fs).mkdirs();
		FileOutputStream out = new FileOutputStream(fs);
		byte [] b = new byte[512];
		int len = 0;
		while ( (len=zin.read(b))!= -1 ) {
			out.write(b,0,len);
		}
		out.close();
		notifyAll(fs);
	}
	
	public static void main(String args[]) {		
		UnZip u = new UnZip(new File("C:\\tablaturas\\abc.zip"),new File("C:\\tablaturas\\ou" ));
		u.addListener(new UnZipListener() {
			public void notifyFileZip(String abspath) {
				System.out.println("unzipping: "+abspath);
			}			
		});
		try {
			u.unzip();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
}