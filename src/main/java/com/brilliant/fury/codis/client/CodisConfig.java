package com.brilliant.fury.codis.client;

import lombok.Data;

/**
 * @author by fury.
 * version 2020/4/2.
 */
@Data
public class CodisConfig {

    private JedisPoolConfig jedisPoolConfig;

    private JedisConfig jedisConfig;

    /**
     * 格式为ip:port, 例如127.0.0.1:2181
     */
    private String zkAddr;

    /**
     * 格式为:{项目名称} 例如codis-demo2, 在ZK上的节点信息为: /jodis/codis-demo2
     */
    private String zkProxyDir;

    /**
     * 默认5s
     */
    private int zkSessionTimeoutMs = 5000;

    private static final String JODIS_PREFIX = "/jodis/";

    public CodisConfig(String zkAddr, String zkProxyDir) {
        this.zkAddr = zkAddr;
        this.zkProxyDir = JODIS_PREFIX + zkProxyDir;
    }

    public CodisConfig(String zkAddr, String zkProxyDir, JedisPoolConfig jedisPoolConfig,
        JedisConfig jedisConfig) {
        this.zkAddr = zkAddr;
        this.zkProxyDir = JODIS_PREFIX + zkProxyDir;
        this.jedisPoolConfig = jedisPoolConfig;
        this.jedisConfig = jedisConfig;
    }

}
