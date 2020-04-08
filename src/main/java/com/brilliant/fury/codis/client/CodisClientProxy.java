package com.brilliant.fury.codis.client;

import static com.brilliant.fury.common.Constants.FLT;

import com.google.common.collect.Sets;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Set;
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

    private static final Set<String> METHOD_SET = Sets.newHashSet("getJedis");

    public Object bind(Object delegate) {
        this.jedisCommands = delegate;
        return Proxy.newProxyInstance(this.jedisCommands.getClass().getClassLoader(),
            this.jedisCommands.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            FuryJedisImpl zzJedis = (FuryJedisImpl) this.jedisCommands;
            String methodName = method.getName();
            if (METHOD_SET.contains(methodName)) {
                return method.invoke(zzJedis, args);
            } else {
                try (Jedis jedis = zzJedis.getJedis()) {
                    return method.invoke(jedis, args);
                } catch (Throwable t) {
                    log.error(FLT + "execute_fail]method={},args={},errmsg={}", methodName,
                        Arrays.toString(args), t.getMessage(), t);
                }
            }
        } catch (Throwable t) {
            log.error(FLT + "invoke_fail]errmsg={}", t.getMessage(), t);
        }
        return null;
    }

}
