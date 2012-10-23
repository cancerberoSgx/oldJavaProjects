package org.sgx.j2s.soundManager;

public class SoundManagerTest {

	/**
		 * @param args
		 */
		public static void main(String[] args) {
	
			System.out.println("inicio");
	//		DomUtils.loadScript("soundmanager2.js");
//			testNative();
			SoundManager sm = SoundManager.getInstance();
			test1(sm);
			
	 
	 System.out.println("fin2");
		}

	static String validMp3Url = "/mathWebThings/static/res/C4.mp3";

	private static void testNative() {
		String mp3Url = validMp3Url;
		/**
		@j2sNative
		  var mySound = soundManager.createSound({
		    id: 'aSound',
		    url: mp3Url,
		    volume: 50
		  });
		  mySound.play();
		 */{}
	}

	/**
	 * test loading of several audio files and then playing them simulstaneously
	 * @param sm
	 */
	private static void test2(SoundManager sm) {
		String []soundParths = new String[]{
				"http://localhost:8080/mathWebThings_wp/static/res/C4.mp3",
				"http://localhost:8080/mathWebThings_wp/static/res/E4.mp3",
				"http://localhost:8080/mathWebThings_wp/static/res/G4.mp3"};
		Sound[] sounds = new Sound[soundParths.length];
		
	} 

	static void test1(SoundManager soundManager) {
//		Runnable r = new Runnable(){
//			public void run() {
				SoundManager sm = SoundManager.getInstance();
				String base = "";//http://localhost/src/org/sgx/j2s/soundManager";
				String soundPath ="/mathWebThings/static/res/C4.mp3";
				if(!sm.canPlayURL(base+soundPath))
					System.out.println("cant play url "+base+soundPath);
				if(!sm.supported())
					System.out.println("SoundManager not supported");
				System.out.println("sm loaded");
				sm.setBaseUrl(base);
				sm.createSound("c4", soundPath);
				sm.play("c4");
//			}
//		};
//		soundManager.onload(r);
//		r.run();
	}

}
