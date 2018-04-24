package cn.lxj.java8.featureA;

/**
 * SubTestImpl
 * description SubTest子接口的实现类
 * create by lxj 2018/4/23
 **/
public class SubTestImpl implements SubTest {

    @Override
    public int sub(int a, int b) {
        return a - b;
    }

    /**
     * 重写default方法
     * 如果一个类实现了多个接口，且这些接口中无继承关系，这些接口中若有相同的（同名，同参数）的default方法，
     *        则接口实现类会报错，接口实现类必须通过特殊语法指定该实现类要实现那个接口的default方法
     * 特殊语法：<接口>.super.<方法名>([参数])
     */
    @Override
    public void defaultMethod() {
        // Defautl.super.defaultMethod();
        System.out.println("SubTest defalut 方法");
    }
}
