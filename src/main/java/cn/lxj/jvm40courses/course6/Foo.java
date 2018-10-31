package cn.lxj.jvm40courses.course6;

/**
 * Foo
 * description TODO
 * create class by lxj 2018/10/29
 **/
public class Foo {
    private int tryBlock;
    private int catchBlock;
    private int finallyBlock;
    private int methodExit;

    public void test() {
        try {
            tryBlock = 0;
        } catch (Exception e) {
            catchBlock = 1;
        } finally {
            finallyBlock = 2;
        }
        methodExit = 3;
    }
}