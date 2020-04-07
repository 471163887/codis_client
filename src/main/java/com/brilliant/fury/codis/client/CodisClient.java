package com.brilliant.fury.codis.client;

/**
 * @author by fury.
 * version 2020/4/7.
 */
public class CodisClient {

    private FuryJedis furyJedis;

    public CodisClient() {
        this.furyJedis = new FuryJedisImpl();
    }

    public CodisClient(String poolName) {
        this.furyJedis = new FuryJedisImpl(poolName);
    }

    public FuryJedis zzjedis() {
        CodisClientProxy codisClientProxy = new CodisClientProxy();
        return (FuryJedis) codisClientProxy.bind(furyJedis);
    }

}
