package org.sgx.pickturemakeup.persistence;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * interface que tienen que realizar aquellas clases cuya misión sea saber cómo 
 * guardar en filesystem un archivo en un formato determinado
 * 
 * @author sgx
 *
 */
public interface Saver {
	
	/**
	 * almacena buffi en filesystem en el archivo path. si el archivo existe será sobreescrito
	 * @param buffi
	 * @param path
	 */
	public void save(BufferedImage buffi, String path) throws IOException ;
	
	/**
	 * @param ext - algo como "png" (en minúsculas!)
	 * @return devuelve true si es capaz de entender la extensión ext
	 */
	public boolean understand(String ext);
	
	/**
	 * aviso de pérdida de calidad  
	 * @param buffi
	 * @return un string no nulo si la imagen perderá resolución/calidad al 
	 * persistirla en el formato que maneja la clase que realiza esta interfaz. 
	 * Si no se perderá calidad se debe devolver null 
	 */
	public String LooseDataMsg(BufferedImage buffi);
	
	/**
	 * @return breve descripción del formato implementado por este saver
	 */
	public String getFormatDescription();

}
