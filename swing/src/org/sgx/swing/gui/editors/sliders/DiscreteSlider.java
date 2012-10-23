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
 * @author sgx
 */
public class DiscreteSlider extends RealtimeSwingAbstractEditor <Integer>
	implements ChangeListener {

	private static final long serialVersionUID = 1L;
	
	int max, min, defaultValue;
	String title;
	private JSlider slider = null;
	private JPanel jPanel = null;
	private JLabel titleLabel = null;
	private JLabel value = null;
	


	public DiscreteSlider(String title, int min, int max, int defaultValue) {
		if(max<=min || defaultValue>max || defaultValue<min) throw new RuntimeException("invalid initialization parameters");
//		if(toInt(max)<=toInt(min) || toInt(defaultValue)>toInt(max) || toInt(defaultValue)<toInt(min))
//				throw new RuntimeException("invalid initialization params 2 ");		
		this.max=max;
		this.min=min;
		this.defaultValue=defaultValue;
		this.title=title;
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
			slider = new JSlider(JSlider.HORIZONTAL, min, max, defaultValue);
			slider.setMinorTickSpacing(1);
			slider.setSnapToTicks(true);
			slider.setPaintTicks(true);
			slider.addChangeListener(this);
		}
		return slider;
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
        	int x = (int)source.getValue();
        	value.setText(new Integer(x).toString());
        	notifyAll(new EditorEvent<Integer>(this, (int)slider.getValue()));        	
        }		
	}

	public Integer getCurrentValue() {
		return slider.getValue();
	}

	public void setValue(Integer value) {
		_notify=false;
		slider.setValue(value);
		_notify=true;
		// TODO Auto-generated method stub
		
	}

//	@Override
//	EditorEvent getEditorEventFor(Object val) {
//		return new EditorEvent<Integer>(this) {
//			@Override
//			public Integer getValue() {
//				return (int)slider.getValue();
//			}			
//		};
//	}

}
