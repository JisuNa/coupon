package com.toy.coupon.api.mvc.service

import com.toy.coupon.api.mvc.model.vo.EventRequestVo

interface EventService {
    fun addEvent(eventRequestVo: EventRequestVo)
}