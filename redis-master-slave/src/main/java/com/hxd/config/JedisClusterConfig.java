package com.hxd.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

@Configuration
public class JedisClusterConfig{

    private  Set<String> sentinels;
    private  JedisPoolConfig jedisPoolConfig;

    @Value("${spring.redis.address}")
    private  String redisAddress;

    @Value("${spring.redis.pool.max-total}")
    private  int maxTotal;

    @Value("${spring.redis.pool.max-idle}")
    private  int maxIdle;

    @Value("${spring.redis.pool.max-wait}")
    private  long maxWaitMillis;

    @Value("${spring.redis.pool.test-on-borrow}")
    private  boolean testOnBorrow;

    @Value("${spring.redis.pool.test-on-return}")
    private  boolean testOnReturn;

    @Value("${spring.redis.pool.test-while-idle}")
    private  boolean testWhileIdle;


    @Value("${spring.redis.pool.min-evictable-idle-timeMillis}")
    private  long minEvictableIdleTimeMillis;

    @Value("${spring.redis.pool.time-between-eviction-runsMillis}")
    private  long timeBetweenEvictionRunsMillis;

    @Value("${spring.redis.pool.num-tests-perEviction-run}")
    private  int numTestsPerEvictionRun;

    @PostConstruct
    public void init(){
        //redis 属性配置
        jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        jedisPoolConfig.setTestOnReturn(testOnReturn);
        jedisPoolConfig.setTestWhileIdle(testWhileIdle);
        jedisPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        jedisPoolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);

        sentinels = new HashSet<>();
        redisAddress=redisAddress.replace("，",",");
        StringTokenizer tokenizer = new StringTokenizer(redisAddress, ",");
        while (tokenizer.hasMoreTokens()){
            sentinels.add(tokenizer.nextToken());
        }
    }



/*    static {
        //redis 属性配置
        jedisPoolConfig = new JedisPoolConfig();
        System.out.println("maxTotal:"+maxTotal);
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        jedisPoolConfig.setTestOnReturn(testOnReturn);
        jedisPoolConfig.setTestWhileIdle(testWhileIdle);
        jedisPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        jedisPoolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);

        sentinels = new HashSet<>();
        System.out.println("ADDRESS："+redisAddress);
        redisAddress=redisAddress.replace("，",",");
        StringTokenizer tokenizer = new StringTokenizer(redisAddress, ",");
        while (tokenizer.hasMoreTokens()){
            sentinels.add(tokenizer.nextToken());
        }

        //jedisPool = new JedisSentinelPool("master1", sentinels, config);

    }*/

    @Bean
    public JedisSentinelPool JedisSentinelPoolFactory(){
        return new JedisSentinelPool("mymaster", sentinels, jedisPoolConfig);
    }

}
