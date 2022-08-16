package com.toy.coupon.api.mvc.model.vo

import javax.validation.constraints.Min

data class CouponPublishRequestVo(
    @field:Min(value = 0)
    val quantity: Long
)
