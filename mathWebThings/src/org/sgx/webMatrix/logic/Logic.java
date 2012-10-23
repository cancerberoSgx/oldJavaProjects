package org.sgx.webMatrix.logic;

import org.sgx.math.Jama.EigenvalueDecomposition;
import org.sgx.math.Jama.Matrix;
import org.sgx.math.Jama.SingularValueDecomposition;

public class Logic {
	
	
	//programa con m�ltiples operaciones entre matrices (MatrixOperationPanel)
	/*what we do is:
	 * each operation will have to register its OperationBuilder. 
	 * the operation result will be displayed entirely in html
	 */
	public static Object [][] operations = {
		//{name, description}
		{"A + B", "A + B", new OperationBuilder() {
			@Override
			public void buildResults() {
				results.put("A + B", a.plus(b));
			}
		}},
		{"A * B", "A * B", new OperationBuilder() {
			@Override
			public void buildResults() {
				results.put("A * B", a.times(b));
			}
		}},
		{"Cholesky decomposition of A", "For a symmetric, positive \ndefinite matrix A, the Cholesky \n" +
				"decomposition is an lower \ntriangular matrix L so that A = L*L'.", 
				new OperationBuilder() {
			@Override
			public void buildResults() {
				results.put("L", a.chol().getL());
			}
		}},
		{"single value decomposition of A", 
			"For an m-by-n matrix A with m >= n, the \nsingular value decomposition is an m-by-n \n" +
			"orthogonal matrix U, an n-by-n diagonal \nmatrix S, and an n-by-n orthogonal \n" +
			"matrix V so that A = U*S*V'.", 
			new OperationBuilder() {
				@Override
				public void buildResults() {
					SingularValueDecomposition svd = a.svd();
					results.put("U", svd.getU());
					results.put("S", svd.getS());
					results.put("V", svd.getV());
				}
		}},
		{"Eigen value decomposition of A", 
			"If A is symmetric, then A = V*D*V' where the \neigenvalue matrix D is diagonal and the \n" +
			"eigenvector matrix V is orthogonal. " +
//			"I.e. A = V.times(D.times(V.transpose())) and \n" +
//			"V.times(V.transpose()) equals the identity matrix.\n\n" +
//			"If A is not symmetric, then the eigenvalue matrix D is block diagonal with the real \n" +
//			"eigenvalues in 1-by-1 blocks and any complex eigenvalues, lambda + i*mu, in 2-by-2 blocks, \n" +
//			"[lambda, mu; -mu, lambda].  The columns of V represent the eigenvectors in the sense that \n" +
//			"A*V = V*D, i.e. A.times(V) equals V.times(D).  The matrix V may be badly conditioned, or \n" +
//			"even singular, so the validity of the equation A = V*D*inverse(V) depends upon V.cond()." +
			"",
			new OperationBuilder() {
				@Override
				public void buildResults() {
					 EigenvalueDecomposition eig = a.eig();
					results.put("V", eig.getV());
					results.put("D", eig.getD());
				}
		}},
		   
		{"inverse of A", "inverse if A is square, pseudoinverse otherwise", 
			new OperationBuilder() {
			@Override
			public void buildResults() {
				results.put("A * B",a.inverse());
			}
		}},
		{"solve Ax = b", "solves X for the system Ax=B or \ngives a least squares solution \nif A is not square gives a least squares solution",
			new OperationBuilder() {
			@Override
			public void buildResults() {
				Matrix x = a.solve(b);
				 Matrix r = a.times(x).minus(b);
			      double rnorm = r.normInf();				
				results.put("X",x);
				results.put("residual norm, ||b - A x||",rnorm);
			}
		}},
		{"determinant of A", "",
			new OperationBuilder() {
			@Override
			public void buildResults() {
				results.put("det(A)",a.det());
			}
		}},
		{"rank of A", " effective numerical rank, obtained from SVD",
			new OperationBuilder() {
			@Override
			public void buildResults() {
				results.put("rank(A)",a.rank());
			}
		}},
		{"condition (2 norm) of A", "ratio of largest to smallest singular value.",
			new OperationBuilder() {
			@Override
			public void buildResults() {
				results.put("cond(A)",a.cond());
			}
		}},
		{"trace of A", " sum of the diagonal elements.",
			new OperationBuilder() {
			@Override
			public void buildResults() {
				results.put("trace(A)",a.trace());
			}
		}}
		
	};

	public static String getOperationDesc(int ind) {
		return (String) operations[ind][1];
	}
	public static String[] getOperationNames() {
		String[] names= new String[operations.length];
		for (int i = 0; i < operations.length; i++) {
			names[i]=(String) operations[i][0];
		}
		return names;
	}

