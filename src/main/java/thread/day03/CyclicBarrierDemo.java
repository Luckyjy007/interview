package thread.day03;

import java.util.concurrent.CyclicBarrier;

/**
 * @program: interview
 * @description:
 * @author: jiangyun
 * @create: 2020-01-07 18:11
 **/
public class CyclicBarrierDemo {

    static class TaskThread extends Thread {

        CyclicBarrier barrier;

        public TaskThread(CyclicBarrier barrier) {
            this.barrier = barrier;
        }



        @Override
        public void run() {
           try {
           Thread.sleep((int) (Math.random() * 1000));
                System.out.println(getName() + " 到达栅栏 A");
                barrier.await();
               barrier.await();
               barrier.await();
               barrier.await();
               barrier.await();
              //  System.out.println(getName() + " 冲破栅栏 A");
               //Thread.sleep((int) (Math.random() * 1000));
               // System.out.println(getName() + " 到达栅栏 B");
              //  barrier.await();
              //  System.out.println(getName() + " 冲破栅栏 B");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }}


    public static void main(String[] args) throws InterruptedException {
        int threadNum = 5;
        CyclicBarrier barrier = new CyclicBarrier(threadNum, new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " 完成最后任务");
            }
        });

        for(int i = 0; i < 5; i++) {
            new TaskThread(barrier).start();
            Thread.sleep(100);
        }
    }
    }

