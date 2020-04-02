package com.brilliant.fury;

import com.brilliant.fury.codis.client.CodisClient;
import com.brilliant.fury.codis.client.JedisPoolInit;
import io.codis.jodis.JedisResourcePool;
import io.codis.jodis.RoundRobinJedisPool;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * 使用例子.
 */
public class Demos {

    /**
     * CodisClient 使用 Demo
     * 区别: 不需要使用try语句, 避免Jedis连接泄露。
     */
    @Test
    public void codisClientDemo() {
        JedisPoolInit jedisPoolInit = new JedisPoolInit();
        jedisPoolInit.init("127.0.0.1:2181", "codis-demo2");

        CodisClient codisClient = CodisClient.getInstance();
        codisClient.set("you_are_brilliant", "_is_true");
        String value = codisClient.get("you_are_brilliant");
        System.out.println(value);
    }

    /**
     * 官网Demo
     */
    @Test
    public void codisLabDemo() {
        JedisResourcePool jedisPool = RoundRobinJedisPool.create()
            .curatorClient("127.0.0.1:2181", 30000).zkProxyDir("/jodis/codis-demo2").build();
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.set("imkey", "imvalue");
            String value = jedis.get("imkey");
            System.out.println(value);
        }
    }



}
