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
        private volatile int count;

        public TaskThread(CyclicBarrier barrier) {
            this.barrier = barrier;
        }



        @Override
        public void run() {
           try {
                barrier.await();
               System.out.println(getName() + " 冲破栅栏 A"+(++count));

             //等待所有线程冲破栅栏A然后一起往下走
                barrier.await();
                System.out.println(getName() +" 冲破栅栏 B"+(++count));
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

        for(int i = 0; i < threadNum; i++) {
            new TaskThread(barrier).start();
        }
    }
    }

