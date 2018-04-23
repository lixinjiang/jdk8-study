package cn.lxj.java8.featureOne;

/**
 * Main
 * description 结论：接口中的static方法不能被继承，也不能被实现类调用，只能被自身调用
 * create by lxj 2018/4/23
 **/
public class Main {
    public static void main(String[] args) {
        DefaultTestImpl dtl = new DefaultTestImpl();
        // 使用接口的static方法
        DefalutTest.staticMethod();

        /**
         * 现在我们创建一个子接口实现类对象，并调用对象中的default方法：
         * 结论1：default方法可以被子接口继承亦可被其实现类所调用
         * 结论2：default方法被继承时，可以被子接口覆写
         * 结论3：如果一个类实现了多个接口，且这些接口中无继承关系，这些接口中若有相同的（同名，同参数）的default方法，
         *        则接口实现类会报错，接口实现类必须通过特殊语法指定该实现类要实现那个接口的default方法
         *        特殊语法：<接口>.super.<方法名>([参数])
         */
        SubTestImpl stl = new SubTestImpl();
        stl.defaultMethod();

        /**
         * 测试示例,接口可以new出来
         * 接口可以调用默认方法，也可以在实现的时候调用接口内的方法
         */
        System.out.println("formulate test start:-------------------------------");
        Formula formula = new Formula() {
            @Override
            public double caculate(int a) {
                return sqrt(a * 100);
            }
        };
        System.out.println(formula.caculate(100));//
        System.out.println(formula.sqrt(16));

    }
}
