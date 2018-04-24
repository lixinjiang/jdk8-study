package cn.lxj.java8.featureC;

public interface Converter<F, T> {
    T convert(F from);
}
