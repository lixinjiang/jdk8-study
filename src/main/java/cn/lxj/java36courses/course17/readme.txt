一个线程两次调用start()方法会出现什么情况？
答：Java线程是不允许调用两次start的，第二次调用必然会抛出IllegalThreadStateException，这是一种运行时异常，多次调用会被认为是编程错误。
    关于线程的生命周期的不同状态，在java5之后，线程状态被明确定义在其公共内部枚举类型java.lang.Thread.State中，分别是
    新建（NEW），表示线程被创建出来还未真正启动的状态，可以认为它是java的内部状态。
    就绪（RUNNABLE），表示该线程已经在jvm中执行，当然由于执行需要消耗资源，可能是正在运行，也可能是在等待系统分配给他CPU片段，在就绪队列里面排队。
    在其他一些分析中，会额外区分一种状态RUNNING,但是从JavaAPI角度来讲，并不能表示出来。
    阻塞（BLOCKED），这个状态和我们前面两讲介绍的同步非常相关，阻塞表示线程在等待Monitor lock。比如线程试图通过synchronized去获取锁，但是其他线程已经独占了，那么当前线程就会处于独占状态。
    等待（WAITING)，表示正在等待其他线程采取某些操作。一个常见的场景类似于生产消费者模式，发现任务条件尚未满足，就让当前消费者线程等待（wait），另外的生产者线程去准备任务数据，然后通过类似
        notify等动作，通知消费线程可以继续工作。Thread.join()也会令线程进入等待状态。
    计时等待（TIMED_WAIT），其进入条件和等待状态类似，但是调用的是存在超时条件的方法，比如wait或join等方法的指定超时版本。
        public final native void wait(long timeout) torws InterruptedException.
    终止（TERMINATED）,不管是意外退出还是正常执行结束，线程已经完成使命，终止运行，也有人将这个状态称为死亡。

