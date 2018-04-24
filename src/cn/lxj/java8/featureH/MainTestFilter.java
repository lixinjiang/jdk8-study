package cn.lxj.java8.featureH;

import java.util.ArrayList;
import java.util.List;

/**
 * MainTestFilter
 * description Filter过滤方法
 * 过滤通过一个predicate接口来过滤并只保留符合条件的元素，该操作属于中间操作。
 * create by lxj 2018/4/24
 **/
public class MainTestFilter {
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
        long count = collection.stream().filter(num -> num != null).filter(num -> num.intValue() > 50).count();
        System.out.println(count);
    }
}
