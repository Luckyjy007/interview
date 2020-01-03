package thread.day01.futrue;

/**
 * @program: interview
 * @description:
 * @author: jiangyun
 * @create: 2019-12-24 10:35
 **/
public class FutureData  implements Data{

    private RealData realData = null;
    private boolean isReady = false;

    // 进行同步控制
    public synchronized void setResult(RealData realData) {
        if (isReady) {
            return;
        }
        System.out.println("FutureData.setResult()");
        this.realData = realData;
        isReady = true;
        notifyAll();

    }



    // 实际调用返回RealDate的数据
    @Override
    public synchronized String getResult() {

        while (!isReady){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("FutureData.getResult()");
        return realData.result;

    }

    @Override
    public String toString() {
        return "FutureData{" +
                "realData=" + realData +
                ", isReady=" + isReady +
                '}';
    }
}