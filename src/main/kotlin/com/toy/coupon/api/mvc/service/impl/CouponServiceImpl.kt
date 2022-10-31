package com.toy.coupon.api.mvc.service.impl

import com.toy.coupon.api.mvc.common.exception.ErrorCode.NOT_ALLOW_PROVIDE_COUPON_ALREADY_PROVIDE_USER
import com.toy.coupon.api.mvc.common.exception.ErrorCode.NOT_ALLOW_PROVIDE_COUPON_THERE_IS_NO_COUPON
import com.toy.coupon.api.mvc.model.vo.CouponProvidePutVo
import com.toy.coupon.api.mvc.model.vo.CouponPublishRequestVo
import com.toy.coupon.api.mvc.repository.TbCouponRepository
import com.toy.coupon.api.mvc.service.CouponService
import org.redisson.api.RedissonClient
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.concurrent.TimeUnit.SECONDS

@Service
@Transactional(rollbackFor = [Exception::class])
class CouponServiceImpl(
    private val tbCouponRepository: TbCouponRepository,
    redis: RedisTemplate<String, String>,
    private val redissonClient: RedissonClient
) : CouponService {
    private val value = redis.opsForValue()

    override fun initCoupon(couponPublishRequestVo: CouponPublishRequestVo) {
        value.set("quantity", couponPublishRequestVo.quantity.toString())
    }

    override fun getCouponQuantity(eventId: Long): String? {
        return value.get("EVENT:${eventId}")
    }

    override fun provideCoupon(couponProvidePutVo: CouponProvidePutVo) {
        if ((value.get("EVENT:${couponProvidePutVo.eventId}")?.toInt() ?: 0) <= 0) {
            throw NOT_ALLOW_PROVIDE_COUPON_THERE_IS_NO_COUPON.of()
        }

        val lock = redissonClient.getLock("coupon:lock")

        runCatching {
            if (lock.tryLock(10, 10, SECONDS)) {
                // 유저가 해당 이벤트에 발급받은 쿠폰이 있는지
                if (tbCouponRepository.findCouponProvidedToUser(couponProvidePutVo.eventId, couponProvidePutVo.userId).isNotEmpty()) {
                    throw NOT_ALLOW_PROVIDE_COUPON_ALREADY_PROVIDE_USER.of()
                } else {
                    val couponId = tbCouponRepository.findAllocableCoupon(couponProvidePutVo.eventId)?.couponId
                        ?: throw NOT_ALLOW_PROVIDE_COUPON_THERE_IS_NO_COUPON.of()

                    tbCouponRepository.provideCoupon(couponProvidePutVo.eventId, couponId, couponProvidePutVo.userId)
                }

                value.decrement("EVENT:${couponProvidePutVo.eventId}", 1)
            }
        }.getOrElse { ex ->
            throw ex
        }.also {
            // 락 해제
            if (lock != null && lock.isLocked) {
                lock.unlock()
            }
        }
    }
}
