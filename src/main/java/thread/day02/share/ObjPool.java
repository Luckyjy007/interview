package thread.day02.share;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

/**
 * @program: interview
 * @description: 信号量实现池子
 * @author: jiangyun
 * @create: 2020-01-06 17:07
 **/
public class ObjPool<T, R> {

    final List<T> pool;
    final Semaphore semaphore;

    public ObjPool(int size, T t) {
        pool = new Vector<T>();
        for (int i = 0; i < size; i++) {
            pool.add(t);
        }
        semaphore = new Semaphore(size);
    }

    // 利用对象池的对象，调用 func
    R exec(Function<T, R> func) throws InterruptedException {
        T t = null;
        semaphore.acquire();
        try {
            t = pool.remove(0);
            return func.apply(t);
        } finally {
            pool.add(t);
            semaphore.release();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        // 创建对象池
        //2 可以用connect取代 就是线程池了 这样可以不阻塞的提供多个线程了
        ObjPool<Integer, String> pool = new ObjPool<Integer, String>(10, 2);
// 通过对象池获取 t，之后执行

        pool.exec(t -> {
            System.out.println(t);
            return t.toString();
        });
    }
}

