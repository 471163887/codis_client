package com.brilliant.fury.codis.client;

import io.codis.jodis.JedisResourcePool;
import io.codis.jodis.RoundRobinJedisPool;

/**
 * 初始化 JedisResourcePool
 *
 * @author by fury.
 * version 2020/4/2.
 */
public class JedisPoolInit {

    /**
     * 默认使用集群名称作为 poolName.
     */
    public void init(String zkAddr, String zkProxyDir) {
        CodisConfig codisConfig = new CodisConfig(zkAddr, zkProxyDir);
        init(zkProxyDir, codisConfig);
    }

    /**
     * 若需要修改了默认的Codis配置, 使用这个构造函数初始化.
     */
    public void init(CodisConfig codisConfig) {
        String zkProxyDir = codisConfig.getZkProxyDir();
        init(zkProxyDir, codisConfig);
    }

    public void init(String poolName, CodisConfig codisConfig) {
        JedisResourcePool jedisPool = RoundRobinJedisPool.create()
            .curatorClient(codisConfig.getZkAddr(), codisConfig.getZkSessionTimeoutMs())
            .zkProxyDir(codisConfig.getZkProxyDir())
            .build();
        JedisPoolManager.getInstance().addPool(poolName, jedisPool);
    }

}
