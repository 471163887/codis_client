package com.brilliant.fury.codis.client;

import static com.brilliant.fury.common.Constants.FLT;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import io.codis.jodis.JedisResourcePool;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisException;

/**
 * 一个项目里可能会使用多个Codis集群.
 *
 * @author by fury.
 * version 2020/4/2.
 */
@Slf4j
public class JedisPoolManager {

    private static final String JEDIS_NULL_ERROR = "getResource_fail_jedisPool_is_empty]poolName= ";

    private Map<String, JedisResourcePool> jedisResourcePoolMap = Maps.newHashMap();

    private String firstPoolName;

    public Jedis getJedis() {
        return getResource(firstPoolName);
    }

    public Jedis getResource(String poolName) {
        try {
            JedisResourcePool jedisPool = jedisResourcePoolMap.get(poolName);
            if (null != jedisPool) {
                return jedisPool.getResource();
            }
            String errorMsg = FLT + JEDIS_NULL_ERROR + poolName;
            log.error(errorMsg);
            throw new JedisException(errorMsg);
        } catch (Throwable t) {
            log.error(FLT + "getResource_fail]msg={}", t.getMessage(), t);
            throw new JedisException(t);
        }
    }

    public synchronized void addPool(String poolName, JedisResourcePool jedisPool) {
        if (Strings.isNullOrEmpty(firstPoolName)) {
            this.firstPoolName = poolName;
        }
        jedisResourcePoolMap.put(firstPoolName, jedisPool);
    }

    public String getFirstPoolName() {
        return firstPoolName;
    }

    public static JedisPoolManager getInstance() {
        return poolManager;
    }

    private static JedisPoolManager poolManager = new JedisPoolManager();

    private JedisPoolManager() {
    }
}
