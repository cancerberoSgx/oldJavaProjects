package org.sgx.pickturemakeup.model;

public class Matrix {

	public int n, m;
	public float[][] data;
	
	public Matrix(int n, int m, float[][] data) {
		super();
		this.n = n;
		this.m = m;
		this.data = data;
	}
	
	public int getN() {
		return n;
	}

	public int getM() {
		return m;
	}

	public float[][] getData() {
		return data;
	}

	public static Matrix make(int n, int m, float val) {
		float[][] a = new float[n][m];
		for(int i =0;i<n; i++)
			for(int j =0;j<m; j++)
				a[i][j]=val;
		return new Matrix(n,m,a);
	}
	
	public String toString() {
		String s ="Matrix[";
		for(int i =0;i<n; i++) {
			s=s+"[";
			for(int j =0;j<m; j++) {
				s=s+","+data[i][j];
			}
			s=s+"], ";
		}
		return s+"]";
	}
	
	public float get(int i, int j) {
		return data[i][j];
	}
}
