package thread.day01.masterworker;

import java.util.Map;
import java.util.Set;

/**
 * @program: interview
 * @description:
 * @author: jiangyun
 * @create: 2019-12-23 11:36
 **/
public class PlusWorker extends Worker {

    @Override
    public Object handle(Object input) {
        Integer i = (Integer) input;

      //  System.out.println(Thread.currentThread().getName()+"\t"+Thread.currentThread().getId());

        return i;
    }

    public static void main(String[] args) {

        Master master = new Master(new PlusWorker(), 5);
        for (int i = 0; i <=100; i++) {
            master.submit(i); //提交一百个子任务
        }
        master.execute(); //开始计算
        int re = 0;
        Map<String, Object> resultMap = master.getResultMap();
        while (resultMap.size() > 0 || !master.isComplete()) {
            Set<String> keys = resultMap.keySet();
            String key = null;
            for (String k : keys) {
                key = k;
                break;
            }
            Integer i = null;
            if (key != null) {
                i = (Integer) resultMap.get(key);   //从结果集中获取结果
            }
            if (i != null) {
                re += i;        //最终结果
            }
            if (key != null) {
                resultMap.remove(key);      //移除已经被计算过的项
            }
        }
        System.out.println("result: " + re);

    }
}