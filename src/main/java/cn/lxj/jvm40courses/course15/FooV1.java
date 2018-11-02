package cn.lxj.jvm40courses.course15;

import cn.lxj.jvm40courses.course4.Customer;
import cn.lxj.jvm40courses.course4.VIP;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * FooV1
 * description TODO
 * create class by lxj 2018/11/1
 **/
public class FooV1 {
    public int foo1() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        int result = list.get(0);
        // test
//        Integer.valueOf(1);
        return result;
    }

    public void test(){
        VIP vip = new VIP();
        Customer customer = new VIP();
        //new VIPOnlyMerchant().actionPrice(customer); // 报错
        new VIPOnlyMerchant().actionPrice(vip);
    }

    public void foo2(int[] array){
        for (int item : array) {
        }
    }
    // 等同于
    public void foo3(int[] array){
        int[] myArray = array;
        int length = myArray.length;
        for (int i = 0; i < length; i++) {
            int item = myArray[i];
        }
    }

    public void foo(ArrayList<Integer> list) {
        for (Integer item : list) {
        }
    }
    // 等同于
    public void bar(ArrayList<Integer> list) {
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer item = iterator.next();
        }
    }

}
class GenericTest <T extends Number>{
    T foo(T t) {
        return t;
    }
}

class Merchant<T extends Customer> {
    public double actionPrice(T customer) {
        return 0.0d;
    }
}

class VIPOnlyMerchant extends Merchant<VIP> {
    @Override
    public double actionPrice(VIP customer) {
        return 0.0d;
    }
}

