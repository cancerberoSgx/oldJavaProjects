//package org.sgx.jmencoder.mplayer.videoInfoDetector;
//
//import java.io.File;
//import java.io.IOException;
//
//import javax.swing.JFrame;
//
//import org.sgx.swing.utils.SwingUtils;
//
//import junit.framework.TestCase;
//
//public class MediaInfoTest extends TestCase {
//
//	public void testBuildFrom() {
//		String [] olines = {"ID_DEMUXER=lavfpref"
//				,"ID_VIDEO_FORMAT=FLV1"
//				,"ID_VIDEO_BITRATE=0"
//				,"ID_VIDEO_WIDTH=200"
//				,"ID_VIDEO_HEIGHT=150"
//				,"ID_VIDEO_FPS=14.985"};
//		MediaInfo mi = MediaInfo.buildFrom(olines);
////		for(int i=0; i<olines.length; i++){
////			if(!mi.containsKey(olines[i]))
////				fail("key fail - " + olines[i]+" - "+);					
////		}
//		assertTrue(mi.get("ID_DEMUXER").equals("lavfpref"));
//		assertTrue(mi.get("ID_VIDEO_FPS").equals("14.985"));
//	}
//
//	//user interface. not junit
//	public static void realTest() {
//		File f = SwingUtils.chooseFile(new JFrame(), null,null,null);
//		try {
//			MediaInfo.getVideoInfo(f, new VideoInfoListener() {
//				public void notifyVideoInfo(MediaInfo m) {
//					System.out.println(m);
//				}			
//			});
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	//user interface. not junit
//	public static void realTest2() {
//		File f = SwingUtils.chooseFile(new JFrame(), null,null,null);
//		try {
//			System.out.println(MediaInfo.getVideoInfo(f));
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	public static void main(String []a) {
//		 realTest2() ;
//	}
//}
