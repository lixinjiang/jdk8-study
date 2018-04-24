package cn.lxj.java8.featureB;

/**
 * MainLambdaTest4
 * description TODO
 * create by lxj 2018/4/24
 **/
public class MainLambdaTest4 {

    public static void main(String[] args) {
        LambdaTest4 lt = LambdaClassTest::add;
        LambdaClassTest lct = new LambdaClassTest();
        System.out.println(lt.add(lct, 5, 8));
    }

}
