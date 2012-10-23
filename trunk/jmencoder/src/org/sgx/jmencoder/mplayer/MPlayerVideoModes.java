package org.sgx.jmencoder.mplayer;

import java.util.Collection;

import org.sgx.utils.JavaUtils;
import org.sgx.utils.Predicate;


public class MPlayerVideoModes {

	public static final String videoModePrefId ="mplayer.videoMode";
	
	public static final String PERCENT_POS = "percent_pos";

	public static final int OS_WIN = 1, OS_MACOS=2, OS_LINUX=4;
	
	public static final Object [][] videoModes = {
		{"directx", "DirectX interface", OS_WIN},
		{"winvidix ", "Windows frontend for VIDIX", OS_WIN},
		{"directx:noaccel", "DirectX interface (with hardware acceleration turned off)", OS_WIN},
		{"quartz","Mac OS X Quartz video output driver",OS_MACOS}, 
		{"macosx","Mac OS X CoreVideo video output driver",OS_MACOS},
		{"vesa","Very general video output driver that should work on any VESA VBE 2.0 compatible card.",OS_MACOS|OS_LINUX|OS_WIN},
		{"svga","Play video using the SVGA library",OS_MACOS|OS_LINUX|OS_WIN},
		{"gl","OpenGL video output driver, simple version",OS_MACOS|OS_LINUX|OS_WIN},
		{"gl","OpenGL video output driver, simple version",OS_MACOS|OS_LINUX|OS_WIN},
		{"gl2","OpenGL video output driver, second generation",OS_MACOS|OS_LINUX|OS_WIN},
		{"aa","ASCII art video output driver that works on a text console",OS_MACOS|OS_LINUX|OS_WIN}
			
	};

	public static final Object PAUSE = "pause";
	
	public static Collection<Object[]> getVideoModesFor(final int so) {
		return JavaUtils.select(videoModes, new Predicate<Object[]>() {
			public boolean select(final Object[] o) {
				return o[2].equals(so);
			}			
		});
	}
	public static Object[] getVideoModesIdsFor(final int so) {
		Collection<Object[]> c = getVideoModesFor(so);
		Object [] a = new Object[c.size()];
		int i=0;
		for(Object[] o : c) {
			a[i]=o[0];
			i++;
		}
		return a;
	}
	public static String getVideoModeDescriptionOf(final String s) {
		return (String) ((Object[]) JavaUtils.selectFirst(videoModes, new Predicate<Object[]>() {
			public boolean select(Object[] o) {
				return o[0].equals(s);
			}			
		}))[1];
	}

}
