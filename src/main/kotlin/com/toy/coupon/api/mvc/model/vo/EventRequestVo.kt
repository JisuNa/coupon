package com.toy.coupon.api.mvc.model.vo

import javax.validation.constraints.Min

class EventRequestVo(
    val eventName: String,
    @field:Min(value = 1)
    val couponQuantity: Long
) {
    val couponQuantityString = couponQuantity.toString()
}