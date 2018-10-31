package cn.lxj.jvm40courses.course6;

/**
 * Foo2
 * description 语法糖Superssed
 * create class by lxj 2018/10/29
 **/
public class Foo2 implements AutoCloseable {
    private final String name;

    public Foo2(String name) {
        this.name = name;
    }

    @Override
    public void close() throws Exception {
        throw new RuntimeException();
    }

    public static void main(String[] args) throws Exception {
        try (Foo2 foo1 = new Foo2("Foo1");
             Foo2 foo2 = new Foo2("Foo2");
             Foo2 foo3 = new Foo2("Foo3")) {
            throw new RuntimeException("Initial");
        }
    }
}
