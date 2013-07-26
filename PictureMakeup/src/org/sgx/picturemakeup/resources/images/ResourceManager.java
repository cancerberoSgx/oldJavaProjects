package org.sgx.picturemakeup.resources.images;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ResourceManager {
private static ResourceManager instance;

private ResourceManager() {
}

public static ResourceManager getInstance() {
	if (instance == null)
		instance = new ResourceManager();
	return instance;
}
/**
 * If you directory tree is something like:
<pre>
SomeClass.class
world
|--maps
   |--image.jpg
<pre>
You can use some reference like:

<pre>SomeClass.class.getResource("./world/maps/image.jpg")</pre>

If the class is inside some another directory, just add ../ to the path.

 * @param resName
 * @return
 */
public java.net.URL getResourceAsUrl(String resName) {
	return ResourceManager.class.getResource(resName); 
}
public BufferedImage getImage(String resName) throws IOException {
	URL url = getResourceAsUrl(resName); 
	BufferedImage img = ImageIO.read(url);
	return img; 
}
public BufferedImage pic2() throws IOException {
	return getImage("pic2.jpg"); 
}
}
