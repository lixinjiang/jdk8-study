package cn.lxj.java8.featureB;

import java.util.*;

/**
 * MainLambdaTest3
 * description
 * create by lxj 2018/4/24
 **/
public class MainLambdaTest3 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(50);
        list.add(18);
        list.add(6);
        list.add(99);
        list.add(32);
        System.out.println(list.toString() + "排序之前");
//        LambdaTest3 lt = Collections::sort;
        /*lt.sort(list, (a, b) -> {
            return a - b;
        })*//*;*/
//        System.out.println(list.toString() + "排序之后");
    }
}