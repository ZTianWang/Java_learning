package oop;

public enum EnumEg{
	TEST1(1,0), TEST2(2,1), TEST3(3,2), TEST4(4,3), TEST5(5,4);
	
	private int num;
	private int index;
	
	//枚举类型构造方法
	private EnumEg(int n, int i) {
		// TODO Auto-generated constructor stub
		this.num = n;
		this.index = i;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return num+"-"+index;
	}
	
	//枚举类默认toString()方法只返回枚举常量名
	public String getString() {
		return super.toString();
	}

}
