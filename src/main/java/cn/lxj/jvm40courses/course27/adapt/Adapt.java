package cn.lxj.jvm40courses.course27.adapt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Adapt
 * description 生成Aapt注解及其处理器
 * 该注解将接收一个Class类型的参数value（如果注解类仅包含一个名为value的参数的时候，那么在使用注解的时候，我们可以省略value=）
 * create class by lxj 2018/11/2
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface Adapt {
    Class<?> value();
}
