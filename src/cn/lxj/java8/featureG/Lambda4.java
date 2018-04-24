package cn.lxj.java8.featureG;

import cn.lxj.java8.featureC.Converter;

/**
 * Lambda4
 * description  lambda内部对于实例的字段以及静态变量是既可读又可写。
 *              该行为和匿名对象是一致的；
 * create by lxj 2018/4/24
 **/
public class Lambda4 {
    static int outerStaticNum;
    int outerNum;

    void testScopes() {
        Converter<Integer, String> stringConverter1 = (from) -> {
            outerNum = 23;
            return String.valueOf(from);
        };
        Converter<Integer, String> stringConverter2 = (from) -> {
            outerStaticNum = 72;
            return String.valueOf(from);
        };
    }
}
