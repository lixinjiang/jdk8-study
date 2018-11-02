package cn.lxj.jvm40courses.course20;

/**
 * BinaryOp
 * description
 * create class by lxj 2018/11/2
 **/
public abstract class BinaryOp {
    public abstract int apply(int a, int b);
}

class Add extends BinaryOp {
    @Override
    public int apply(int a, int b) {
        return a + b;
    }
}

class Sub extends BinaryOp {
    @Override
    public int apply(int a, int b) {
        return a - b;
    }
}