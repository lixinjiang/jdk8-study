package cn.lxj.java36courses.course15;

/**
 * TestReadWriteLock
 * description 测试读写锁，定义5个读线程和一个写线程，通过运行他们我们可以来查看运行的结果
 * create by lxj 2018/8/13
 **/
public class TestReadWriteLock {
    public static void main(String[] args) {
        PricesInfo pricesInfo = new PricesInfo();
        Reader[] readers = new Reader[100];       // 5个读线程
        Thread[] threadReaders = new Thread[100]; // 5个读线程
        for (int i = 0; i < 100; i++) {
            readers[i] = new Reader(pricesInfo);
            threadReaders[i] = new Thread(readers[i]);
        }
        Writer writer = new Writer(pricesInfo);
        Thread threadWriter = new Thread(writer);
        for (int i = 0; i < threadReaders.length; i++) {
            threadReaders[i].start();
        }
        threadWriter.start();
    }
}