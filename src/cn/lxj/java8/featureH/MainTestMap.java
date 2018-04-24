package cn.lxj.java8.featureH;

import java.util.ArrayList;
import java.util.List;

/**
 * MainTestMap
 * description 比如mapToInt就是把原始Stream转换成一个新的Stream，
 * 这个新生成的Stream中的元素都是int类型。之所以会有这样三个变种方法，可以免除自动装箱/拆箱的额外消耗；
 * create by lxj 2018/4/24
 **/
public class MainTestMap {
    public static void main(String[] args) {
        List<String> collection = new ArrayList<String>();
        collection.add("14");
        collection.add("5");
        collection.add("43");
        collection.add("89");
        collection.add("64");
        collection.add("112");
        collection.add("55");
        collection.add("55");
        collection.add("58");
        // 将String转化为Integer类型
        collection.stream().mapToInt(Integer::valueOf).forEach(System.out::println);
        // 或
        collection.stream().mapToInt(a -> Integer.parseInt(a)).forEach(System.out::println);
    }

}
