package thread.day02.share;

import java.util.concurrent.Semaphore;

/**
 * @program: interview
 * @description:信号量测试
 * @author: jiangyun
 * @create: 2020-01-06 16:14
 **/
public class MySemaphore {

    static final Semaphore s = new Semaphore(1);
    static int count;

    void addOne() throws InterruptedException {

        System.out.println(Thread.currentThread().getName() + " enter addOne");
        s.acquire();
        System.out.println(Thread.currentThread().getName() + " do s.acquire() " + s);
        try {
            count++;
            System.out.println(Thread.currentThread().getName() + "  count++; " + count);
        } finally {
            s.release();
            System.out.println(Thread.currentThread().getName() + "   s.release(); " + s);

        }


    }

    public static void main(String[] args) throws InterruptedException {
        MySemaphore semaphore = new MySemaphore();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.addOne();
                } catch (InterruptedException e) {


                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.addOne();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.join();
        thread2.join();

        thread1.start();

        thread2.start();
    }

}