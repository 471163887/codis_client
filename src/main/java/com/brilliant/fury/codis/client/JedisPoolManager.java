package com.brilliant.fury.codis.client;

import static com.brilliant.fury.common.Constants.FLT;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import io.codis.jodis.JedisResourcePool;
import java.util.Map;
import java.util.Optional;
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

    private Map<String, JedisResourcePool> jedisResourcePoolMap = Maps.newHashMap();

    private String firstPoolName;

    public Jedis getJedis() {
        Optional<Jedis> jedisOptional = getResource(firstPoolName);
        if (!jedisOptional.isPresent()) {
            throw new JedisException("[Get_Jedis_Resource_Fail]");
        }
        return jedisOptional.get();
    }

    public Optional<Jedis> getResource(String poolName) {
        try {
            JedisResourcePool jedisPool = jedisResourcePoolMap.get(poolName);
            if (null != jedisPool) {
                return Optional.of(jedisPool.getResource());
            } else {
                if (jedisResourcePoolMap.isEmpty()) {
                    log.info(FLT + "getResource]jedisPool未初始化");
                }
            }
        } catch (Throwable t) {
            log.error(FLT + "getResource]msg={}", t.getMessage(), t);
        }
        return Optional.empty();
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
