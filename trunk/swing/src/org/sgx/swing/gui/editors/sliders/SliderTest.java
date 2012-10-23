package org.sgx.swing.gui.editors.sliders;

import javax.swing.JPanel;

import org.sgx.swing.j2s.model.events.EditorEvent;
import org.sgx.swing.j2s.model.events.EditorListener;
import org.sgx.swing.j2s.model.events.Event;
import org.sgx.swing.j2s.model.events.EventListener;
import org.sgx.swing.utils.SwingUtils;

public class SliderTest {

	
	public static void main(String[] args) {
		DiscreteSlider ds = new DiscreteSlider("title",-66,100,75);
		ContinuousSlider cs = new ContinuousSlider("title2",3,11,5);
		EventListener l = new EventListener() {
			public void notifyEvent(Event e) {
				if(e instanceof EditorEvent)
					System.out.println(((EditorEvent)e).getValue());
			}			
		};
		//or use the EditorListener helper
		ds.addEventListener(new EditorListener<Integer>(){
			@Override
			public void notifyValueChanged(Integer val) {
				System.out.println("discrete change to: "+val);
			}
		});
		cs.addEventListener(new EditorListener<Double>(){
			@Override
			public void notifyValueChanged(Double val) {
				System.out.println("continous change to: "+val);
			}
		});
		JPanel p = new JPanel();
		p.add(ds);
		p.add(cs);
		SwingUtils.showInFrame(p);
	}

}
