package cn.lxj.java8.featureH;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * MainTest
 * description Predicate接口测试
 * create by lxj 2018/4/24
 **/
public class MainTestPredicate {
    public static void main(String[] args) {
        Predicate<String> predicate = (s) -> s.length() > 0;
        System.out.println(predicate.test("foo"));  // true
        // 该方式是非的意思
        /*default Predicate<T> negate() {
            return (t) -> !test(t);
        }*/
        System.out.println(predicate.negate().test("foo"));// false
        System.out.println("华丽非分隔符************************");
        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;
        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();
        System.out.println(nonNull.test(null));
        System.out.println(isNull.test(null));
        System.out.println(isEmpty.test("sss"));
        System.out.println(isNotEmpty.test(""));
        System.out.println("华丽的分隔符************************");
    }
}
