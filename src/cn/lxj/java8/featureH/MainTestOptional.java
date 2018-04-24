package cn.lxj.java8.featureH;

import java.util.Optional;

/**
 * MainTastOptional
 * description Optional 不是函数是接口，这是个用来防止NullPointerException异常的辅助类型
 * create by lxj 2018/4/24
 **/
public class MainTestOptional {
    public static void main(String[] args) {
        Optional<String> optional = Optional.of("bam");
        optional.isPresent();           // true
        optional.get();                 // "bam"
        optional.orElse("fallback");    // "bam"
        optional.ifPresent((s) -> System.out.println(s.charAt(0)));     // "b"
    }
}
