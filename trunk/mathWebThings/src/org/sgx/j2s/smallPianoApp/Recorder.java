package org.sgx.j2s.smallPianoApp;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Text;
import org.sgx.j2s.soundManager.Sound;
import org.sgx.j2s.utils.AbstractRunnable;
import org.sgx.j2s.utils.Utils;
import org.stringtree.json.JSONReader;
import org.stringtree.json.JSONWriter;
/**
 * 
 * @author sgurin
 *
 */
public class Recorder {
	
	Map<Long, Note> record;
	Map<String, Long> keyInitialTimes;
	KeyListener keyListener;
//	boolean recording;
	private Map<String, Sound> currentInstrument;
	private String currentInstrumentName;
	
	public Recorder(){
		init();
//		for(String key : keys) {
//			keyInitialTimes.put(key, new Long(0));
//		}
		keyListener = new KeyListener() {
//			@Override
			public void keyPressed(KeyEvent arg0) {
				String key = arg0.character+"";				
				keyInitialTimes.put(key, System.currentTimeMillis());
			}
//			@Override
			public void keyReleased(KeyEvent arg0) {
				String key = arg0.character+"";				
				Long time = keyInitialTimes.get(key);
				keyInitialTimes.put(key, 0l);
				long t = time.longValue();
				if(time!=null && t!=0) {
					long now = System.currentTimeMillis();
					Note n = Options.keyNotes.get(key);
					if(n!=null) {
						n=n.clone();
						n.setTime(time);
						n.setDuration((int) (now-t));
						record.put(time, n);
						System.out.println("note recording :"+n);
					}
				}
			}
		};
	}
	private void init() {
		record=new HashMap<Long, Note>();
		keyInitialTimes=new HashMap<String, Long>();
}
	public void startRecording(Text target){
		init();
		target.addKeyListener(keyListener);
	}
	public String getCurrentInstrumentName() {
		return currentInstrumentName;
	}
	public void setCurrentInstrumentName(String currentInstrumentName) {
		this.currentInstrumentName = currentInstrumentName;
	}
	public void stopRecording(Text target){
//		System.out.println("stoprecording");
		target.removeKeyListener(keyListener);
//		System.out.println(Utils.pringMap(record));
	}
	public void playRecordedWith(String instrumentName) {
		this.currentInstrumentName = instrumentName;
		Vector<Long> v = getOrderedRecord();
		//comenzamos a tocar
		long t0 = v.get(0);
		for (int i = 0; i < v.size(); i++) {	
			Note n = record.get(v.get(i));
			long now = System.currentTimeMillis();
			long delay=(n.getTime()-t0);
			System.out.println("**** "+v.get(i)+" - "+delay);
			Utils.getInstance().installTimer(new AbstractRunnable<Note>(n){
//				@Override
				public void run() {
					Recorder.this.playNote(getData());					
				}				
			}, delay);
		}
	}
	private Vector<Long> getOrderedRecord() {
		Vector<Long> v = new Vector(record.keySet());
		Collections.sort(v, new Comparator<Long>(){
//			@Override
			public int compare(Long o1, Long o2) {
				return o1.floatValue()<o2.floatValue() ? -1 : 1;
			}			 
		});
		return v;
	}
	private void playNote(Note n) {
		Options.pressNote(currentInstrumentName, n);
		Utils.getInstance().installTimer(new AbstractRunnable<Note>(n){
//			@Override
			public void run() {
				Options.releaseNote(currentInstrumentName, getData());					
			}				
		}, n.getDuration());
	}
	public Map<Long, Note> getRecord() {
		return record;
	}
	public String saveCurrentRecord() {
		JSONWriter wr = new JSONWriter();
		return wr.write(record.values());
	}
	public void loadFrom(String s) throws Exception {
		try {
			record.clear();
			JSONReader r = new JSONReader();
			List<Map> noteList = (List) r.read(s);
			for(Map m : noteList){
				Note n=Note.buildFromMap(m);
				record.put(n.getTime(), n);
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
}
