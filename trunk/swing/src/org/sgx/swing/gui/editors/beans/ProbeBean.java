package org.sgx.swing.gui.editors.beans;

public class ProbeBean {

	Object[] selectableItem;
	
	boolean boolItem;
	
	String strintItem;
	
	double numericItem;
	
	public ProbeBean() {}//default
	
	public ProbeBean(Object[] selectableItem, boolean boolItem, String strintItem, double numericItem) {
		super();
		this.selectableItem = selectableItem;
		this.boolItem = boolItem;
		this.strintItem = strintItem;
		this.numericItem = numericItem;
	}

	public boolean isBoolItem() {
		return boolItem;
	}

	public void setBoolItem(boolean boolItem) {
		this.boolItem = boolItem;
	}

	public double getNumericItem() {
		return numericItem;
	}

	public void setNumericItem(double numericItem) {
		this.numericItem = numericItem;
	}

	public Object[] getSelectableItem() {
		return selectableItem;
	}

	public void setSelectableItem(Object[] selectableItem) {
		this.selectableItem = selectableItem;
	}

	public String getStrintItem() {
		return strintItem;
	}

	public void setStrintItem(String strintItem) {
		this.strintItem = strintItem;
	}
	
}
