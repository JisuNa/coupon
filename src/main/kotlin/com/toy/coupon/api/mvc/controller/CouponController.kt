package com.toy.coupon.api.mvc.controller

import com.toy.coupon.api.mvc.model.vo.CouponPublishRequestVo
import com.toy.coupon.api.mvc.service.CouponService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("coupon")
class CouponController(
    private val couponService: CouponService
) {
    @PostMapping(name = "쿠폰 발행")
    fun initCoupon(@RequestBody @Valid couponPublishRequestVo: CouponPublishRequestVo) {
        couponService.initCoupon(couponPublishRequestVo)
    }

    @GetMapping("/quantity", name = "수량 조회")
    fun getCouponQuantity(): String? {
        return couponService.getCouponQuantity()
    }

    @PutMapping("/quantity/decrease")
    fun decreaseQuantity() {
        couponService.decreaseQuantity()
    }

}