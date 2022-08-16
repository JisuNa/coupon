package com.toy.coupon.api.config

import org.springframework.boot.autoconfigure.data.redis.RedisProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
@EnableConfigurationProperties(RedisProperties::class)
class RedisConfig {

    @Bean
    fun redisTemplate(redisConnectionFactory: LettuceConnectionFactory): RedisTemplate<*, *> {
        return RedisTemplate<ByteArray, ByteArray>().apply {
            setConnectionFactory(redisConnectionFactory)

            keySerializer = StringRedisSerializer()
            hashKeySerializer = StringRedisSerializer()
            valueSerializer = StringRedisSerializer()
            hashValueSerializer = StringRedisSerializer()
        }
    }
}