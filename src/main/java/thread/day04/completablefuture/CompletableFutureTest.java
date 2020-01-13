package thread.day04.completablefuture;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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

}
