package thread.day04.completablefuture;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * @author ：江运
 * @date ：2020/1/13 18:49
 * @description：example
 */
public class CompletableFutureTest {


    public CompletableFutureTest()  {
    }

    /**
     *
     * 功能描述:直接阻塞
     * @param: []
     * @return: void
     * @author: 江运
     * @date: 2020/1/13 6:53 下午
     */
    @Test
    public void create() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        //会一直阻塞在此
        String s = completableFuture.get();
    }

    /**
     *
     * 功能描述:手动完成
     * @param: []
     * @return: void
     * @author: 江运
     * @date: 2020/1/13 6:54 下午
     */
    @Test
    public void completeManual(){
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        boolean complete = completableFuture.complete("我完成了");
    }

    /**
     *
     * 功能描述:
     * @param: []
     * @return: void
     * @author: 江运
     * @date: 2020/1/13 6:57 下午
     */
    @Test
    public void runSync() throws ExecutionException, InterruptedException {

        CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {

                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("你不get我 我就不会出现啊");

            }
        });
        System.out.println(future.isDone());
       future.get();
        System.out.println(future.isDone());




    }

    /**
     *
     * 功能描述:
     * @param: []
     * @return: void
     * @author: 江运
     * @date: 2020/1/14 9:48 上午
     */
    public void runSyncLambda(){

        CompletableFuture.runAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("快get我 马上出来");

        });

    }

    /**
     *
     * 功能描述:带返回值
     * @param: []
     * @return: void
     * @author: 江运
     * @date: 2020/1/14 10:04 上午
     */
    @Test
    public void supplyAsync() throws ExecutionException, InterruptedException {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {

            @Override
            public String get() {

                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return "Hello CompletableFuture";

            }
        });
        System.out.println(future.get());
    }


    /**
     *
     * 功能描述:
     * @param: []
     * @return: void
     * @author: 江运
     * @date: 2020/1/14 10:06 上午
     */
    @Test
    public void supplyAsyncLambda() throws ExecutionException, InterruptedException {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {

            try {
              Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "Hello supplyAsyncLambda";
        });

        System.out.println(future.get());

    }


    /**
     *
     * 功能描述:
     * @param: []
     * @return: void
     * @author: 江运
     * @date: 2020/1/14 10:18 上午
     */
    @Test
    public void supplyAsyncExecutors() throws ExecutionException, InterruptedException {

        ExecutorService executors = Executors.newFixedThreadPool(3);

        CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {

                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("我是用executors的");
                return "Hello";
            }
        }, executors);
        System.out.println(future.get());

    }

    /**
     *
     * 功能描述:
     * @param: []
     * @return: void
     * @author: 江运
     * @date: 2020/1/14 10:21 上午
     */
    @Test
    public void supplyAsyncExecutorsLambad() throws ExecutionException, InterruptedException {

        ExecutorService pool = Executors.newFixedThreadPool(5);

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("hello ");
            return "HaHa";
        }, pool);

        System.out.println(future.get());


    }

    @Test
    public void thenApply() throws ExecutionException, InterruptedException {

        String s = CompletableFuture.runAsync(() -> {
            System.out.println("");
        }).thenApply(x -> {
            System.out.println(x);
            return "x" + x;
        }).get();
        System.out.println(s);

    }

    @Test
    public void thenApply2() throws ExecutionException, InterruptedException {

        CompletableFuture<String> apply = CompletableFuture.supplyAsync(() -> {
            return "I am jiangyun";
        }).thenApply(x -> { return x + "  Wleome to HuBei";
        });
        System.out.println(apply.get());


        CompletableFuture<Object> compose = CompletableFuture.runAsync(() -> {
            System.out.println("");
        }).thenApply(x -> {
            return x + " HaHa";
        }).thenCompose(x -> {
            System.out.println("x=" + x + " test");
            return null;
        });

    }


    @Test
    public void thenCompose() throws ExecutionException, InterruptedException {

        CompletableFuture<String> compose = CompletableFuture.supplyAsync(() -> {
            return "Hello ";
        }).thenCompose(x -> CompletableFuture.supplyAsync(() -> {
            return x + " World";
        }));
        System.out.println(compose.get());

    }

    @Test
    public void thenCombine(){

        CompletableFuture<Integer> height = CompletableFuture.supplyAsync(() -> {
            System.out.println("Calculating height ");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new Random().nextInt(10);
        });


        CompletableFuture<Double> width = CompletableFuture.supplyAsync(() -> {
            System.out.println("Calculating width ");

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new Random().nextDouble() * 100;
        });


        width.thenCompose()

    }
}
