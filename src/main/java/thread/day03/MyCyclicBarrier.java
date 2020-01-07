package thread.day03;

import sun.jvm.hotspot.memory.Generation;

import java.util.Vector;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @program: interview
 * @description:
 * @author: jiangyun
 * @create: 2020-01-07 17:14
 **/
public class MyCyclicBarrier<D,P,R> {
    // 订单队列
    Vector<P> pos;
    // 派送单队列
    Vector<D> dos;

    void check(){
        P p = pos.remove(0);
        D d = dos.remove(0);
        // 执行对账操作
        R diff = check(p, d);
        // 差异写入差异库
        save(diff);
    }


//    void checkAll(){
//        // 循环查询订单库
//        Thread T1 = new Thread(()->{
//            while(hasUnSendOrder()){
//                // 查询订单库
//                pos.add(getPOrders());
//                // 等待
//                barrier.await();
//            }
//        });
//        T1.start();
//        // 循环查询运单库
//        Thread T2 = new Thread(()->{
//            while(hasUnSendOrder()){
//                // 查询运单库
//                dos.add(getDOrders());
//                // 等待
//                barrier.await();
//            }
//        });
//        T2.start();
//    }



    private D getDOrders() {
        return null;
    }

    private P getPOrders() {
        return null;
    }


    R check( P p,D d){

        //todo do buissness

        return null;
    }

    void save(R diff){

        //write db
    }



    boolean hasUnSendOrder(){

        return false;
    }



    public static void main(String[] args) {

        // 执行回调的线程池
        Executor executor = Executors.newFixedThreadPool(1);
      //  final CyclicBarrier barrier = new CyclicBarrier(2, ()->{ executor.execute(()->check()); });

    }


}