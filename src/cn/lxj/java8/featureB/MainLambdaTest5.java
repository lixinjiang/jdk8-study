package cn.lxj.java8.featureB;

/**
 * MainLambdaTest5
 * description
 <函数式接口>  <变量名> = <类>::<new>
 //调用
 <变量名>.接口方法([实际参数...])
 * create by lxj 2018/4/24
 **/
public class MainLambdaTest5 {
    public static void main(String[] args) {
        LambdaTest5 lt = String::new; // 引用构造器方法
        System.out.println(lt.creatString(new char[]{'1','2','3','a'}));
    }
}