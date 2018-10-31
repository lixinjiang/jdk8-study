package cn.lxj.java36courses.course14;

public class Test {

	public static void main(String[] args) {
		Factory factory = Factory.getInstance();

		for (int i = 0; i < 100; i++) {
			ThreadTest test = new ThreadTest();
			test.setFactory(factory);
			test.start();
		}
	}
}
