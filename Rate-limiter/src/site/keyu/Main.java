package site.keyu;

import utils.CountRateLimiter;
import utils.TokenBucketLimiter;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        //CountRateLimiter limiter = new CountRateLimiter(4);
        TokenBucketLimiter limiter = new TokenBucketLimiter(50);


        while (true){
            try {
                if (limiter.acquire()){
                    System.out.println("Acquire time:"+ System.currentTimeMillis());
                }else {
                    System.out.println("Service is too busy!"+System.currentTimeMillis());
                }

                TimeUnit.MICROSECONDS.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
