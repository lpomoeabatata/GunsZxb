package com.stylefeng.guns.modular.system.service.impl;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.stylefeng.guns.core.util.Utils;
import com.stylefeng.guns.modular.system.service.IRedisService;
import redis.clients.jedis.Jedis;

public class RedisServiceImpl implements IRedisService {

    @Override
    public void sadd(String key, Set<String> value) throws Exception {
        Utils f = Utils.createFactory();
        Jedis jedis = f.connection();
        for(String str: value){
            jedis.sadd(key, str);
        }
//      Iterator<String> it = value.iterator();
//      while (it.hasNext()) {
//       String str = it.next();
//       System.out.println(str);
//       jedis.sadd(key,str);
//      }

    }

    @Override
    public void set(String key, String value) throws Exception {
        Utils f = Utils.createFactory();
        Jedis jedis = f.connection();
        jedis.set(key,value);

    }

    @Override
    public void srem(String key, Set<String> value) throws Exception {
        Utils f = Utils.createFactory();
        Jedis jedis = f.connection();
        Iterator<String> it = value.iterator();
        while(it.hasNext()){
            String str = it.next();
            jedis.srem(key, str);
        }
    }

    @Override
    public String get(String key) throws Exception {
        Utils f = Utils.createFactory();
        Jedis jedis = f.connection();
        return jedis.get(key);
    }

    @Override
    public Long scard(String key) throws Exception {
        Utils f = Utils.createFactory();
        Jedis jedis = f.connection();
        return jedis.scard(key);
    }

    @Override
    public Set<String> smembers(String key) throws Exception {
        Utils f = Utils.createFactory();
        Jedis jedis = f.connection();
        return jedis.smembers(key);
    }

    @Override
    public boolean sismember(String key, String value) throws Exception {
        Utils f = Utils.createFactory();
        Jedis jedis = f.connection();
        return jedis.sismember(key,value);
    }

    @Override
    public String srandmember(String key) throws Exception {
        Utils f = Utils.createFactory();
        Jedis jedis = f.connection();
        return jedis.srandmember(key);
    }

    @Override
    public void hmset(String key, Map<String, String> map) throws Exception {
        Utils f = Utils.createFactory();
        Jedis jedis = f.connection();
        jedis.hmset(key,map);
    }

    @Override
    public Long hlen(String key) throws Exception {
        Utils f = Utils.createFactory();
        Jedis jedis = f.connection();
        return jedis.hlen(key);
    }

    @Override
    public Set<String> hkeys(String key) throws Exception {
        Utils f = Utils.createFactory();
        Jedis jedis = f.connection();
        return jedis.hkeys(key);
    }

    @Override
    public List<String> hvals(String key) throws Exception {
        Utils f = Utils.createFactory();
        Jedis jedis = f.connection();
        return jedis.hvals(key);
    }

    @Override
    public List<String> hmget(String key, String s1, String s2)
            throws Exception {
        Utils f = Utils.createFactory();
        Jedis jedis = f.connection();
        return jedis.hmget(key,s1,s2);
    }

    @Override
    public void hdel(String key, String s) throws Exception {
        Utils f = Utils.createFactory();
        Jedis jedis = f.connection();
        jedis.hdel(key,s);
    }

    @Override
    public void lpush(String key, List<String> list) throws Exception {
        Utils f = Utils.createFactory();
        Jedis jedis = f.connection();
        for(String s: list){
            jedis.lpush(key,s);
        }
    }

    @Override
    public List<String> lrange(String key, Long start, Long end)
            throws Exception {
        Utils f = Utils.createFactory();
        Jedis jedis = f.connection();
        return jedis.lrange(key, start, end);
    }

    @Override
    public void del(String key) throws Exception {
        Utils f = Utils.createFactory();
        Jedis jedis = f.connection();
        jedis.del(key);
    }

    @Override
    public void append(String key, String value) throws Exception {
        Utils f = Utils.createFactory();
        Jedis jedis = f.connection();
        jedis.append(key,value);
    }

}