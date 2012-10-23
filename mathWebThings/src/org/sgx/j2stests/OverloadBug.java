package org.sgx.j2stests;

/** this is not a bug!!!!*/
public class OverloadBug {
	public int cons ;
	public OverloadBug(Object[][]value) {
		cons=2;
	}
	public OverloadBug(Object[] value) {
		cons=1;
	}
	private int getCons() {
		return 0;
	}
	public void setCons(int cons) {
		this.cons = cons;
	}
	public void method1(Object[][]val){
		cons=2;
	}
	public void method1(Object[]val){
		cons=1;
	}
	
	
	public static void main(String[] args) {
		String[][] s2 = new String[][]{{"1,1", "1,2", "1,3"}, {"2,1", "2,2", "2,3"}};
		if(!(s2 instanceof String[][]))
			System.out.println("!(s2 instanceof String[][])");
		
		OverloadBug w2 = new OverloadBug(s2);
		if(w2.getCons()!=2)
			System.out.println("wrong overloaded constructor invoked!");
		w2.setCons(0);
		w2.method1(new String[] {"uno", "two"});
		if(w2.getCons()!=1)
			System.out.println("wrong overloaded method invoked!");
		
		w2.setCons(0);
		w2.method1(new String[][] {{"uno", "two"}, {"hello", "world"}});
		if(w2.getCons()!=2)
			System.out.println("wrong overloaded method invoked!");
	}
	
}
