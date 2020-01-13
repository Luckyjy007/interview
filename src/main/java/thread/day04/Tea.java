package thread.day04;

import java.util.concurrent.*;

/**
 * @author ：江运
 * @date ：2020/1/13 15:44
 * @description：泡茶问题
 */
public class Tea {

    /**
     *
     * 功能描述:T1Task 需要执行的任务：洗水壶、烧开水、泡茶
     * @param:
     * @return:
     * @author: 江运
     * @date: 2020/1/13 3:45 下午
     */
    static class T1Task implements Callable<String>{

        FutureTask<String> ft2;
        // T1 任务需要 T2 任务的 FutureTask


        public T1Task(FutureTask<String> ft2) {
            this.ft2 = ft2;
        }

        @Override
        public String call() throws Exception {
            System.out.println("T1: 洗水壶...");
            TimeUnit.SECONDS.sleep(1);

            System.out.println("T1: 烧开水...");
            TimeUnit.SECONDS.sleep(3);
            // 获取 T2 线程的茶叶 get方法导致的阻塞 拿不到茶叶不执行下去
            System.out.println("T1 我会阻塞的等待t2茶叶准备好");
            String tf = ft2.get();
            System.out.println("T1: 拿到t2的茶叶:"+tf);

            System.out.println("T1: 泡茶...");
            return " 上茶:" + tf;
        }
    }


    /**
     *
     * 功能描述:T2Task 需要执行的任务:洗茶壶、洗茶杯、拿茶叶
     * @author: 江运
     * @date: 2020/1/13 3:35 下午
     */
    static class T2Task implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("T2: 洗茶壶...");
            TimeUnit.SECONDS.sleep(1);

            System.out.println("T2: 洗茶杯...");
            TimeUnit.SECONDS.sleep(2);

            System.out.println("T2: 拿茶叶...");
            TimeUnit.SECONDS.sleep(5);
            System.out.println("T2  我拿到茶叶了");
            return " 龙井 ";
        }}

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<String> ft2 = new FutureTask<>(new T2Task());
// 创建任务 T1 的 FutureTask
        FutureTask<String> ft1 = new FutureTask<>(new T1Task(ft2));

        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        threadPool.submit(ft1);
        threadPool.submit(ft2);

        threadPool.shutdown();
        System.out.println(ft1.get());
    }


}
