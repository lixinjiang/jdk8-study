package cn.lxj.java8.featureH;

import cn.lxj.java8.common.Person;

import java.util.Comparator;

/**
 * MainTestComparator
 * description 测试Comparator接口
 * create by lxj 2018/4/24
 **/
public class MainTestComparator {
    public static void main(String[] args) {
        Person p1 = new Person("John", "Doe");
        Person p2 = new Person("Alice", "Wonderland");
        /*Comparator<Person> comparator = (p1,p2) -> p1.getFirstName().compareTo(p2.getFirstName());
        comparator.compare(p1, p2);             // > 0
        comparator.reversed().compare(p1, p2);  // < 0*/
    }
}
