# CodisClient
## 背景
    在使用Coids客户端Jodis时, 遇到了一些问题, 比如：
    1. 在每次使用前，需要调用 try(Jedis jedis = jedisPool.getResource())
    2. codis mget的性能不佳, 但是用法简单已经被开发的同学们先入为主.
    3. 有些复杂但通用的操作原生的Jedis并不支持.