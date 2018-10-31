package cn.lxj.java36courses.course15;

/**
 * Writer
 * description 写线程
 * create by lxj 2018/8/13
 **/
public class Writer implements Runnable{
    private PricesInfo pricesInfo;

    /**
     * 构造方法
     * @param pricesInfo
     */
    public Writer(PricesInfo pricesInfo){
        this.pricesInfo = pricesInfo;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {   // 三次设置price1和price2
            pricesInfo.setPrices(Math.random() * 10, Math.random() * 8);
        }
    }
}