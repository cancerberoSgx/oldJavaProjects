package org.sgx.pickturemakeup.model;

/**
 * @author SGURIN
 */
public class MatrixProperty  extends Property{
	public static final int INTEGER_TYPE = 1;
	public static final int FLOAT_TYPE = 2;
	
	int type;
	
	Matrix M;	
	
	public MatrixProperty(String id, String description, 
			int type, int n, int m) {
		super(id, description);
		this.type = type;
		M=Matrix.make(n, m, 0.0f);
		defaultValue=M;
	}
//
//	Object[][] getDefaultModelFor() {
//		Object[][] o = new Object[n][m];
//		if(type==MatrixProperty.FLOAT_TYPE) 		
//			for(int i=0;i<n;i++)
//				for(int j=0;j<m;j++)
//					o[i][j]=new Float("0.0f");
//		
//		else if(type==MatrixProperty.INTEGER_TYPE)
//			for(int i=0;i<n;i++)
//				for(int j=0;j<m;j++)
//					o[i][j]=new Integer("0");
//		else throw new RuntimeException("type not supported");
//		return o;
//	}
	


	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	public float[][]getDataFloat() {
		return M.getData();
	}

	public int getN() {
		return M.n;	
	}
	public int getM() {
		return M.m;	
	}
	
	public void setMatrix(Matrix m) {
		this.M=m;
	}
	
	public Matrix getMatrix() {
		return M;
	}
}