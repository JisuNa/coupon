package com.toy.coupon.api.mvc.controller

import com.toy.coupon.api.mvc.common.ResponseVo
import com.toy.coupon.api.mvc.model.vo.CouponProvidePutVo
import com.toy.coupon.api.mvc.service.CouponService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("coupon")
class CouponController(
    private val couponService: CouponService
) {
    @GetMapping("/quantity", name = "수량 조회")
    fun getCouponQuantity(
        @RequestParam eventId: Long
    ): ResponseVo<Long>? {
        return ResponseVo(couponService.getCouponQuantity(eventId))
    }

    @PutMapping(name = "쿠폰 지급")
    fun provideCoupon(@RequestBody couponProvidePutVo: CouponProvidePutVo) {
        couponService.provideCoupon(couponProvidePutVo)
    }
}
