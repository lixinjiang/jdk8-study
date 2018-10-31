package cn.lxj.java36courses.course14;

/**
 * Singleton1
 * description 改造后的单例模式
 * create by lxj 2018/8/1
 **/
public class Singleton1 {
    private static Singleton1 instance;

    private Singleton1(){}

    public static Singleton1 getInstance(){
        if (instance == null){
            instance = new Singleton1();
        }
        return instance;
    }
}
