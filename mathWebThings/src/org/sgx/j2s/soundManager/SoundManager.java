package org.sgx.j2s.soundManager;

import org.sgx.j2s.html.myApi.DomUtils;
import org.sgx.j2s.utils.JsUtils;
import org.sgx.j2s.utils.Utils;

/**
 * this is a SoundManager java2script Wrapper class. 
 * Includes SM2 minimized javascript code
 *  
 * @author: sgurin
 
*/
public class SoundManager {

	private static SoundManager instance;

	private SoundManager() {
	}

	public static SoundManager getInstance() {
		if (null == instance) {
			instance = new SoundManager();
		}
		return instance;
	}
	
	/**
	 * @j2sNative
	 * return soundManager.baseUrl = url;
	 */
	public void setBaseUrl(String url) {
		
	}
	/**
	 * @j2sNative
	 * soundManager.debugMode = b;
	 */
	public void setDebugMode(boolean b) {
		
	}
	
	/**
	 * @j2sNative
	 * return soundManager.useHighPerformance = b;
	 */
	public void setUseHighPerformance(boolean b) {
		
	}
	
	/**
	 * @j2sNative
	 * return soundManager.onload=r.run;
	 */
	public void onload(Runnable r) {		
	}
	
	public Sound createSound(String name, String url) {		
		return new Sound(createSound_(name,url));
	}
	/**
	 * @j2sNative
	 * return soundManager.createSound(name, url);
	 */
	private Object createSound_(String name, String url) {		
		return null;
	}
	/**
	 */
	public Sound createSound(Object[][]params) {
		Object jsParams = Utils.toJavascript(params);
		/**
		 * @j2sNative
		 * return soundManager.createSound(jsParams);
		 */{}
		 return null;
	}
	
	public void createVideo(Object[][]params) {
		Object jsParams = Utils.toJavascript(params);
		/**
		 * @j2sNative
		 * return soundManager.createVideo(jsParams);
		 */{}
	}
	/**
	 * @j2sNative
	 * return soundManager.destroySound(soundName);
	 */
	public void destroySound(String soundName){}
	/**
	 * @j2sNative
	 * return soundManager.destroyVideo(vName);
	 */
	public void destroyVideo(String vName){}
	
	
	/**
	 * @j2sNative
	 * return soundManager.play(soundName);
	 */
	public void play(String soundName) {
	}
	
	public void play(String soundName, Object[][]params) {
		Object jsParams = Utils.toJavascript(params);
		/**
		 * @j2sNative
		 * return soundManager.play(soundName, jsParams);
		 */{}
	}
	
	/**Seeeks to a given position within a sound, specified by miliseconds (1000 msec = 1 second.) Affects position property.
	 * Can only seek within loaded sound data, as defined by the duration property.
	 * @j2sNative
	 * return soundManager.setPosition(id, msecOffset);
	 */
	public void setPosition(String id, long msecOffset) {
		
	}
	
	
	/**Pauses the sound specified by ID. (Does not toggle.) Affects paused property (boolean.)
	 * @j2sNative
	 * return soundManager.pause(id);
	 */
	public void pause(String id) {
		
	}
	/**Pauses all sounds whose playState is >0. Affects paused property (boolean.)
	 * @j2sNative
	 * return soundManager.pauseAll(id);
	 */
	public void pauseAll() {
		
	}
	/**Resumes the currently-paused sound specified by ID.
	 * @j2sNative
	 * return soundManager.resume(id);
	 */
	public void resume(String id) {
		
	}
	/**Resumes all currently-paused sounds.
	 * @j2sNative
	 * return soundManager.resumeAll();
	 */
	public void resumeAll() {
		
	}
	/**Pauses/resumes play on the sound specified by ID.
	 * @j2sNative
	 * return soundManager.togglePause(id);
	 */
	public void togglePause(String id) {
		
	}
	/**Sets the volume of the sound specified by ID. Accepted values: 0-100. Affects volume property.
	 * @j2sNative
	 * return soundManager.setVolume(id, volume);
	 */
	public void setVolume(String id, int volume) {
		
	}
	/**Mutes the sound specified by ID. 
	 * @j2sNative
	 * return soundManager.mute(id);
	 */
	public void mute(String id) {
		
	}
	/** mutes all sounds
	 * @j2sNative
	 * return soundManager.mute();
	 */
	public void muteAll() {
		
	}
	/**unMutes the sound specified by ID. 
	 * @j2sNative
	 * return soundManager.unmute(id);
	 */
	public void unmute(String id) {
		
	}
	/** unmutes all sounds
	 * @j2sNative
	 * return soundManager.unmute();
	 */
	public void unmuteAll() {
		
	}
	/**Sets the stereo pan (left/right bias) of the sound specified by ID. 
	 * Accepted values: -100 to 100 (L/R, 0 = center.) Affects pan property.
	 * @j2sNative
	 * return soundManager.setPan(id, pan);
	 */
	public void setPan(String id, int pan) {
		
	}
	/**stops the sound specified by ID. 
	 * @j2sNative
	 * return soundManager.stop(id);
	 */
	public void stop(String id) {
		
	 }
	/** stops all sounds
	 * @j2sNative
	 * return soundManager.stop();
	 */
	public void stopAll() {
		
	}
	
	/** Returns a boolean indicating whether soundManager has attempted to and succeeded 
	 * in initialising. This function will return false if called before initialisation 
	 * has occurred.
	 * @j2sNative
	 * return soundManager.supported();
	 */
	public boolean supported() {
		return false;
	}
	
	/** Returns a boolean indicating whether soundManager can play the given URL. Playability is determined by a matching URL pattern set at runtime, based on Flash version and MPEG4 (MovieStar mode) support.
	 * @j2sNative
	 * return soundManager.canPlayURL(mediaUrl);
	 */
	public boolean canPlayURL(String mediaUrl) {
		return false;
	}
	/** Returns an SMSound object specified by ID, or null if there is a problem.
	 * @j2sNative
	 * return soundManager.getSoundById(id);
	 */
	private Object getSoundById_(String id) {
		return null;
	}
	public Sound getSoundById(String id) {
		return new Sound(getSoundById_(id));
	}

}
