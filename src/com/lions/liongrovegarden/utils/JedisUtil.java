package com.lions.liongrovegarden.utils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;

/**该方法主要用于单线程的直连操作
 * Created by LiaoJS on 2016/5/17.
 */
public class JedisUtil {
    private static final Log logger = LogFactory.getLog(JedisUtil.class);

    /**REDIS IP地址*/
    private final static String urlIp = ConfigProperty.getForAbs("redis.connection.hostName","redis.properties");
    private final static String password =   ConfigProperty.getForAbs("redis.connection.password", "redis.properties");

    /**REDIS 缓存数据有效时间（单位：秒  如：86400=24小时）*/
    private final static String ExpiryTime = ConfigProperty.getForAbs("Redis.ExpiryTime", "redis.properties");
    private final static int port =  Integer.parseInt(ConfigProperty.getForAbs("redis.connection.port", "redis.properties"));

   // private int port = 6379;
    private Jedis jedis;

    public JedisUtil(){
        //logger.info(this.urlIp+"--"+this.port);
    	if(null == jedis){
    		 this.jedis = new Jedis(this.urlIp,this.port);
    	}
      //  if(password!=null && !"".equals(password))this.jedis.auth(this.password);
    }

    /**
     * redis存值
     * @param key
     * @param value
     */
    public void setValue(String key,String value){
        this.jedis.set(key, value);
    }

    /**
     * redis存值（原有值追加）
     * @param key
     * @param value
     */
    public void append(String key,String value){
        this.jedis.append(key, value);
    }

    /**
     * redis取值
     * @param key
     */
    public String getValue(String key){
        try {
            return this.jedis.get(key);
        }catch (Exception e){
            logger.error(key);
            return "";
        }
    }

    /**
     * redis删除
     * @param key
     */
    public void deleteByKey(String key){
        this.jedis.del(key);
    }

    /**
     * redis设置key的存活时间（有效时间）,时间为秒,指向值
     * @param key
     */
    public void setex(String key,String value){
        int expirytime = Integer.parseInt(ExpiryTime);
        this.jedis.setex(key, expirytime, value);
    }

    /**
     * redis设置key的存活时间（有效时间）,时间为秒,指向结果集
     * @param key
     */
    public void expire(String key){
        int expirytime = Integer.parseInt(ExpiryTime);
        this.jedis.expire(key, expirytime);
    }

    /**
     * list 集合存储
     * @param listKey
     * @param key
     * @param value
     */
    public void hsetList(String listKey,String key,String value){
        this.jedis.hset(listKey, key, value);
    }

    /**
     * list 获取指定的值
     * @param listKey
     * @param key
     */
    public String hgetList(String listKey,String key){
        return this.jedis.hget(listKey, key);
    }

    /**
     * list 删除指定的值
     * @param listKey
     * @param key
     */
    public void hdelList(String listKey,String key){
        this.jedis.hdel(listKey, key);
    }

    /**
     * 判断某个key是否存在
     * @param key
     * @return
     */
    public boolean exists(String key){
        return this.jedis.exists(key);
    }

    /**
     * 判断list集合中的某个key是否存在
     * @param listKey
     * @param key
     * @return
     */
    public boolean hexists(String listKey,String key){
        return this.jedis.hexists(listKey, key);
    }

    /**
     * 根据sid判断是否是有效时间
     * @param key
     * @return
     */
    public boolean ttl(String key){
        boolean res = false;
        if(this.jedis.ttl(key) > 0){
            res = true;
        }else{
            res = false;
        }
        return res;
    }


    /**
     * 关掉redis连接
     */
    public void CloseRedisCon(){
        this.jedis.close();
    }

    /**
     * 清空所有数据
     */
    public void FlushAll(){
        this.jedis.flushAll();
    }

    /**
     * 清空库中所有数据
     */
    public void FlushDB(){
        this.jedis.flushDB();
    }
}
