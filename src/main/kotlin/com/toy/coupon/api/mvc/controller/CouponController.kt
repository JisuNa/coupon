package com.toy.coupon.api.mvc.controller

import com.toy.coupon.api.mvc.common.ResponseVo
import com.toy.coupon.api.mvc.model.vo.CouponProvidePutVo
import com.toy.coupon.api.mvc.service.CouponService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

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
