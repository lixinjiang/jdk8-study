package cn.lxj.java8.featureH;

import cn.lxj.java8.common.Person;

import java.util.function.Supplier;

/**
 * MainTestSupplier
 * description 测试Supplier接口
 * Supplier 接口返回一个任意范型的值，和Function接口不同的是该接口没有任何参数
 * create by lxj 2018/4/24
 **/
public class MainTestSupplier {
    public static void main(String[] args) {
        // 使用泛型
        Supplier<Person> personSupplier = Person::new;
        Person person = personSupplier.get();
        System.out.println(person);
    }
}
