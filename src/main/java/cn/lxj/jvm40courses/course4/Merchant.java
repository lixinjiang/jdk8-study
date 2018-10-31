package cn.lxj.jvm40courses.course4;

/**
 * Merchant
 * description TODO
 * create class by lxj 2018/10/26
 **/
public class Merchant {
    public Number actionPrice(double price, Customer customer) {
        if (customer.isVIP()) return price * 0.8;
        else return price * 0.7;
    }
}
