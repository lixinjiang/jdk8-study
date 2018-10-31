package cn.lxj.java36courses.course12;

import java.io.*;

/**
 * IOCopy
 * description java.io类库中的拷贝
 * create by lxj 2018/7/26
 **/
public class IOCopy {
    // 利用java.io类库，直接为源文件构建一个FileInputStream读取，然后再为目标文件构建一个FileOutStream，完成写入工作
    public static void copyFileByStream(File source, File dest) {
        // 这种写法是当try()中的资源会自动关闭，不用谢finally的操作了，如果使用try{}的话，开启或者新建的资源需要在finally中去关闭
        try (FileInputStream is = new FileInputStream(source); OutputStream os = new FileOutputStream(dest);) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
