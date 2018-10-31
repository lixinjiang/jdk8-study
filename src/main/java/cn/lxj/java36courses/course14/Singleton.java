package cn.lxj.java36courses.course14;

/**
 * Singleton
 * description 单例模式,由于java中会为没有明确声明构造函数的类，定义一个public的无参数的构造函数，不能保证额外的对象被创造出来
 * create by lxj 2018/8/1
 **/
public class Singleton {
    private static Singleton instance = new Singleton();

    public static Singleton getInstance() {
        return instance;
    }

}
