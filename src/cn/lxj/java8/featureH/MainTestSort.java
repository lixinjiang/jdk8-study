package cn.lxj.java8.featureH;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * MainTestSort
 * description sort排序
 * 排序是一个中间操作，返回的是排序好后的Stream。
 * 如果你不指定一个自定义的Comparator则会使用默认排序。
 * create by lxj 2018/4/24
 **/
public class MainTestSort {
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
//        需要注意的是，排序只创建了一个排列好后的Stream，而不会影响原有的数据源，collection：
        collection.stream().sorted().filter((s) -> s.startsWith("1")).forEach(System.out::println);
    }
}
