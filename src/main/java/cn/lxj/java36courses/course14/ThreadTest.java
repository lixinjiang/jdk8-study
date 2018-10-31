package cn.lxj.java36courses.course14;

public class ThreadTest extends Thread {
	
	private Factory factory;
	
	public void run() {
		factory.methodA();
	}

	public Factory getFactory() {
		return factory;
	}

	public void setFactory(Factory factory) {
		this.factory = factory;
	}
	
}
