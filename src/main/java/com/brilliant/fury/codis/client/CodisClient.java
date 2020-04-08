package com.brilliant.fury.codis.client;

/**
 * 选择数据源，并绑定Proxy
 *
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

    public FuryJedis furyJedis() {
        CodisClientProxy codisClientProxy = new CodisClientProxy();
        return (FuryJedis) codisClientProxy.bind(furyJedis);
    }

}
