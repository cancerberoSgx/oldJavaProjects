package org.sgx.utils.zip;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.io.*;

import org.sgx.utils.JavaUtils;
import org.sgx.utils.Predicate;
import org.sgx.utils.Utils;

 /**
  * taken from http://forums.sun.com/thread.jspa?messageID=2158052
  * @author nati
  *
  */
public class Zip {
	
	
	private ZipOutputStream cpZipOutputStream = null;
	private String strSource = "";		
	private String strTarget = "";		
	private Predicate<File> p = defaultPredicate;
	List<ZipListener> listeners;
	
	static Predicate<File> defaultPredicate = new Predicate<File>() {
		public boolean select(File o) {
			return true;
		}		
	};
	
	private static long  size          = 0;	
	private static long   numOfFiles    = 0;		
	/**
	 * main constructor. invoke please!
	 * @param predicate: will zip only thos files that math predicate
	 */
	public Zip(String strSource, String strTarget, Predicate<File> predicate) {
		super();
		this.strSource = strSource;
		this.strTarget = strTarget;
		this.p = predicate==null?defaultPredicate:predicate;
		listeners=new LinkedList<ZipListener>();
	}	
	public Zip(String strSource, String strTarget) {
		this(strSource, strTarget, defaultPredicate);
	}
	public  void addListener(ZipListener object) {
		listeners.add(object);
	}			
	private void notifyAll(File f){
		for(ZipListener l : listeners)
			l.notifyFileZip(f.getAbsolutePath(), numOfFiles, size);
	}
	
	public void zip() throws IOException {	
			File cpFile = new File (strSource);									
			if (!cpFile.isFile() && !cpFile.isDirectory() ) {							
				System.out.println("\nSource file/directory Not Found!");							
				return;					
			}								
			FileOutputStream fos = new FileOutputStream(strTarget);			
			cpZipOutputStream = new ZipOutputStream(fos);					
			cpZipOutputStream.setLevel(9);					
			zipFiles( cpFile);					
			cpZipOutputStream.finish();					
			cpZipOutputStream.close();					
			System.out.println("\n Finished creating zip file " + strTarget + " from source " + strSource);					
			System.out.println("\n Total of  " + numOfFiles +" files are Zipped " );			
			System.out.println("\n Total of  " + size  + " bytes are Zipped  ");		
	
	}		
	
	private void  zipFiles(File cpFile) {
		
		if(!p.select(cpFile))
			return;
		
		int byteCount;
		final int DATA_BLOCK_SIZE = 2048;	
		FileInputStream cpFileInputStream;
		if (cpFile.isDirectory()) {						
			File [] fList = cpFile.listFiles() ;						
			for (int i=0; i< fList.length; i++){								
				zipFiles(fList[i]) ;						
			}				
		} 
		else {						
			try {		
				if(cpFile.getAbsolutePath().equalsIgnoreCase(strTarget))
					return;
				
				size += cpFile.length();									
				numOfFiles++;				
				String strAbsPath = cpFile.getPath();				
				String strZipEntryName = strAbsPath.substring(strSource.length()+1, strAbsPath.length());								
				
				cpFileInputStream = new FileInputStream (cpFile) ;															
				ZipEntry cpZipEntry = new ZipEntry(strZipEntryName);
				cpZipOutputStream.putNextEntry(cpZipEntry );
				
				byte[] b = new byte[DATA_BLOCK_SIZE];
				while ( (byteCount = cpFileInputStream.read(b, 0, DATA_BLOCK_SIZE)) != -1)
					cpZipOutputStream.write(b, 0, byteCount);
				cpZipOutputStream.closeEntry() ;
				notifyAll(cpFile);
			} catch (Exception e) {								
				e.printStackTrace();						
			}				
		}		
	}

	public static void main(String args[]) {		
		Zip u = new Zip("C:\\tablaturas","C:\\tablaturas\\abc.zip" );
		u.addListener(new ZipListener() {
			public void notifyFileZip(String abspath, long numOfFiles, long size) {	
				System.out.println("hola:"+Utils.toString(new Object[] {abspath, numOfFiles, size}));
			}			
		});
		try {
			u.zip();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
 