package cn.lxj.java36courses.course9;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LinkedHashMapSample
 * description java核心讲义36讲
 * create by lxj 2018/7/23
 **/
public class LinkedHashMapSample {
    public static void main(String[] args) {
        LinkedHashMap<String, String> accessOrderedMap = new LinkedHashMap<String, String>(16, 0.75F, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {// 实现自定义删除策略，否则行为和普遍map没有区别
                return size() > 3;
            }
        };
        accessOrderedMap.put("Project1", "Valhalla");
        accessOrderedMap.put("Project2", "Panama");
        accessOrderedMap.put("Project3", "Loom");
        accessOrderedMap.forEach((k, v) -> {
            System.out.println(k + ":" + v);
        });

        //模拟访问
        accessOrderedMap.get("Project1");
        accessOrderedMap.get("Project2");
        accessOrderedMap.get("Project3");
        System.out.println("Iterator over should be not affected:");
        accessOrderedMap.forEach((k, v) -> {
            System.out.println(k + ":" + v);

        });
        // 触发删除
        accessOrderedMap.put("Project4:", "Mission Control1");
        accessOrderedMap.put("Project5:", "Mission Control2");
        System.out.println("Oldest entry should be removed:");
        accessOrderedMap.forEach((k, v) -> {// 遍历顺序不变
            System.out.println(k + ":" + v);
        });
    }
}