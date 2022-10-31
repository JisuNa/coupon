package com.toy.coupon.api.mvc.service

import com.toy.coupon.api.mvc.model.vo.CouponProvidePutVo
import com.toy.coupon.api.mvc.model.vo.CouponPublishRequestVo

interface CouponService {
    fun initCoupon(couponPublishRequestVo: CouponPublishRequestVo)
    fun getCouponQuantity(eventId: Long): String?
    fun provideCoupon(couponProvidePutVo: CouponProvidePutVo)
}
