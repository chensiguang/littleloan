package com.hexin.core.util.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hexin.core.util.properties.Property;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.exceptions.JedisException;

public class ShardedRedisUtil {
	
	private ShardedJedisPool pool = null;
	private  List<JedisShardInfo> jdsInfoList =new ArrayList<JedisShardInfo>();
	public ShardedJedisPool getPool() {
        if (pool == null || pool.isClosed()) {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(Integer.parseInt(Property.getProperty("redis.maxTotal")));
            config.setMaxIdle(Integer.parseInt(Property.getProperty("redis.maxIdle")));
            config.setMaxWaitMillis(Integer.parseInt(Property.getProperty("redis.maxWaitMillis")));
            config.setTestOnBorrow(false);
            setIp(Property.getProperty("redis.ip"));
            pool = new ShardedJedisPool(config, jdsInfoList);
        }
        return pool;
    }
	private String ip;
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
		if(!ip.equals("")){
			try{
				String[] ipPortList = ip.split(",");
				for(String ipPort : ipPortList){
					String sip = ipPort.split(":")[0];
					String port = ipPort.split(":")[1];
					JedisShardInfo shardJedis = new JedisShardInfo(sip, port);
					this.jdsInfoList.add(shardJedis);
				}
			}
			catch(Exception ex){
				
			}
		}
	}
	
	public  List<JedisShardInfo> getJdsInfoList() {
		return jdsInfoList;
	}

	public  void setJdsInfoList(List<JedisShardInfo> jdsInfoList) {
		this.jdsInfoList = jdsInfoList;
	}
	
	public  String get(String key){
        String value = null;
        ShardedJedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	pool.close();
        }
        
        return value;
    }
	
	public  String set(String key, String value) {
		ShardedJedis jedis = null;
        boolean success = true;
        try {
        	pool = getPool();
            jedis = pool.getResource();
            return jedis.set(key, value);
        }catch (JedisException e) {
            success  = false;
            if(jedis != null){
                pool.close();
            }
            throw e;
        }finally{
            if(success && jedis != null){
                pool.close();
            }
        }
    }
	
	public  Long removeKey(String key){
		ShardedJedis jedis = null;
        boolean success = true;
        try {
        	pool = getPool();
            jedis = pool.getResource();
            return jedis.del(key);
        }catch (JedisException e) {
            success  = false;
            if(jedis != null){
                pool.close();
            }
            throw e;
        }finally{
            if(success && jedis != null){
                pool.close();
            }
        }
	}
	
	
	public  String setMap(String key, Map<String, String> hash){
		ShardedJedis jedis = null;
        boolean success = true;
        try {
        	pool = getPool();
            jedis = pool.getResource();
            return jedis.hmset(key, hash);
        }catch (JedisException e) {
            success  = false;
            if(jedis != null){
                pool.close();
            }
            throw e;
        }finally{
            if(success && jedis != null){
                pool.close();
            }
        }
	}
	
	public  String setMap(String key, String field, String value){
		ShardedJedis jedis = null;
        boolean success = true;
        try {
        	pool = getPool();
            jedis = pool.getResource();
            return jedis.hset(key, field, value).toString();
        }catch (JedisException e) {
            success  = false;
            if(jedis != null){
                pool.close();
            }
            throw e;
        }finally{
            if(success && jedis != null){
                pool.close();
            }
        }
	}
	
	public  String getMap(String key, String field){
		ShardedJedis jedis = null;
        boolean success = true;
        try {
        	pool = getPool();
            jedis = pool.getResource();
            return jedis.hget(key, field);
        }catch (JedisException e) {
            success  = false;
            if(jedis != null){
                pool.close();
            }
            throw e;
        }finally{
            if(success && jedis != null){
                pool.close();
            }
        }
	}
	
	public  Long removeMapField(String key, String... fields){
		ShardedJedis jedis = null;
        boolean success = true;
        try {
        	pool = getPool();
            jedis = pool.getResource();
            return jedis.hdel(key, fields);
        }catch (JedisException e) {
            success  = false;
            if(jedis != null){
                pool.close();
            }
            throw e;
        }finally{
            if(success && jedis != null){
                pool.close();
            }
        }
	}
	
	public  Long lPush(String listName, String value){
		ShardedJedis jedis = null;
        boolean success = true;
        try {
        	pool = getPool();
            jedis = pool.getResource();
            return jedis.lpush(listName, value);
        }catch (JedisException e) {
            success  = false;
            if(jedis != null){
                pool.close();
            }
            throw e;
        }finally{
            if(success && jedis != null){
                pool.close();
            }
        }
	}
	public  String lPop(String listName){
		ShardedJedis jedis = null;
        boolean success = true;
        try {
        	pool = getPool();
            jedis = pool.getResource();
            return jedis.lpop(listName);
        }catch (JedisException e) {
            success  = false;
            if(jedis != null){
                pool.close();
            }
            throw e;
        }finally{
            if(success && jedis != null){
                pool.close();
            }
        }
	}
	
	public  Long rPush(String listName, String value){
		ShardedJedis jedis = null;
        boolean success = true;
        try {
        	pool = getPool();
            jedis = pool.getResource();
            return jedis.rpush(listName, value);
        }catch (JedisException e) {
            success  = false;
            if(jedis != null){
                pool.close();
            }
            throw e;
        }finally{
            if(success && jedis != null){
                pool.close();
            }
        }
	}
	public  String rPop(String listName){
		ShardedJedis jedis = null;
        boolean success = true;
        try {
        	pool = getPool();
            jedis = pool.getResource();
            return jedis.rpop(listName);
        }catch (JedisException e) {
            success  = false;
            if(jedis != null){
                pool.close();
            }
            throw e;
        }finally{
            if(success && jedis != null){
                pool.close();
            }
        }
	}
}
