package thread.day02.disruptor;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @program: interview
 * @description:
 * @author: jiangyun
 * @create: 2019-12-26 16:25
 **/
public class LongEventProducer {

    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }


    //发布事件
    public void onData(ByteBuffer bb) {
        //可以把ringBuffer看做一个事件队列，那么next就是得到下面一个事件槽
        long sequence = ringBuffer.next();//1.获取下一个序列
        try {
            //用上面的索引取出一个空的事件用于填充
            LongEvent event = ringBuffer.get(sequence);//2.获取disruptor的sequence

            System.out.println(sequence+" sequence");
            event.setValue(bb.getLong(0)); //3.设置事件Context
        } finally {
            //发布事件
            ringBuffer.publish(sequence); //4.发布事件
        }
    }
}