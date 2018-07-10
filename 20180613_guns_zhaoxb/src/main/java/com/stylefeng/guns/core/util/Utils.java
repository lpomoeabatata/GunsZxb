package com.stylefeng.guns.core.util;

import redis.clients.jedis.Jedis;
  
public class Utils {  
    private static final String ip = "192.168.0.154";
    private static final Integer port = 6379;  
      
    public  Jedis connection() throws Exception{  
        Jedis jedis = new Jedis(ip,port);  
        return jedis;  
    }  
       
    public static Utils createFactory() throws Exception{  
        return new Utils();  
    }  
}
