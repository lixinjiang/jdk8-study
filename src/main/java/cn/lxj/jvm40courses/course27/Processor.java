package cn.lxj.jvm40courses.course27;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * Processor
 * description 自定义注解的处理器
 * create class by lxj 2018/11/2
 **/
public interface Processor {
    /**
     * 具体的初始化代码放在里面
     * @param processingEnvironment
     */
    void init(ProcessingEnvironment processingEnvironment);

    /**
     * 返回注解处理器锁支持的注解类型，这些注解类型只需要用字符串的形式表示即可
     * @return
     */
    Set<String> getSupportedAnnotationTypes();

    /**
     * 返回处理器所支持的版本
     * @return
     */
    SourceVersion getSupportedSourceVersion();

    /**
     * 最关键的注解处理方法
     * @param annotations
     * @param roundEv
     * @return
     */
    boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEv);
}
