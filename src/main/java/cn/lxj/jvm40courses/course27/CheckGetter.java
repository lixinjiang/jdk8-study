package cn.lxj.jvm40courses.course27;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * CheckGetter
 * description
 * 定义了一个简单的注解CheckGetter
 * 既可以用来标注类，也可以用来标注字段，和Overrider生命周期一样，被限制在源代码中
 * create class by lxj 2018/11/2
 **/
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
public @interface CheckGetter {
}
