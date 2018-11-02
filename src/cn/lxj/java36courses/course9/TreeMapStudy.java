package cn.lxj.java36courses.course9;

import cn.lxj.java8.common.Person;

import java.util.*;

/**
 * TreeMapStudy
 * description TODO
 * create by lxj 2018/7/25
 **/
public class TreeMapStudy {
    public static void main(String[] args) {
        System.out.println("BEGAIN===>>>");
        Person person1 = new Person("马先生", 220180);
        Person person2 = new Person("李先生", 220193);
        Person person3 = new Person("王小姐", 220186);

        //HashMap
        Map<Number, Person> map = new HashMap<>();
        map.put(person1.getLastCNM(), person1);
        map.put(person2.getLastCNM(), person2);
        map.put(person3.getLastCNM(), person3);

        System.out.println("HashMap，无序===》》》");
        for (Iterator<Number> it = map.keySet().iterator(); it.hasNext(); ) {
            Person person = map.get(it.next());
            System.out.println(person.getLastCNM() + "---" + person.getName());
        }

        //TreeMap
        System.out.println("TreeMap，升序===》》》");
        TreeMap<Number, Person> treeMap = new TreeMap<Number, Person>();
        treeMap.putAll(map);
        for (Iterator<Number> it = treeMap.keySet().iterator(); it.hasNext(); ) {
            Person person = treeMap.get(it.next());
            System.out.println(person.getLastCNM() + "---" + person.getName());
        }

        System.out.println("TreeMap，降序===》》》");
        TreeMap<Number, Person> treeMap2 = new TreeMap<>(Collections.reverseOrder());
        treeMap2.putAll(map);
        for (Iterator<Number> it = treeMap2.keySet().iterator(); it.hasNext(); ) {
            Person person = treeMap2.get(it.next());
            System.out.println(person.getLastCNM() + "---" + person.getName());
        }

        System.out.println("<<<===END");
    }
}