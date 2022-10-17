package com.toy.coupon.api.mvc.service.impl

import com.toy.coupon.api.mvc.model.vo.CouponPublishRequestVo
import com.toy.coupon.api.mvc.repository.TbCouponRepository
import com.toy.coupon.api.mvc.service.CouponService
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

@Service
class CouponServiceImpl(
    private val tbCouponRepository: TbCouponRepository,
    redis: RedisTemplate<String, String>
): CouponService {
    private val zset = redis.opsForZSet()
    private val value = redis.opsForValue()

    override fun initCoupon(couponPublishRequestVo: CouponPublishRequestVo) {
        value.set("quantity", couponPublishRequestVo.quantity.toString())

    }

    override fun getCouponQuantity(eventId: Long): String? {
        return value.get("EVENT:${eventId}")
    }

    override fun decreaseQuantity() {
        value.decrement("quantity", 1)
    }
}
