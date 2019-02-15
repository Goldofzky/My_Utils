package utils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:Keyu
 * 计数器实现的限流算法
 * 缺点：有临界问题，可能在重置计数器的前后一小段时间发生高频的请求，超过规定的次数
 */
public class CountRateLimiter implements Limiter{
    //用于计数
    private AtomicInteger count = new AtomicInteger();
    //每秒限定的次数
    private Integer qps;

    private boolean hasopen = false;


    public CountRateLimiter(Integer qps){
        this.qps = qps;
    }

    private void open(){
        /**
         * 该线程每隔一秒将计数器的值重置为0
         */
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        //将计数器置零
                        count.set(0);
                        TimeUnit.SECONDS.sleep(1);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
        this.hasopen = true;
    }


    @Override
    public boolean acquire(){

        if (!hasopen){
            open();
        }

        if (count.get() >= qps){

            return false;
        }
        count.incrementAndGet();


        return true;
    }

}
