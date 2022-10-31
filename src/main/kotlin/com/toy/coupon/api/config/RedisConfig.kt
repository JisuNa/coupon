package com.toy.coupon.api.config

import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.config.Config
import org.springframework.beans.factory.annotation.Value
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

    @Value("\${spring.redis.host}")
    private lateinit var host: String

    @Value("\${spring.redis.port}")
    private lateinit var port: String

    @Bean
    fun redisTemplate(): RedisTemplate<*, *> {
        return RedisTemplate<ByteArray, ByteArray>().apply {
            setConnectionFactory(LettuceConnectionFactory(host, port.toInt()))

            keySerializer = StringRedisSerializer()
            hashKeySerializer = StringRedisSerializer()
            valueSerializer = StringRedisSerializer()
            hashValueSerializer = StringRedisSerializer()
        }
    }

    @Bean
    fun redissonClient(): RedissonClient {
        val redisConfig = Config()
        redisConfig.useSingleServer().apply {
            address = "redis://$host:$port"
            connectionMinimumIdleSize = 5
            connectionMinimumIdleSize = 5
        }
        return Redisson.create(redisConfig)
    }
}
