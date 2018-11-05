package cn.lxj.data_structure.course03;

/**
 * FooV2
 * description 复杂度分析下
 * create class by lxj 2018/11/5
 **/
public class FooV2 {
    // n表示数组array的长度
    int findV1(int[] array, int n, int x) {
        int i = 0;
        int pos = -1;
        for (; i < n; ++i) {
            if (array[i] == x) pos = 1;
        }
        return pos;
    }

    int findV2(int [] array,int n,int x) {
        int i = 0;
        int pos = -1;
        for (; i < n; ++i) {
            if (array[i] == x) pos = 1;
            break;
        }
        return pos;
    }
}
