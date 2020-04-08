package com.brilliant.fury.codis.client;

import redis.clients.jedis.AdvancedJedisCommands;
import redis.clients.jedis.BasicCommands;
import redis.clients.jedis.ClusterCommands;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.MultiKeyCommands;
import redis.clients.jedis.ScriptingCommands;
import redis.clients.jedis.SentinelCommands;

/**
 * @author by fury.
 * version 2020/4/7.
 */
public interface FuryJedis extends JedisCommands, MultiKeyCommands,
    AdvancedJedisCommands, ScriptingCommands, BasicCommands, ClusterCommands, SentinelCommands {

    Jedis getJedis();

}
