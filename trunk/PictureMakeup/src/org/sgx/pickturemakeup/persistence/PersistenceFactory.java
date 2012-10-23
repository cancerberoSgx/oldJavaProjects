package org.sgx.pickturemakeup.persistence;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * mi responsabilidad es conocer todos los savers y loaders. A mi me pueden pedir un saver
 * o un loader para una extensión dada. 
 * 
 * @author sgx
 *
 */
public class PersistenceFactory {

	public static final String SupportedExtensions [] = new String [] {"png", "gif", "bmp", "jpg"};
	
	List<Saver> savers;
	List<Loader> loaders;
	
	public void registerLoader(Loader l) {
		System.out.println("registering loader: "+loaders);
		if(!loaders.contains(l))
			loaders.add(l);
	}
	public void registerSaver(Saver l) {
		if(!savers.contains(l))
			savers.add(l);
	}
	
	private PersistenceFactory() {
		savers = new LinkedList<Saver>();
		loaders = new LinkedList<Loader>();
		register();
	}


	private void register() {
		registerLoader(new PNGLoader());
		
		registerSaver(new PNGSaver());
	}


	static PersistenceFactory instance;
	
	public static PersistenceFactory getInstance() {
		if(instance==null)
			instance = new PersistenceFactory();
		return instance;
	}
	
	/**
	 * @param ext
	 * @return un objeto que sea capaz de guardar imagenes con extension ext
	 * o null si no se encuentra tal saver.
	 */
	public Saver getSaverForExtension(String ext) {
		Iterator<Saver> i = savers.iterator();
		while(i.hasNext()) {
			Saver s = i.next();
			if(s.understand(ext))
				return s;			
		}
		return null;
	}
	
	/**
	 * @param ext
	 * @return un objeto que sea capaz de cargar imagenes con extension ext
	 * o null si no se encuentra tal Loader.
	 */
	public Loader getLoaderForExtension(String ext) {
		System.out.println("loaders: "+loaders);
		Iterator<Loader> i = loaders.iterator();
		while(i.hasNext()) {
			Loader s = i.next();
			if(s.understand(ext.toLowerCase()))
				return s;				
		}
		return null;
	}
	
	public String [] getSupportedExtensions() {
		return SupportedExtensions;
	}

	public Object getDefaultExtension() {
		return "png";
	}
}
