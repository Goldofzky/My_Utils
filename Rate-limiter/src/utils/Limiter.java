package utils;

/**
 * @Author:Keyu
 */
public interface Limiter {

    /**
     * 限流请求
     * @return true 请求成功
     * @return false 请求失败
     */
    public boolean acquire();
}
