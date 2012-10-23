package org.sgx.swing.gui.editors.sliders;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.sgx.swing.j2s.model.events.EditorEvent;


/**
 * un slider que simula un rango continuo y muestra la selección actual
 * Notificar� un Double o Float
 * @author sgx
 */
public class ContinuousSlider extends RealtimeSwingAbstractEditor<Double>
	implements ChangeListener {

	private static final long serialVersionUID = 1L;
	
	private static final int ticsCount = 1000;
	private static final int presitionInLabel = 2;
	
	double step, max, min, defaultValue;
	String title;
	private JSlider slider = null;
	private JPanel jPanel = null;
	private JLabel titleLabel = null;
	private JLabel value = null;

	public ContinuousSlider(/*String propId, */String title, double min, double max, double defaultValue) {
		if(max<=min || defaultValue>max || defaultValue<min) throw new RuntimeException("invalid initialization parameters");
		this.max=max;
		this.min=min;
		this.defaultValue=defaultValue;
		this.title=title;
		step = (max-min)/((double)ticsCount);
		initialize();		
	}

	private void initialize() {
		this.setSize(300, 200);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(getSlider(), null);
		this.add(getJPanel(), null);
	}

	private JSlider getSlider() {
		if (slider == null) {
			slider = new JSlider(JSlider.HORIZONTAL, 1, ticsCount, toInt(defaultValue));
			slider.setMinorTickSpacing(1);
			slider.setSnapToTicks(false);
			slider.setPaintTicks(false);
			slider.addChangeListener(this);
		}
		return slider;
	}	
	
	int toInt(double x) {	
		double T = new Double(ticsCount).intValue();
		return new Double((x-min)*T/(max-min)).intValue();
	}
	
	double toDouble(int x) {
		double T = new Double(ticsCount).intValue();
		return (((double)x)*(max-min)/T)+min;
	}

	private JPanel getJPanel() {
		if (jPanel == null) {
			value = new JLabel();
			value.setText(new Double(defaultValue).toString());
			titleLabel = new JLabel();
			titleLabel.setText(title+" = ");
			jPanel = new JPanel();
			jPanel.setLayout(new BoxLayout(getJPanel(), BoxLayout.X_AXIS));
			jPanel.add(titleLabel, null);
			jPanel.add(value, null);
		}
		return jPanel;
	}

	public void stateChanged(ChangeEvent e) {
	   JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()) {
        	double x = toDouble((int)source.getValue());
        	String [] a = new Double(x).toString().split("\\.");        	
        	String t;        	        	
        	if(a.length==2 && a[1].length()>presitionInLabel)
        		t=a[0]+"."+a[1].substring(0, presitionInLabel);
        	else 
    			t=new Double(x).toString();        	
        	value.setText(t);	
        	notifyAll(new EditorEvent<Double>(this, new Double(value.getText())));        	
        }	
	}

	public Double getCurrentValue() {
		return toDouble((int)slider.getValue());
	}
	public void setValue(Double value) {
		_notify=false;
		slider.setValue(toInt(value));
		_notify=true;
	}

}
