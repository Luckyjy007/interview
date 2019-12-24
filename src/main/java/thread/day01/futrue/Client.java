package thread.day01.futrue;

/**
 * @program: interview
 * @description:
 * @author: jiangyun
 * @create: 2019-12-24 10:54
 **/
public class Client {

    public Data request(final String queryStr){
        //返回伪数据
        final FutureData futureData = new FutureData();
        //开启线程构造真实数据
        new Thread(){
            public void run(){
                RealData realData = new RealData(queryStr);
                System.out.println("要调用futureData");
                futureData.setResult(realData);
            }
        }.start();
        //返回伪数据，等待真实数据加载
        return futureData;
    }

    public static void main(String[] args) {


        Data data = new Client().request("123456");
        System.out.println("main:"+data);
        System.out.println("main:"+data.getResult());

    }

}