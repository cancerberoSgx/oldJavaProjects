package org.sgx.swt.mygrid;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * simple matrix of string editor
 * matrix is trpresented with: String[row][column]
 * @author sg
 *
 */
public class CopyOfMyGrid extends Composite{

	
	private String[][] gdata;
	private Text[][] editor;
	private int columns;
	private int rows;

	public CopyOfMyGrid(Composite parent, int style, String[][]gdata) {
		super(parent, style);
		this.gdata=gdata;
		init();
	}

	private void init() {
		if(gdata==null)
			throw new RuntimeException("invalid data: "+gdata);		
		rows = gdata.length;
		if(rows!=0) 
			columns = gdata[0].length;		
		else
			columns = 0;
		this.editor = new Text[rows][columns];
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = columns;
		for(int i=0; i<rows; i++) {
			for(int j=0; j<columns; j++) {
				Text t = new Text(this, SWT.NONE);
				editor[i][j]=t;
				t.setText((gdata[i][j]==null?"":gdata[i][j])+"");
				this.editor [i][j]=t;
			}
		}
		setLayout(gridLayout);
	}
	
	public String[][]getData() {
		String[][] current = new String[rows][columns];
		for(int i=0; i<rows; i++) {
			for(int j=0; j<columns; j++) {
				current[i][j]=editor[i][j].getText();
			}
		}
		return current;
	}
}
