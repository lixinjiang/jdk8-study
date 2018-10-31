package cn.lxj.java36courses.course13;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;

/**
 * TestV0
 * description 测试
 * create by lxj 2018/7/26
 **/
public class Test {
    public static void main(String[] args) {
//        Collections
//        Cloneable
//        Serializable
        BigDecimal bg1 = new BigDecimal("2.0010");
        BigDecimal bg2 = new BigDecimal("3.0261");
        BigDecimal bg3 = bg1.multiply(bg1);

        System.out.println(BigDecimal.ROUND_HALF_UP);
        System.out.println(bg2.setScale(2, BigDecimal.ROUND_HALF_UP));
        System.out.println(bg2.setScale(2, BigDecimal.ROUND_HALF_DOWN));
    }
}
