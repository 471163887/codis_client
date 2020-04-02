package com.brilliant.fury.codis.client;

import lombok.Data;

/**
 * 这里根据实际的使用场景, 对默认不合理的配置进行改写
 *
 * @author by fury.
 * version 2020/4/2.
 */
@Data
public class JedisPoolConfig {

    /**
     * 资源池中的最大连接数,
     */
    private int maxTotal = 8;

    /**
     * 资源池允许的最大空闲连接数,  maxTotal 和 maxIdle 尽量相等, 避免了连接池伸缩带来的性能干扰
     */
    private int maxIdle = 8;

    /**
     * 资源池确保的最少空闲连接数, 即最少有多少个状态为idle的jedis实例
     */
    private int minIdle = 0;

    /**
     * 当资源池用尽后, 调用者是否要等待. 只有当值为true时, 下面的maxWaitMillis才会生效
     */
    private boolean blockWhenExhausted = true;

    /**
     * 当资源池连接用尽后, 调用者的最大等待时间, 默认5秒超时则抛异常
     */
    private int maxWaitMillis = 5 * 1000;

    /**
     * 向资源池借用连接时是否做连接有效性检测(ping), 检测到的无效连接将会被移除。
     * 业务调用量很大时候建议设置为false, 减少一次ping的网络开销。
     */
    private boolean testOnBorrow = false;

    /**
     * 向资源池归还连接时是否做连接有效性检测(ping), 检测到无效连接将会被移除。
     */
    private boolean testOnReturn = false;

    /**
     * 是否开启JMX监控, 应用本身也需要开启, 这个参数才能生效.
     */
    private boolean jmxEnabled = true;

}
