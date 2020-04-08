package com.brilliant.fury;

import com.brilliant.fury.codis.client.CodisClient;
import com.brilliant.fury.codis.client.JedisPoolInit;
import com.brilliant.fury.codis.client.FuryJedis;
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
     * 优点: 不需要使用try语句, 不需要调用 jedis.close, 避免Jedis连接泄露。
     */
    @Test
    public void codisClientDemo() {
        JedisPoolInit jedisPoolInit = new JedisPoolInit();
        jedisPoolInit.init("127.0.0.1:2181", "codis-demo2");
        CodisClient codisClient = new CodisClient("codis-demo2");
        FuryJedis furyJedis = codisClient.furyJedis();
        furyJedis.set("you_are_brilliant", "_is_true");
        String value = furyJedis.get("you_are_brilliant");
        System.out.println(value);
        String value2 = furyJedis.getSet("you_are_brilliant", "_is_true_2");
        System.out.println(value2);
        String value3 = furyJedis.getSet("you_are_brilliant", "_is_true_3");
        System.out.println(value3);
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
