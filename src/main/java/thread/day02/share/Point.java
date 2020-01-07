package thread.day02.share;


import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.StampedLock;

/**
 * @program: interview
 * @description: ReadWriteLock 支持两种模式：一种是读锁，一种是写锁。而 StampedLock 支持三种模式，分别是：写锁、
 * 悲观读锁和乐观读。其中，写锁、悲观读锁的语义和 ReadWriteLock 的写锁、读锁的语义非常类似，允许多个线程同时获取悲观
 * 读锁，但是只允许一个线程获取写锁，写锁和悲观读锁是互斥的。不同的是：StampedLock 里的写锁和悲观读锁加锁成功之后，
 * 都会返回一个 stamp；然后解锁的时候，需要传入这个 stamp。相关的示例代码如下。
 * @author: jiangyun
 * @create: 2020-01-07 14:54
 **/
public class Point {


    private int x, y;

    final StampedLock stampedLock = new StampedLock();


    double distanceFromOrigin() {

        //乐观读
        long l = stampedLock.tryOptimisticRead();
        // 读入局部变量，
        // 读的过程数据可能被修改
        int curX = x, curY = y;
        // 判断执行读操作期间，
        // 是否存在写操作，如果存在，
        // 则 sl.validate 返回 false

        if (!stampedLock.validate(l)) {

            // 升级为悲观读锁
            l = stampedLock.readLock();

            try {

                curX = x;
                curY = y;

            } finally {

                stampedLock.unlock(l);
            }
        }

        return Math.sqrt(curX * curX + curY * curY);
        //在上面这个代码示例中，如果执行乐观读操作的期间，存在写操作，会把乐观读升级为悲观读锁。这个做法挺合理的，
        // 否则你就需要在一个循环里反复执行乐观读，直到执行乐观读操作的期间没有写操作（只有这样才能保证 x 和 y
        // 的正确性和一致性），而循环读会浪费大量的 CPU。升级为悲观读锁，代码简练且不易出错，建议你在具体实践时
        // 也采用这样的方法。

    }

    public static void main(String[] args) throws InterruptedException {

        /*
        对于读多写少的场景 StampedLock 性能很好，简单的应用场景基本上可以替代 ReadWriteLock，但是StampedLock 的功能仅仅是
        ReadWriteLock 的子集，在使用的时候，还是有几个地方需要注意一下。
        StampedLock 在命名上并没有增加 Reentrant，想必你已经猜测到 StampedLock 应该是不可重入的。事实上，的确是这样的，
        StampedLock 不支持重入。这个是在使用中必须要特别注意的。
        另外，StampedLock 的悲观读锁、写锁都不支持条件变量，这个也需要你注意。
        还有一点需要特别注意，那就是：如果线程阻塞在 StampedLock 的 readLock() 或者 writeLock() 上时，此时调用该阻塞线程的
        interrupt() 方法，会导致 CPU 飙升。例如下面的代码中，线程 T1 获取写锁之后将自己阻塞，线程 T2 尝试获取悲观读锁，
        也会阻塞；如果此时调用线程 T2 的 interrupt() 方法来中断线程 T2 的话，你会发现线程 T2 所在 CPU 会飙升到 100%。
         */

        StampedLock stampedLock = new StampedLock();

        Thread t1 = new Thread(() -> {
            //获取锁
            stampedLock.writeLock();
            // 永远阻塞在此处，不释放写锁
            LockSupport.park();
        });

        t1.start();

        // 保证 T1 获取写锁
        Thread.sleep(100);

        Thread T2 = new Thread(() ->
                // 阻塞在悲观读锁
                stampedLock.readLock()
        );
        T2.start();
// 保证 T2 阻塞在读锁
        Thread.sleep(100);
        // 中断线程 T2
        // 会导致线程 T2 所在 CPU 飙升
        T2.interrupt();
        T2.join();
    }


}