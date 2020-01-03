package thread.day02.disruptor;

/**
 * @program: interview
 * @description: 在disruptor中，Event代表数据，定义携带数据的事件
 * @author: jiangyun
 * @create: 2019-12-26 16:10
 **/
public class LongEvent {

    private long value;


    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}