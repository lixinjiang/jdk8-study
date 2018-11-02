package cn.lxj.jvm40courses.course22;

/**
 * FooV1
 * description TODO
 * create class by lxj 2018/11/2
 **/
public class FooV1 {
    public static int indexOf(String str) {
        int i = str.indexOf(",");
        if (i == 0) {
            return 0;
        } else {
            return 1;
        }
    }
}
