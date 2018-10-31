package cn.lxj.java36courses.course15;

/**
 * Reader
 * description 读数据Reader线程
 * create by lxj 2018/8/10
 **/
public class Reader implements Runnable {
    private PricesInfo pricesInfo;  //价格信息实体

    /**
     * 构造方法
     * @param pricesInfo
     */
    public Reader(PricesInfo pricesInfo) {
        this.pricesInfo = pricesInfo;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s：Price1：%f\n", Thread.currentThread().getName(), pricesInfo.getPrice1());
            System.out.printf("%s：Price2：%f\n", Thread.currentThread().getName(), pricesInfo.getPrice2());
        }
    }
}
