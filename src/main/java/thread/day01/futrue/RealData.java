package thread.day01.futrue;

/**
 * @program: interview
 * @description:
 * @author: jiangyun
 * @create: 2019-12-24 10:35
 **/
public class RealData implements Data {

    protected String result;

    public RealData(String result) {

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < 5; i++) {
            sb.append(result);
            System.out.println("makeing "+result.toString());
            try {
                //模拟构造时间比较长
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }


        }

        System.out.println("RealData.RealData()");
        this.result = sb.toString();

    }

    @Override
    public String getResult() {
        return result;
    }
}