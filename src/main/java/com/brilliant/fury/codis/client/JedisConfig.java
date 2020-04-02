package com.brilliant.fury.codis.client;

import lombok.Data;

/**
 * @author by fury.
 * version 2020/4/2.
 */
@Data
public class JedisConfig {

    /**
     * 是否开启空闲资源检测
     */
    private boolean testWhileIdle = true;

    /**
     * 空闲资源的检测周期（单位为毫秒）-1代表（不检测）
     */
    private long timeBetweenEvictionRunsMillis = 30000L;

    /**
     * 资源池中资源的最小空闲时间（单位为毫秒），达到此值后空闲资源将被移除。
     * 默认值 10分钟
     */
    private long minEvictableIdleTimeMillis = 60000L;

    /**
     * 做空闲资源检测时, 每次检测资源的个数. 设置为-1, 就是对所有连接做空闲监测
     */
    private int numTestsPerEvictionRun = 3;

}
