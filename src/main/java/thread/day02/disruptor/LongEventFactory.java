package thread.day02.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @program: interview
 * @description:通过一个工厂来生产数据(Event)
 * @author: jiangyun
 * @create: 2019-12-26 16:11
 **/
public class LongEventFactory implements EventFactory<LongEvent> {

    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}