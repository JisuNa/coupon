package com.toy.coupon.api.mvc.controller

import com.toy.coupon.api.mvc.model.vo.EventRequestVo
import com.toy.coupon.api.mvc.service.EventService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/event")
class EventController(
    private val eventService: EventService
) {
    @PostMapping(name = "이벤트 등록")
    fun registerEvent(@RequestBody @Valid eventRequestVo: EventRequestVo) {
        eventService.addEvent(eventRequestVo)
    }
}