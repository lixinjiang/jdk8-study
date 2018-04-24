package cn.lxj.java8.featureA;

/**
 * 示例
 */
public interface Formula {
    double caculate(int a);

    /**
     * 默认方法
     * @param a
     * @return
     */
    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}

