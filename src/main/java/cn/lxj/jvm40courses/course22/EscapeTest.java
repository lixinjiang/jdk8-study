package cn.lxj.jvm40courses.course22;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * EscapeTest
 * description 逃逸分析
 * create class by lxj 2018/11/2
 **/
public class EscapeTest {
    public static void forEach(ArrayList<Object> list, Consumer<Object> f) {
        for (Object obj : list) {
            f.accept(obj);
        }
    }

    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        for (int i = 0; i < 400_000_000; i++) {
            forEach(list, obj -> {
            });
        }
    }
}