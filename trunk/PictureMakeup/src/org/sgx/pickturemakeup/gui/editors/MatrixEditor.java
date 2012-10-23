package org.sgx.pickturemakeup.gui.editors;

import java.awt.Dimension;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.sgx.pickturemakeup.model.Matrix;
import org.sgx.pickturemakeup.model.MatrixProperty;
import org.sgx.pickturemakeup.model.PropertyChangeNotifier;
import org.sgx.pickturemakeup.model.PropertyChangeObserver;


/**
 * @author sgurin 
 */
public class MatrixEditor extends PropertyEditor	 {

	private static final long serialVersionUID = 1L;


	JTable table;

	int n,  m;

	JTextField nfield, mfield;

	int valueType;

	public MatrixEditor(String propId, int n, int m) {
		super(propId);
		if(n<1||m<1)
			throw new RuntimeException("invalid matrix size");
		this.n=n;
		this.m=m;
		init();
	}

	private void init() {		
		this.valueType=MatrixProperty.FLOAT_TYPE;		
		nfield=new JTextField();
		mfield=new JTextField();
		nfield.setText(new Integer(n).toString());
		mfield.setText(new Integer(m).toString());

		table=makeDefaultTable();

		//layouting
		Box b1 = Box.createHorizontalBox(), b2=Box.createHorizontalBox();
		b1.setMaximumSize(new Dimension(200,20));
		b2.setMaximumSize(new Dimension(200,20));
		b1.add(new JLabel("Number of columns: "));
		b1.add(nfield);
		b2.add(new JLabel("Number of rows: "));
		b2.add(mfield);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(b1);
		this.add(b2);
		this.add(table);

		//events
		nfield.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyTyped(java.awt.event.KeyEvent e) {

				String input = nfield.getText()+e.getKeyChar();
			}
		});
		mfield.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyTyped(java.awt.event.KeyEvent e) {
				String input = nfield.getText()+e.getKeyChar();

			}
		});
	}

	/**
	 * 
	 * @return 
	 * if(valueType==FLOAT_TYPE) return Float[][]
	 * if(valueType==integer_TYPE) return Integer[][]
	 * @throws BadFormatException
	 */
	Object [][] getMatrix() throws BadFormatException {
		javax.swing.table.TableModel model = table.getModel();
		Object [][] a = new Object[n][m];
		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++) {
				Object o = model.getValueAt(i, j);
				if(valueType==MatrixProperty.FLOAT_TYPE) {
					try {
						a[i][j]=new Float(o.toString());
					} catch(Exception e) {
						throw new BadFormatException("cant parse "+o+" to float");
					}
				}				
				else if(valueType==MatrixProperty.INTEGER_TYPE){
					try {
						a[i][j]=new Integer(o.toString());
					} catch(Exception e) {
						throw new BadFormatException("cant parse "+o+" to integer");
					}
				}
				else
					throw new RuntimeException("not supported valuetype");
			}
		}      
		return a;
	}

	JTable makeDefaultTable() {
		Object[][] model = getDefaultModel();
		String []columns = getEmptyColums();

		JTable table = new JTable(model, columns);
		table.getCellEditor(1,1).addCellEditorListener(new CellEditorListener() {

			public void editingStopped(ChangeEvent e) {
				System.out.println("table edited");
				Object[][] a=null;
				try {
					a = getMatrix();
					String s="[";
					for(int i =0;i<n; i++) {
						s=s+"[";
						for(int j =0;j<m; j++) {
							s=s+","+a[i][j];
						}
						s=s+"], ";
					}
					System.out.println("notifying: "+s+"]");

				} catch(BadFormatException ex) {
					JOptionPane.showMessageDialog(MatrixEditor.this, "Invalid number format");
				}
				if(a!=null) {
					Matrix matrix = new Matrix(n, m, getDataFloatFor(n,m,a,valueType));
					MatrixEditor.this.notifyAll(matrix);
				}
			}

			public void editingCanceled(ChangeEvent e) {
				System.out.println("table editing canceled");
			}

		});
		return table;
	}

	public float [][] getDataFloatFor(int n, int m, Object[][]data, int type) {
		float[][] X = new float[n][m];
		if(type==MatrixProperty.FLOAT_TYPE) 		
			for(int i=0;i<n;i++)
				for(int j=0;j<m;j++) {
					Object o = data[i][j];
					if(o instanceof Float) 
						X[i][j]=((Float)o).floatValue();
					else 
						throw new RuntimeException("invalid type value in "+i+","+j+" - "+o+". Must be a float");
				}		
		else if(type==MatrixProperty.INTEGER_TYPE)
			for(int i=0;i<n;i++)
				for(int j=0;j<m;j++) {
					Object o = data[i][j];
					if(o instanceof Integer) 
						X[i][j]=((Integer)o).floatValue();
					else 
						throw new RuntimeException("invalid type value in "+i+","+j+" - "+o+". Must be a integer");
				}
		else 
			throw new RuntimeException("type not supported");
		return X;
	}

	private String[] getEmptyColums() {
		String[] s = new String[n];
		for(int i=0;i<n;i++)
			s[i]="";
		return s;
	}

	Object[][] getDefaultModel() {
		Object[][] o = new String[n][m];
		if(valueType==MatrixProperty.FLOAT_TYPE) 		
			for(int i=0;i<n;i++)
				for(int j=0;j<m;j++)
					o[i][j]="0.0";

		else 
			for(int i=0;i<n;i++)
				for(int j=0;j<m;j++)
					o[i][j]="0";
		return o;
	}

	void debug (Object[][]model, Object[]columnNames) {
		System.out.println("model:");
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
				;

	}

	public static void main(String [] a) {
		JFrame f = new JFrame();
		MatrixEditor ed = new MatrixEditor("kk",4,5);
		f.getContentPane().add(ed);
		f.setSize(400,500);
		f.setVisible(true);
	}
}
