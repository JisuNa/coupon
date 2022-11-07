package com.toy.coupon.api.mvc.component

import org.redisson.api.RLock
import org.redisson.api.RedissonClient
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit.SECONDS

@Component
class RedisHelper(
    private val redissonClient: RedissonClient
) {
    fun <T> getValue(key: String): T {
        return redissonClient.getBucket<T>(key).get()
    }

    fun <T> save(key: String, value: T) {
        redissonClient.getBucket<T>(key).set(value)
    }

    fun decrease(key: String) {
        getValue<Long>(key).dec()
    }

    fun <T> lock(key: String, function: () -> T): T {
        val lock = redissonClient.getLock("$key$LOCK_SUFFIX")

        runCatching {
            lock.tryLock(WAIT_TIME, LEASE_TIME, SECONDS)
        }.getOrElse { ex ->
            throw ex
        }.also {
            unlock(lock)
        }

        return function()
    }

    private fun unlock(lock: RLock?) {
        if (lock != null && lock.isLocked) {
            lock.unlock()
        }
    }

    companion object {
        private const val LOCK_SUFFIX = ":lock"
        private const val WAIT_TIME = 10L
        private const val LEASE_TIME = 10L
    }
}
