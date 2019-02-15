package utils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:Keyu
 * 令牌桶限流法
 */
public class TokenBucketLimiter implements Limiter{
    //令牌
    AtomicInteger token = new AtomicInteger();
    //每秒限定的次数
    private Integer qps;

    private boolean hasopen = false;

    private Integer waitTime;

    private Integer maxPermits;

    public TokenBucketLimiter(Integer qps){
        token.set(1);
        this.qps = qps;
        this.waitTime = 1000000/qps;
        this.maxPermits = qps;
    }

    private void open(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        TimeUnit.MICROSECONDS.sleep(waitTime);
                        if (token.get() < maxPermits){
                            //如果令牌桶没有满
                            //放令牌进去
                            token.incrementAndGet();
                        }
                        //否则丢弃令牌

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
        hasopen = true;
    }

    @Override
    public boolean acquire(){
        if (!hasopen){
            open();
        }

        if (token.get() <= 0){
            return false;
        }

        token.decrementAndGet();
        return true;
    }

}
