package cn.lxj.java36courses.course14;

public class Factory {

	private volatile static Factory instance;

	private Factory() {
		super();
	}

	public static Factory getInstance() {
		if (instance == null) {
			synchronized (Factory.class) {
				if (instance == null) {
					instance = new Factory();
				}
			}
		}
		return instance;
	}

	public void methodA() {
		try {
			System.out.println("enter"+Thread.currentThread().getName());
			Thread.sleep(5000);
			System.out.println("exit"+Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
