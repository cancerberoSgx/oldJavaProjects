package org.sgx.j2s.smallPianoApp;

import java.util.Map;

import org.stringtree.json.JSONReader;

/**
 * simple note format
 * @author sg
 *
 */
public class Note {

	String name;
	boolean bemol;
	int octave;
	long time;
	int duration;

	public Note(String name, boolean bemol, int octave) {
		this(name, bemol, octave, 0, 0);		
	}

	public Note(String name, boolean bemol, int octave, long time, int duration) {
		super();
		this.name = name;
		this.bemol = bemol;
		this.octave = octave;
		this.time = time;
		this.duration = duration;
	}

	public boolean isBemol() {
		return bemol;
	}
	public int getOctave() {
		return octave;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public void setKey(String key) {
		this.name = key;
	}
	public void setBemol(boolean bemol) {
		this.bemol = bemol;
	}
	public void setOctave(int octave) {
		this.octave = octave;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean equalFrec(Note n) {
		return octave==n.getOctave() && n.isBemol()==bemol && name.equals(n.getName());
	}
	public Note clone() {
		return new Note(name, bemol, octave, time, duration);
	}
	public String toString(){
		return name+(bemol?"b":"")+octave;
	}
	public String toJSON(){
		return "{'name':"+name+",'bemol':"+bemol+
		",'octave':"+octave+",'time':"+time+",'duration':"+duration+"}";
	}
	public static Note fromJSON(String json) {
		try{
		JSONReader r = new JSONReader();
		Map m = (Map) r.read(json);
		return buildFromMap(m);
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	public static Note buildFromMap(Map m) {
		return new Note((String)m.get("name"),(Boolean)m.get("bemol") ,((Long)m.get("octave")).intValue() ,
				(Long)m.get("time") ,((Long)m.get("duration")).intValue() );
	}
}
