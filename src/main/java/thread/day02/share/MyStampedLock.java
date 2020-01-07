package thread.day02.share;

import java.util.concurrent.locks.StampedLock;

/**
 * @program: interview
 * @description: StampedLock模版
 * @author: jiangyun
 * @create: 2020-01-07 15:39
 **/
public class MyStampedLock {

    private final StampedLock stampedLock = new StampedLock();

    public void readTemplate() {

        long optimisticRead = stampedLock.tryOptimisticRead();

        // 读入方法局部变量

// 校验 stamp

        if (!stampedLock.validate(optimisticRead)) {

// 升级为悲观读锁
            optimisticRead = stampedLock.readLock();

            try {
                //重新读取

            } finally {
                stampedLock.unlock(optimisticRead);
            }
        }

        // 使用方法局部变量执行业务操作 do work

    }

    public void writeTemplate() {

        long writeLock = stampedLock.writeLock();

        try {
            //写共享变量
        }finally {
            stampedLock.unlock(writeLock);
        }

    }

}