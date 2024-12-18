package rest_controller;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

public class SpringRedisHelper {

    private static final String HOST_NAME = "127.0.0.1";
    private static final int PORT = 6379;

    public static RedisConnection getJedisConnection() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(HOST_NAME, PORT);
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(config);
        return connectionFactory.getConnection();
    }

    public static StringRedisTemplate getStringJedisTemplate() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(HOST_NAME, PORT);
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(config);
        return new StringRedisTemplate(connectionFactory);
    }
}
