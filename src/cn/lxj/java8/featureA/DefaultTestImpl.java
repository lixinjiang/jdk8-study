package cn.lxj.java8.featureA;

/**
 * DefaultTestImpl  接口中静态方法不会被实现和继承，default方法可以实现也可以不用实现
 * description TODO
 * create by lxj 2018/4/23
 **/
public class DefaultTestImpl implements DefalutTest {
    @Override
    public int sub(int a, int b) {
        return a - b;
    }
}
