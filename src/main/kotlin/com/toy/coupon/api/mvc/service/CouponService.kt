package com.toy.coupon.api.mvc.service

import com.toy.coupon.api.mvc.model.entity.TbCoupon
import com.toy.coupon.api.mvc.model.vo.CouponProvidePutVo

interface CouponService {
    fun getCouponQuantity(eventId: Long): Long?
    fun provideCoupon(couponProvidePutVo: CouponProvidePutVo)
    fun getCoupons(eventId: Long): List<TbCoupon>
}
