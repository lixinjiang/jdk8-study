package cn.lxj.java8.featureH;

import java.util.ArrayList;
import java.util.List;

/**
 * MainTestDistinct
 * description 去重方法
 * create by lxj 2018/4/24
 **/
public class MainTestDistinct {
    public static void main(String[] args) {
        List<Integer> collection = new ArrayList<Integer>();
        collection.add(14);
        collection.add(5);
        collection.add(43);
        collection.add(89);
        collection.add(64);
        collection.add(112);
        collection.add(55);
        collection.add(55);
        collection.add(58);
        collection.stream().distinct().forEach(System.out::println);
    }
}