	public static String getOperationName(int selectionIndex) {
		return getOperationNames() [selectionIndex];
	}
	public static OperationBuilder getOperationBuilderFor(String name) {
		for (int i = 0; i < operations.length; i++) {
			String n=(String) operations[i][0];
			if(n.equals(name))
				return (OperationBuilder)operations[i][2];
		}
		return null;
	}
	
	
	public static 	int maximDigitLength = 4;
public static String matrixToHtml(String[][]M) {
	if(M==null)
		throw new RuntimeException("invalid arg");	

	String s="<table style=\"" +
			"border-left:1px black solid;border-right:1px black solid;" +
			"font-size: �13px;"+
			"\">";

	int[] dims = getDimensionsOf(M);
	int rows=dims[0], columns=dims[1];
	
	for (int i = 0; i < rows; i++) {
		s+="<tr>";
		for (int j = 0; j < columns; j++) {
			String entry = 
				M[i][j].length()>maximDigitLength ? 
						(M[i][j].substring(0,maximDigitLength)) : 
							(M[i][j]);
			s+="<td>"+entry+"</td>";
		}
		s+="</tr>";
	}
	
	return s+"</table>";
}

private static int[] getDimensionsOf(String[][] M) {
	int columns, rows;
	if(M==null)
		throw new RuntimeException("invalid argument null");		
	rows = M.length;
	if(rows!=0) 
		columns = M[0].length;		
	else
		columns = 0;
	
	return new int[]{rows, columns};
}
private static int[] getDimensionsOf(double[][] M) {
int columns, rows;
if(M==null)
	return null;//	throw new RuntimeException("invalid argument null");		
rows = M.length;
if(rows!=0) 
	columns = M[0].length;		
else
	columns = 0;
return new int[]{rows, columns};
}



public static double[][] toDoubleMatrix(String[][] s) {
	if(s==null)
		return null;
	int[] dims = getDimensionsOf(s);
	int rows=dims[0], columns=dims[1];
	double[][] D=new double[rows][columns];
	for(int i=0; i<rows; i++) {
		for (int j = 0; j < columns; j++) {
			try {
				D[i][j]=Double.parseDouble(s[i][j]);
			} catch (Exception e) {
				throw new RuntimeException("problem parsing numeric value ("+i+","+j+"): "+s[i][j]);
			}
		}
	}
	return D;
}

public static Matrix toMatrix(double[][]M) {
	if(M==null)
		return null;
	int[] dims = getDimensionsOf(M);
	
	int rows=dims[0], columns=dims[1];
	Matrix m = new Matrix(rows, columns);
	for (int i = 0; i < rows; i++) {
		for (int j = 0; j < columns; j++) {
			m.set(i, j, M[i][j]);
		}
	}
	return m;
}
public static String[][] toStringMatrix(double[][] M) {
	if(M==null)
		throw new RuntimeException("invalid arg null");
	int[] dims = getDimensionsOf(M);
	int rows=dims[0], columns=dims[1];
	String[][] S=new String[rows][columns];
	for(int i=0; i<rows; i++) {
		for (int j = 0; j < columns; j++) {
			try {
				S[i][j]=M[i][j]+"";
			} catch (Exception e) {
				throw new RuntimeException("problem parsing numeric value ("+i+","+j+"): "+M[i][j]);
			}
		}
	}
	return S;
}

/**
 * Solve a linear system A x = b and compute the residual norm, ||b - A x||.
 * @return if a sollution exists returns an array with two elements: [0] = X (result matrix) [1] the residual norm
 * if the solution cannot be computed (i.e: singular matrix) return the first field of the array as null and the second field with a error message
 */
public static Object[] solveSystem(double[][] a, double[][] b) {
     Matrix A = toMatrix(a);
     Matrix B = toMatrix(b);
	
     Matrix x=null;
     try {
    	 x = A.solve(B);
     } catch (Exception e) {
    	 return new Object[]{null, e.getMessage()};
	}
//     Matrix r = A.times(x).minus(B);
//     double rnorm = r.normInf();
     return new Object[]{x.getArray(), 0};
}


public static String matrixToHtmlTable(double[][] M) {
	return matrixToHtml(toStringMatrix(M));
}

public static String[][] toStringArray(Matrix v) {
	String[][] M = new String[v.getRowDimension()][v.getColumnDimension()];
	for (int i = 0; i < v.getRowDimension(); i++) {
		for (int j = 0; j < v.getColumnDimension(); j++) {
			M[i][j]=v.get(i,j)+"";
		}
	}
	return M;
}




}
