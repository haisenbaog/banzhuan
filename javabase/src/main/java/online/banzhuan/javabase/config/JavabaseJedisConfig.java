package online.banzhuan.javabase.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * redis配置属性
 * @author haisenbao
 * @date  2020/7/4
 */
@Data
@Component
@ConfigurationProperties(prefix = "javabase.redis")
public class JavabaseJedisConfig {
    private String host;
    private Integer port;
    private String password;
    private Integer db;
    private Integer maxTotal;
    private Integer maxIdle;
    private Integer timeout;
    private Integer defaultExpiration;
    private String namespace;
}