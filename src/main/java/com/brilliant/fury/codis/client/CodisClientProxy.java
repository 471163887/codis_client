package com.brilliant.fury.codis.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 * @author by fury.
 * version 2020/4/7.
 */
public class CodisClientProxy implements InvocationHandler {

    private static final Logger log = LoggerFactory.getLogger(CodisClientProxy.class);

    private Object jedisCommands;

    public Object bind(Object delegate) {
        this.jedisCommands = delegate;
        return Proxy.newProxyInstance(this.jedisCommands.getClass().getClassLoader(),
            this.jedisCommands.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            FuryJedisImpl zzJedis = (FuryJedisImpl) this.jedisCommands;
            try (Jedis jedis = zzJedis.getJedis()) {
                return method.invoke(jedis, args);
            } catch (Throwable t) {
                log.error("{}", t.getMessage(), t);
                return null;
            }
        } catch (Throwable t) {
            log.error("{}", t.getMessage(), t);
        }
        return null;
    }

}
