package cn.lxj.data_structure.course03;

/**
 * FooV1
 * description
 * 大〇时间复杂度算法，成正比。
 * 并不能具体表示代码的真正执行时间，而是代码执行时间随着数据规模增长的趋势，也叫作渐进时间复杂度（asymptotic time complexity）
 * 当n非常大的时候，公式中的低阶、常量、系数三部分并不能左右增长趋势，所以都可以忽略，只需要记录一个最大量级就行。
 * create class by lxj 2018/11/5
 **/
public class FooV1 {

    // 假设每行代码的执行时间都不一样，但是我们这里只是粗略估计，所以可以假设每行代码的执行时间都一样，为unit_time。
    // 在这段代码的基础之上，这段代码的总执行时间是多少呢？
    // 总执行时间是T(N) = (2n + 2 ) * unit_time，可看出与代码的执行次数成正比

    /**
     * T(n) = 〇(n)
     *
     * @param n
     * @return
     */
    int calV1(int n) {
        int sum = 0;    // 一个unit_time
        int i = 1;      // 一个unit_time
        for (; i < n; i++) {    // n * unit_time
            sum += i;           // n * unit_time
        }
        return sum;
    }

    /**
     * T(n)=(2n*n + 2n + 3) * unit_time
     * 记录最大量级：T(n) = 〇(n^2)
     *
     * @param n
     * @return
     */
    int calV2(int n) {
        int sum = 0;    // 一个
        int i = 1;      // 一个
        int j = 1;          // 一个
        for (; i <= n; i++) {// n个
            j = 1;      // n个
            for (; j <= n; j++) { // n*n个
                sum = sum + i * j;// n*n个
            }
        }
        return sum;
    }

    void print(int n) {
        int i = 0;  // 申请空间存储变量i，与n无关，可以忽略
        int[] a = new int[n];   // 申请了大小为n的int类型数组，除此之外，剩下代码都没有占用更多空间，
                                 // 所以整段代码的空间复杂度就是〇（n）
        for (; i < n; ++i) {
            a[i] = i * i;
        }
        for (i = n - 1; i >= 0; --i) {
            System.out.println(a[i]);
        }
    }
}