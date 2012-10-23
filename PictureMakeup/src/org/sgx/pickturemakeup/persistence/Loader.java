package org.sgx.pickturemakeup.persistence;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface Loader {

	public BufferedImage load(String path) throws IOException;
	
	/**
	 * @param ext - algo como "png" (en minúsculas!)
	 * @return devuelve true si es capaz de entender la extensión ext
	 */
	public boolean understand(String ext);
	
	/**
	 * @return breve descripción del formato implementado por este saver
	 */
	public String getFormatDescription();

}
