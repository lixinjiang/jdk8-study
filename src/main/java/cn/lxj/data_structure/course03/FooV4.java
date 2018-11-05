package cn.lxj.data_structure.course03;

/**
 * FooV4
 * description 课后
 * create class by lxj 2018/11/5
 **/
public class FooV4 {
    // 全局变量，大小为10的数组array，长度为len，下标为i。
    int array[] = new int[10];
    int len = 10;
    int i = 0;

    // 往数组中添加一个元素
    void add(int element) {
        if (i >= len) {// 数组空间不够了
            // 重新申请一个两倍大小的数组空间
            int[] new_array = new int[len * 2];
            // 把原来array数组中的数据一次copy到new_array中
            for (int j = 0; j < len; j++) {
                new_array[j] = array[j];
            }
            // new_array复制给array,array现在大小就是2倍的len了
            array = new_array;
            len = 2 * len;
        }
        // 将element放到下标为i的位置，下标i加一
        array[i] = element;
        ++i;
    }
}
