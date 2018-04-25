JDK1.8中有一些并发的新特性，可以提高变成的效率。本文写的主要是LongAdder和stampedlock的特性。
多线程发生死锁时dump查看方式：
LongAdder

LongAdder是什么？
在大数据处理过程，为了方便监控，需要统计数据，少不了原子计数器。为了尽量优化性能，需要采用高效的原子计数器。在jdk8中，引入了LongAdder，非常适合多线程原子计数器。
我们知道，AtomicLong已经是非常高效的了，涉及并发的地方都是使用CAS(无锁)操作，在硬件层次上去做 compare and set操作。效率非常高。
LongAdder比AtomicLong更加高效。

实现原理：
LongAdder 沿用了concurrentMap原理，他是将1个整数拆分成一个数组cells，数组中有若干个cell。若有多个线层，每个线程通过CAS更新其中的一个小cell。然后内部将数组做sum求和操作得到整数的value；
这样就使得AtomicLong的单一线程做CAS操作演变成多个线程同时做CAS操作，期间互不影响。从而提高效率；
LongAdder开始并没有做拆分，当多线程间执行遇到冲突时才会拆分cell，若是多线程执行始终没有冲突，则它相当于AtomicLong；

如何分配cell的？？？
拿到线程相关的HashCode对象后，获取它的code变量，计算出一个在Cells 数组中当前线程的HashCode对应的索引位置，并将该位置的Cell 对象拿出来用CAS更新它的value值。

import java.util.concurrent.atomic.LongAdder;

public class LongAdderTest {

    private static LongAdder la =new LongAdder();

    public static int a =0;
    public static void add(){
        la.increment();
        a++;
    }
    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                for(int i=0;i<10000;i++){
                    add();
                }
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                for(int i=0;i<10000;i++){
                    add();
                }
            }
        });
        t2.start();
        t1.join();t2.join();
        System.out.println("---la-----"+la);
        System.out.println("---a-----"+a);
    }

}
StampedLock
stampedLock推出了乐观读锁，在使用乐观读锁时，不会阻塞写锁，这使得我们在写数据时，不会因为使用读锁而长时间的阻塞写，从而提高效率；
ReentrantReadWriteLock 在沒有任何读写锁时，才可以取得写入锁，这可用于实现了悲观读取（Pessimistic Reading），即如果执行中进行读取时，经常可能有另一执行要写入的需求，为了保持同步，ReentrantReadWriteLock 的读取锁定就可派上用场。
然而，如果读取执行情况很多，写入很少的情况下，使用 ReentrantReadWriteLock 可能会使写入线程遭遇饥饿（Starvation）问题，也就是写入线程迟迟无法竞争到锁定而一直处于等待状态。
StampedLock控制锁有三种模式（写，读，乐观读），一个StampedLock状态是由版本和模式两个部分组成，锁获取方法返回一个数字作为票据stamp，它用相应的锁状态表示并控制访问，数字0表示没有写锁被授权访问。在读锁上分为悲观锁和乐观锁。
所谓的乐观读模式，也就是若读的操作很多，写的操作很少的情况下，你可以乐观地认为，写入与读取同时发生几率很少，因此不悲观地使用完全的读取锁定，程序可以查看读取资料之后，是否遭到写入执行的变更，再采取后续的措施（重新读取变更信息，或者抛出异常） ，这一个小小改进，可大幅度提高程序的吞吐量！！
示例代码：
import java.util.concurrent.locks.StampedLock;

public class StampedLockTest {

    private static final StampedLock sl = new StampedLock();
    private static int x;
    private static int y;

    public static void move(int deltax,int deltay) throws InterruptedException{
        System.out.println("写线程----"+Thread.currentThread().getName());
        long sw = sl.writeLock();//获取写锁
        try{
            x = x+deltax;
            y = y+deltay;
        }finally{
            sl.unlockWrite(sw);//释放写锁
        }
    }

    public static int distanceFromOrigin(){
        long sr = sl.tryOptimisticRead(); //获取乐观读锁，不会阻塞写锁
        int currentx = x;
        int currenty = y;
        //读完成后验证期间是否有写操作改变了数据sl.validate(sr)为true则表示期间无写操作，否则表示数据可能已经被改变
        System.out.println(currentx+"---第一次读取数据-----"+currenty);
        if(!sl.validate(sr)){
            sr = sl.readLock(); //使用了悲观锁，会阻塞写锁
            try{
                System.out.println("悲观锁读线程----------"+Thread.currentThread().getName());
                currentx = x;
                currenty = y;
            }finally{
                sl.unlockRead(sr);//释放悲观读锁
            }
        }else{
            System.out.println("乐观锁读线程----------"+Thread.currentThread().getName());
        }
        System.out.println(currentx+"----第二次读取数据----"+currenty);
        return currentx*currenty;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        for(int i=0;i<10;i++){
            final int q =i;
            new Thread(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    try {
                        move(q,q+8);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        for(int i=0;i<50;i++){
            new Thread(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    try {
                        distanceFromOrigin();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

}