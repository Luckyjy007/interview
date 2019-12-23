package thread.day01.masterworker;

import java.util.Map;
import java.util.Queue;

/**
 * @program: interview
 * @description:
 * @author: jiangyun
 * @create: 2019-12-23 11:29
 **/
public class Worker implements Runnable{

    private Queue<Object> workQueue;
    private Map<String,Object> resultMap;

    public Queue<Object> getWorkQueue() {
        return workQueue;
    }

    public void setWorkQueue(Queue<Object> workQueue) {
        this.workQueue = workQueue;
    }

    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    //子任务处理的逻辑，在子类中实现具体逻辑
    public Object handle(Object input) {
        return input;
    }

    @Override
    public void run() {

        while (true) {
            //获取子任务，poll()方法取出（并删除）队首的对象
            Object input = workQueue.poll();
            if (input == null) {
                break;
            }
            //处理子任务
            Object re = handle(input);
            //将处理结果写入结果集
            resultMap.put(Integer.toString(input.hashCode()), re);
        }

    }
}