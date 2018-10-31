package cn.lxj.jvm40courses.course10;

/**
 * CPUCasheTest
 * description
 * create class by lxj 2018/10/30
 **/
public class CPUCasheTest {
    public static void main(String[] args) {
        int[] arr2 = new int[6_4 * 1_0_2_4 * 1_0_2_4];
        long start1 = System.nanoTime();
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] *= 3;
        }
        long l1 = System.nanoTime() - start1;
        System.out.println(l1);
        long start2 = System.nanoTime();
        for (int i = 0; i < arr2.length; i+=16) {
            arr2[i] *= 3;
        }
        long l2 = System.nanoTime() - start2;
        System.out.println(l2);
        System.out.println("///////:" + l1 / l2);
    }
}
