package thread.day02.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * @program: interview
 * @description:在定义了Event之后我们需要创建一个消费者来处理这些事件，也是一个事件处理器
 * @author: jiangyun
 * @create: 2019-12-26 16:12
 **/
public class LongEventHandler implements EventHandler<LongEvent>{

    private String clientName;

    LongEventHandler(String clientName){
        this.clientName = clientName;
    }

    //事件监听，类似观察者模式
    @Override
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {

       // System.out.println(longEvent.getValue());
               System.out.println(this.clientName + "\t"+longEvent.getValue());//向Client推送消息
        longEvent.getValue();


    }
}