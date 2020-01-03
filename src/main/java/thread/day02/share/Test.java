package thread.day02.share;

import java.util.List;

/**
 * @program: interview
 * @description:
 * @author: jiangyun
 * @create: 2019-12-30 15:37
 **/
public class Test {

    private long count = 0;

    public void add10w() {

        for (int i = 0; i < 100000; i++) {

            this.count++;
        }

    }

    public static void main(String[] args) throws InterruptedException {
        final Test test = new Test();

        Thread th1 = new Thread(() -> {
            test.add10w();
        });

        Thread th2 = new Thread(() -> {
            test.add10w();
        });

        th1.start();
        th2.start();

        th1.join();
        th2.join();

        System.out.println(test.count);
    }

    private List<Object> als;

    // 一次性申请所有资源
    synchronized void apply(Object from, Object to) {
        // 经典写法
        while (als.contains(from) || als.contains(to)) {
            try {
                wait();
            } catch (Exception e) {
            }
        }
        als.add(from);
        als.add(to);
    }

}