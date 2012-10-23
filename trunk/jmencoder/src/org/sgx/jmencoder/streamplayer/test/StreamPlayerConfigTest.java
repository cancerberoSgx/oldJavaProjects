package org.sgx.jmencoder.streamplayer.test;

import java.io.IOException;

import org.sgx.jmencoder.streamplayer.StreamPlayerConfig;
import org.sgx.jmencoder.streamplayer.model.StreamEndpoint;

public class StreamPlayerConfigTest {
public static void main(String[] args) {
	try {
		testPlay("http://carve850.com.uy/directo.pls");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

private static void testPlay(String endpointUrl) throws IOException {
	StreamPlayerConfig config = StreamPlayerConfig.getInstance();
	config.doPlay(new StreamEndpoint("no", "no", "no", endpointUrl));
}
}
