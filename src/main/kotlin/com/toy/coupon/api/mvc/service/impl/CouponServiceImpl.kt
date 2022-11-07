package com.toy.coupon.api.mvc.service.impl

import com.toy.coupon.api.mvc.common.exception.ErrorCode.NOT_ALLOW_PROVIDE_COUPON_ALREADY_PROVIDE_USER
import com.toy.coupon.api.mvc.common.exception.ErrorCode.NOT_ALLOW_PROVIDE_COUPON_THERE_IS_NO_COUPON
import com.toy.coupon.api.mvc.component.RedisHelper
import com.toy.coupon.api.mvc.model.entity.TbCoupon
import com.toy.coupon.api.mvc.model.vo.CouponProvidePutVo
import com.toy.coupon.api.mvc.repository.TbCouponRepository
import com.toy.coupon.api.mvc.service.CouponService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = [Exception::class])
class CouponServiceImpl(
    private val tbCouponRepository: TbCouponRepository,
    private val redisHelper: RedisHelper
) : CouponService {
    override fun getCouponQuantity(eventId: Long): Long? {
        return redisHelper.getValue<Long>("EVENT:$eventId")
    }

    override fun provideCoupon(couponProvidePutVo: CouponProvidePutVo) {
        redisHelper.lock("COUPON:LOCK") {
            if (redisHelper.getValue<Long>("EVENT:${couponProvidePutVo.eventId}") <= 0) {
                throw NOT_ALLOW_PROVIDE_COUPON_THERE_IS_NO_COUPON.of()
            }

            // 유저가 해당 이벤트에 발급받은 쿠폰이 있는지
            if (tbCouponRepository.findCouponProvidedToUser(couponProvidePutVo.eventId, couponProvidePutVo.userId).isNotEmpty()) {
                throw NOT_ALLOW_PROVIDE_COUPON_ALREADY_PROVIDE_USER.of()
            } else {
                val couponId = tbCouponRepository.findAllocableCoupon(couponProvidePutVo.eventId)?.couponId
                    ?: throw NOT_ALLOW_PROVIDE_COUPON_THERE_IS_NO_COUPON.of()

                tbCouponRepository.provideCoupon(couponProvidePutVo.eventId, couponId, couponProvidePutVo.userId)

                redisHelper.decrease("EVENT:${couponProvidePutVo.eventId}")
            }
        }
    }

    override fun getCoupons(eventId: Long): List<TbCoupon> {
        return tbCouponRepository.findCoupons(eventId)
    }
}
