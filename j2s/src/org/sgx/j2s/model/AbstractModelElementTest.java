package org.sgx.j2s.model;

import org.sgx.j2s.model.base.Color;
import org.sgx.j2s.model.events.Event;
import org.sgx.j2s.model.events.EventListener;
import org.sgx.j2s.model.events.PropertyChangeEvent;
import org.sgx.j2s.model.events.PropertyChangeListener;
import org.sgx.j2s.util.Map;
import org.sgx.j2s.util.impl.Utils;
/**
 * testing eclipse automatic generated getters and setters
 * @author nati
 *
 */ 
public class AbstractModelElementTest extends AbstractModelElement{

	int size; 
	Color color;
double p;
boolean aBool;
	public AbstractModelElementTest(int size, Color color, double p, boolean bool) {
	super();
	this.size = size;
	this.color = color;
	this.p = p;
	aBool = bool;
}

	public int getSize() {
	return size;
}

public void setSize(int size) {
	this.size = size;
	_afterSet("size");
}

public Color getColor() {
	return color;
}

public void setColor(Color color) {
	this.color = color;
	_afterSet("color");
}

public double getP() {
	return p;
}

public void setP(double p) {
	this.p = p;
	_afterSet("p");
}

public boolean isABool() {
	return aBool;
}

public void setABool(boolean bool) {
	aBool = bool;
	_afterSet("aBool");
}

	public static void main(String[] args) {
//		test1();

		System.out.println(" errors ? : "+Utils.printAssertsFailed());
	}

	public static int testCount=0;
	private static void test1() {
		AbstractModelElementTest o = new AbstractModelElementTest(12, Color.BLACK, 2.3, false);
		PropertyChangeListener el = new PropertyChangeListener() {
			public void notifyPropertyChange(PropertyChangeEvent e) {
				testCount++;
			}
		};
		o.addPropertyChangeListener("color",el);
		Utils.assertTrue(testCount==0, "msg1");
		o.set("color", Color.BLUE);
		Utils.assertTrue(testCount==1, "msg2");
		Utils.assertTrue(o.get("color").equals(o.getColor()), "msg3");
		Utils.assertTrue(o.get("color").equals( Color.BLUE), "msg4");
		o.setColor(Color.RED);
		Utils.assertTrue(testCount==2, "msg5 "+testCount);
		Utils.assertTrue(o.get("color").equals( Color.RED), "msg6");
//		System.out.println(o.get("notAProp"));
		Utils.assertTrue(o.getProperties().keys().size()==2, "msg7");
	}

	@Override
	public Map<String, Class> getProperties() {
		return	(Map)Utils.transformArrayToMap(new Object[][]
			      { {"color", Color.class}, {"size", Integer.class} }
			);
	}

}
