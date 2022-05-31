package ci.palmafrique.vonabri.utils.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import ci.palmafrique.vonabri.utils.ParamsUtils;
import ci.palmafrique.vonabri.utils.dto.UserDto;

@Configuration
public class RedisConfig {
	@Autowired
	private ParamsUtils paramsUtils;
	@SuppressWarnings("static-access")
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(paramsUtils.getRedisServerHost(),
				paramsUtils.getRedisServerPort());
		JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder().usePooling().build();
		JedisConnectionFactory factory = new JedisConnectionFactory(configuration, jedisClientConfiguration);
		factory.afterPropertiesSet();
		return factory;
	}
	@Bean
	public RedisTemplate<String, UserDto> redisTemplate() {
		final RedisTemplate<String, UserDto> template = new RedisTemplate<String, UserDto>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setKeySerializer(new StringRedisSerializer());
		template.setHashValueSerializer(new Jackson2JsonRedisSerializer<UserDto>(UserDto.class));
		template.setValueSerializer(new Jackson2JsonRedisSerializer<UserDto>(UserDto.class));
		return template;
	}

}
