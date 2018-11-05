package cn.lxj.data_structure.course03;

/**
 * FooV3
 * description
 * create class by lxj 2018/11/5
 **/
public class FooV3 {
    void insert(int value, int n) {
        // array 表示一个长度为n的数组
        // 代码中的array.length就等于n
        int[] array = new int[n];
        int count = 0;
        if (count == array.length) {
            int sum = 0;
            for (int i = 0; i < array.length; ++i) {
                sum = sum + array[i];
            }
            array[0] = sum;
            count = 1;
        }
        array[count] = value;
        ++count;
    }
}