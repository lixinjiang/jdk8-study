package cn.lxj.data_structure.course06;

import java.io.File;

/**
 * PrintTreeTest
 * description TODO
 * create class by lxj 2018/11/5
 **/
public class PrintTreeTest {
    public static void main(String[] args) {
        File file = new File("e:/u02");
        printFile(file, 0);
    }

    public static void printFile(File file, int level) {
        for(int i = 0; i < level; ++i) {
            System.out.print("\t");
        }
        System.out.println(file.getName());

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for(File f: files) {
                printFile(f, level + 1);
            }
        }
    }
}