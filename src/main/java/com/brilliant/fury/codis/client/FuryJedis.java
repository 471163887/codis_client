package com.brilliant.fury.codis.client;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCommands;

/**
 * @author by fury.
 * version 2020/4/7.
 */
public interface FuryJedis extends JedisCommands {

    Jedis getJedis();

}
