package org.sgx.pickturemakeup.gui.editors;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.sgx.pickturemakeup.model.PropertyChangeNotifier;
import org.sgx.pickturemakeup.model.PropertyChangeObserver;


/**
 * un slider que simula un rango continuo y muestra la selección actual
 * Notificar� un Double o Float
 * @author sgx
 */
public class ContinuousSlider extends PropertyEditor 
	implements ChangeListener, PropertyChangeNotifier {

	private static final long serialVersionUID = 1L;
	
	private static final int ticsCount = 1000;
	private static final int presitionInLabel = 2;
	
	double step, max, min, defaultValue;
	String title;
	private JSlider slider = null;
	private JPanel jPanel = null;
	private JLabel titleLabel = null;
	private JLabel value = null;

	/**
	 * This is the default constructor
	 */
	public ContinuousSlider(String propId, String title, double min, double max, double defaultValue) {
		super(propId);
		if(max<=min || defaultValue>max || defaultValue<min) throw new RuntimeException("invalid initialization parameters");
//		if(toInt(max)<=toInt(min) || toInt(defaultValue)>toInt(max) || toInt(defaultValue)<toInt(min))
//				throw new RuntimeException("invalid initialization params 2 ");		
		this.max=max;
		this.min=min;
		this.defaultValue=defaultValue;
		this.title=title;
		step = (max-min)/((double)ticsCount);
		initialize();		
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(getSlider(), null);
		this.add(getJPanel(), null);
	}

	/**
	 * This method initializes slider	
	 * 	
	 * @return javax.swing.JSlider	
	 */
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
		return ((double)x)*(max-min)/T;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
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
        	//update label de current value
        	String [] a = new Double(x).toString().split("\\.");        	
        	String t;
        	        	
        	if(a.length==2 && a[1].length()>presitionInLabel)
        		t=a[0]+"."+a[1].substring(0, presitionInLabel);
        	else 
    			t=new Double(x).toString();
        	value.setText(t);
        	notifyAll(toDouble((int)source.getValue()));        		
        	
        }	
	}
}
