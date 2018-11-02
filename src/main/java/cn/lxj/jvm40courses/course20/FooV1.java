package cn.lxj.jvm40courses.course20;

/**
 * FooV1
 * description
 * create class by lxj 2018/11/2
 **/
public class FooV1 {
    // 方法内联的过程
    public static boolean flag = true;
    public static int value0 = 0;
    public static int value1 = 1;

    /**
     * 用来接收一个int类型的参数
     * @param value
     * @return
     */
    public static int foo(int value) {
        int result = bar(flag);
        if (result != 0) {
            return result;
        } else {
            return value;
        }
    }

    /**
     * bar方法接收一个boolean类型的参数。
     * @param flag
     * @return
     */
    public static int bar(boolean flag) {
        return flag ? value0 :value1;
    }
}