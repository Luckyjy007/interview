package thread.day01.executor;

import java.util.UUID;
import java.util.concurrent.*;

/**
 * @program: interview
 * @description:
 * @author: jiangyun
 * @create: 2019-12-24 15:33
 **/
public class UseExecutor   {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService pool = Executors.newFixedThreadPool(10);



        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);


        FutureTask<String> task = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {


                Thread.sleep(1000L);

                return UUID.randomUUID().toString();
            }
        });


        Future<?> submit = pool.submit(task);



        String s = task.get();

        System.out.println(" other waiting "+s);



        int count=0;

        ScheduledFuture<?> schedule = scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {

            //    System.out.println("hi ");


            }
        }, 1, 1, TimeUnit.SECONDS);


    }


}