package cn.lxj.java8.featureC;

/**
 * MainConverterTest
 * description TODO
 * create by lxj 2018/4/24
 **/
public class MainConverterTest {
    public static void main(String[] args) {
        Converter<String,Integer> converter = (from) -> Integer.valueOf(from);
        Integer converted = converter.convert("123");
        System.out.println(converted);
    }
}
