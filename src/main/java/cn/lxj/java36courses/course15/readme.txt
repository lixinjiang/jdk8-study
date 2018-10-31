第十五讲    |   synchronized和ReentrantLock有什么区别

注：Java并发阶段

面试问题：synchronized和ReentrantLock有什么区别？有人说synchronized最慢，这句话靠谱吗？

典型回答：synchronized是java的内建同步机制，所以有人也称其为Intrinsic Locking，提供了互斥的语义和可见性，当一个线程已经获取当前锁的时候，其他视图获取的线程只能等待或者阻塞在那里，
    在Java5之前，synchronized是仅有的同步手段，在代码中synchronized可以用来修饰方法，也可以使用在特定的代码块上，本质上synchronized方法等同于把方法全部语句用synchronized块包起来。

    ReentrantLock通常翻译为重入锁（再入锁），是Java5提供的锁实现，它的语义和synchronized基本相同。重入锁通过代码直接调用lock（）方法获取，代码书写也更加灵活。与此同时ReentrantLock
    提供了很多实用的方法，实现很多synchronized无法做到的细节控制，比如可以控制fairness（公平性），或者利用定义条件等。但是注意，编码中需要调用unlock（）方法释放，不然会一直持有该锁。
    在早起可能synchronized性能比较差，但是在后续版本改进当中，在低竞争场景中可能优于ReentrantLock。

考点分析：
    这是并发编程当中的常见问题。锁作为并发编程的基础工具之一，至少需要掌握：
    ——理解什么是线程安全
    ——synchronized、ReentrantLock等机制的基本使用案例
    更进一步，还需要：
    ——掌握synchronized、ReentrantLock底层实现原理；理解锁膨胀，降级；理解偏斜锁、自旋锁、轻量锁、重量级锁等概念。
    ——掌握并发包中java.util.concurrent.lock各种不同实现和案例分析。
    Brain Goetz 等专家撰写的《Java 并发编程实战》（Java Concurrency in Practice）
什么是线程安全？
    如果状态不是共享的，或者是不可修改的，也就不存在线程安全的问题，进而可以推理出保证线程安全的两个办法：
    1、封装：通过封装，我们可以对对象内部隐藏、保护起来。
    2、不可变：java语言还没有真正意义上的原生不可变，但是未来也许会引入。理解final和immutable。
    线程安全需要保证几个基本特征：
    1、原子性：相关操作不会中途被其他线程干扰，一般通过同步机制实现。
    2、可见性：是一个线程修改了某个共享变量，其状态能够立即被其他线程知晓，通常被解释为将线程本地状态反映到主内存上，volatile就是负责保护可见性。
    3、有序性：是保证线程内串行语义，避免指令重排等。

通过ThreadSafeSample来模拟两次对共享状态的操作，仅仅两个线程的低度并发，就非常容易碰到former和latter不等的情况，这是因为在两次取值的过程中，其他线程可能已经修改了shareState。

修改：将两次赋值用synchronized保护起来，使用this作为互斥单元，就可以避免别的线程去修改sharedState。见ThreadSafeSampleSyn.java
    注：private 和 public 的作用是在该实例内，对于整个成员方法可见，而不是该类所有的实例的可见性的判断标识；
        单例才存在线程安全问题，多例不存在，除非共享同一个外部变量；

ReentrantLock：重入锁（再入锁）
    表示当一个线程试图获取一个它已经获取的锁的时候，这个获取动作就自动成功，这是对锁获取粒度的一个概念，亦即锁的持有是以线程为单位而不是基于调用次数,java锁实现强调再入性是为了和pthread
    的行为再区分。再入锁可以设置公平性（fairness），可以在创建再入锁的时候选择是否公平的。
    ReentrantLock fairLock = new ReentrantLock(true);   //
    为true公平时，代表会将锁交给等待时间最久的线程。公平性是减少线程“饥饿”（个别线程长期等待锁，但始终无法获取）情况发生的一个办法。
    如果使用synchronized，那么无法进行公平性的选择，其永远不是公平的，这也是主流系统线程调度的选择。通用场景中，公平性未必有想象中的那么重要，java默认的调度策略很少导致“饥饿”发生。
    与此同时，若要引入公平性则会需要额外开销，自然会导致一定的吞吐量下降。所以，当程序中确实有公平性需要的时候，才有必要指定它。

    优势：
        ——带超时的获取锁尝试
        ——可以判断是否有线程，后者某个特定线程，再排队等待获取锁
        ——可以响应中断请求。
        ——...

    强调条件变量：java.util.concurrent.Condition
    如果说ReentrantLock是synchronized的替代，那么Condition就是wait、notify、notifyAll等操作转为相应的对象，将晦涩而复杂的同步操作转变为直观可控的对象行为。
    ArrayBlockingQueue——并发阻塞队列，在于阻塞队列支持阻塞添加和阻塞删除方法。
        阻塞添加：当阻塞队列元素已满的时候，队列会阻塞加入元素线程，知道队列元素不满的时候才重新唤醒线程执行元素加入操作。
        阻塞删除：队列元素为空的时候，删除队列元素的线程将被阻塞，知道队列不为空再执行删除操作（一般都会返回被删除的元素）

附：读写锁   PricesInfo  Reader Writer

