package thread.day04.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author ：江运
 * @date ：2020/1/13 18:42
 * @description：
 */
public class Create {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        //会一直阻塞在此
        String s = completableFuture.get();

        //手动完成一个completableFuture任务
        boolean complete = completableFuture.complete("我完成了");
        System.out.println(complete);

    }
}
