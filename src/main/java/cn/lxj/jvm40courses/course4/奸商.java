package cn.lxj.jvm40courses.course4;

import java.util.Random;

/**
 * 奸商
 * description TODO
 * create class by lxj 2018/10/26
 **/
public class 奸商 extends 商户{
    //public static int STATISVALUE = 2;
    @Override
    public double 折后价格(double 原价, 客户 某客户) {
        if (某客户.isVIP()) {              // invokeinterface  调用接口方法
            return 原价 * 价格歧视();      // invokestatic     调用静态方法
        }else{
            return super.折后价格(原价,某客户);// invokespecial调用私有实例方法
        }
    }

    public static double 价格歧视(){
        return new Random()     // invokespecial调用私有实例方法、构造器，以及使用super关键字调用父类的实例方法或构造器，和所实现接口的默认方法。
                .nextDouble()    // invokevirtual调用非私有实例方法
                + 0.8d;
    }

    public static void main(String[] args) {
        int statisvalue = 奸商.STATISVALUE;
        System.out.println(statisvalue);
    }
}
