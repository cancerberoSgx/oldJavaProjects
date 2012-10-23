package org.sgx.j2s.smallPianoApp;

import java.util.Map;

import org.sgx.j2s.soundManager.Sound;
import org.sgx.j2s.soundManager.SoundManager;
import org.sgx.j2s.utils.JsUtils;
import org.sgx.j2s.utils.Utils;

public class Options {   
	public static final String RESOURCE_BASE_URL = "/webThings/static/res/";
	public static String[] keys = ("z,s,x,d,c,v,g,b,h,n,j,m"+"q,2,w,3,e,r,5,t,6,y,7,u").split(",");
	public static Map<String, Note> keyNotes = (Map)Utils.toMap(new Object[][]{
			{"z", new Note("C", false, 4)},
			{"s", new Note("D", true, 4)},
			{"x", new Note("D", false, 4)},
			{"d", new Note("E", true, 4)},
			{"c", new Note("E", false, 4)},			
			{"v", new Note("F", false, 4)},
			{"g", new Note("G", true, 4)},
			{"b", new Note("G", false, 4)},
			{"h", new Note("A", true, 4)},
			{"n", new Note("A", false, 4)},
			{"j", new Note("B", true, 4)},
			{"m", new Note("B", false, 4)},
			
			{"q", new Note("C", false, 5)},
			{"2", new Note("D", true, 5)},
			{"w", new Note("D", false, 5)},
			{"3", new Note("E", true, 5)},
			{"e", new Note("E", false, 5)},			
			{"r", new Note("F", false, 5)},
			{"5", new Note("G", true, 5)},
			{"t", new Note("G", false, 5)},
			{"6", new Note("A", true, 5)},
			{"y", new Note("A", false, 5)},
			{"7", new Note("B", true, 5)},
			{"u", new Note("B", false, 5)}
	});
	public static String getSoundUrl(String instrument, String key, boolean bemol, int octave) {
		return RESOURCE_BASE_URL+instrument+"/"+key.toUpperCase()+
			(bemol?"b":"")+octave+".mp3";
	}
	public static String getSoundUrl(String instrument, Note n) {
		return getSoundUrl(instrument, n.getName(), n.isBemol(), n.getOctave());
	}
	public static Sound getSoundFor(String key, String instrument){
		return getSoundFor(keyNotes.get(key), instrument);
	}
	
	public static Sound createSoundFor(Note n, String instrument) {
		return SoundManager.getInstance().createSound(getSoundIdFor(n, instrument),
				getSoundUrl(instrument, n));
	}
	
	public static Sound getSoundFor(Note n, String instrumentName) {
		String id = getSoundIdFor(n, instrumentName);
		
	
		return SoundManager.getInstance().getSoundById(id);
	}
	public static String getSoundIdFor(Note n, String instrumentName) {		
		return instrumentName+"-"+n.getName()+(n.isBemol()?"b":"")+n.getOctave();
	}
	public static void pressNote(String instrumentName,	Note n) {
		try{
			System.out.println("pressing note "+n);
			Sound s = getSoundFor(n, instrumentName);
			if(s!=null){				
				if(s.getPlayState()!=1 ){
					s.play();
				}
			}
			}catch (Exception e) {
				// TODO: handle exception
			}		
	}
	public static void releaseNote(String instrumentName,	Note n) {
		try{
			Sound s = getSoundFor(n, instrumentName);
			if(s!=null){
				s.stop 	();
				s.setPosition(200);
			}}catch (Exception e) {
				// TODO: handle exception
			}	
	}
	/**
	 * -1 on error
	 * 1 if is buffering
	 * 0 if note was played  
	 */
	public static int pressNote(Map<String, Sound> instrument,	String key) {
		try{
		Sound s = instrument.get(key);		
		if(s!=null){
			if(s.isBuffering()) {
				return 1;
			}
			if(s.getPlayState()!=1 ){
				s.play();
				return 0;
			}
		}
		else 
			return -1;
		}catch (Exception e) {
			return -1;
		}
		return 0;
	}
	public static void releaseNote(Map<String, Sound> instrument,
			String key) {
		try{
		Sound s = instrument.get(key);
		if(s!=null){			
			s.stop 	();
			s.setPosition(200);
		}}catch (Exception e) {
			// TODO: handle exception
		}
	}
}
