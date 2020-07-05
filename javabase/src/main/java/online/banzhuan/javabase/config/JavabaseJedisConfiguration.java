package online.banzhuan.javabase.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;

/**
 * redis连接池
 * @author haisenbao
 * @date 2020/7/4 23:08
 */
@Slf4j
@Configuration
public class JavabaseJedisConfiguration {

    @Resource
    private JavabaseJedisConfig javabaseJedisConfig;

    @Bean
    public JedisPool getJedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(javabaseJedisConfig.getMaxTotal());
        jedisPoolConfig.setMaxIdle(javabaseJedisConfig.getMaxIdle());
        jedisPoolConfig.setTestOnBorrow(true);

        return new JedisPool(jedisPoolConfig,
                javabaseJedisConfig.getHost(),
                javabaseJedisConfig.getPort(),
                javabaseJedisConfig.getTimeout(),
                javabaseJedisConfig.getPassword(),
                javabaseJedisConfig.getDb());
    }

}
