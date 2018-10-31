package cn.lxj.java36courses.course12;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * NIOCopy
 * description NIO拷贝
 * create by lxj 2018/7/26
 **/
public class NIOCopy {
    public static void copyFileByChannel(File source, File dest) {
        try (FileChannel ic = new FileInputStream(source).getChannel(); FileChannel oc = new FileOutputStream(dest)
                .getChannel();) {
            for (long count = ic.size(); count > 0; ) {
                long transferrd = ic.transferTo(ic.position(), count, oc);
                ic.position(ic.position() + transferrd);
                count -= transferrd;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
