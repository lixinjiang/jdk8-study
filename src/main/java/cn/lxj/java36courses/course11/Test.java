package cn.lxj.java36courses.course11;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * TestV0
 * description
 * create by lxj 2018/7/25
 **/
public class Test {
    public static void main(String[] args) {
        // 获取线程安全的HashMap,效率低下
        Map<String, Object> kvMap = Collections.synchronizedMap(new HashMap<String, Object>());
    }
}
