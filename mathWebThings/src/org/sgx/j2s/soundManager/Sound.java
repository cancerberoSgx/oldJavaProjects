package org.sgx.j2s.soundManager;
/**
 * class wrapping SM2 SMSound objects
 * 
 * @author SGURIN
 *
 */
public class Sound {
	Object _sound;
	
public Sound(Object _sound) {
		super();
		this._sound = _sound;
	}

/**
 * @j2sNative
 * this._sound.play();
 */
	public void play(){}
	/**
	 * @j2sNative
	 * return this._sound.playState;
	 */
public int getPlayState() {
	// TODO Auto-generated method stub
	return 0;
}
/**
 * @j2sNative
 * this._sound.stop();
 */
	public void stop() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * @j2sNative
	 * this._sound.mute();
	 */
public void mute() {
	// TODO Auto-generated method stub
	
}
/**
 * @j2sNative
 * this._sound.unmute();
 */
	public void unmute() {
		// TODO Auto-generated method stub
		
	}
	/**Sets the volume of the given sound. Accepted values: 0-100. Affects volume property.
	 * @j2sNative
	 * this._sound.setVolume(i);
	 */
public void setVolume(int i) {
	// TODO Auto-generated method stub
	
}
/**
 * @j2sNative
 * return this._sound.volume;
 */
public int getVolume(){
	return 0;
}
/**
 * @j2sNative
 * this._sound.onfinish=r.run;
 */
public void onFinish(Runnable r) {
	
}
/**    Numeric value indicating a sound's current load status
    0 = uninitialised
    1 = loading
    2 = failed/error
    3 = loaded/success
 * @j2sNative
 * return this._sound.readyState;
 */
public int getReadyState() {
	// TODO Auto-generated method stub
	return 0;
}
/**
 * set position in miliseconds
 * @j2sNative
 * return this._sound.setPosition(i);
 */
public void setPosition(int i) {
	// TODO Auto-generated method stub
	
}
/**
 * gets position in miliseconds
 * @j2sNative
 * return this._sound.getPosition();
 */
public int getPosition() {
	return 0;
}
/**
 * gets position in miliseconds
 * @j2sNative
 * return this._sound.isBuffering;
 */
public boolean isBuffering() {
	// TODO Auto-generated method stub
	return false;
}
}
