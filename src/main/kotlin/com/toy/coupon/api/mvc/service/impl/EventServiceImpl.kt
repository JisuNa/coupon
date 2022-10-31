package com.toy.coupon.api.mvc.service.impl

import com.toy.coupon.api.mvc.model.entity.TbCoupon
import com.toy.coupon.api.mvc.model.entity.TbEvent
import com.toy.coupon.api.mvc.model.vo.EventRequestVo
import com.toy.coupon.api.mvc.repository.TbEventRepository
import com.toy.coupon.api.mvc.service.EventService
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class EventServiceImpl(
    private val tbEventRepository: TbEventRepository,
    redis: RedisTemplate<String, String>
) : EventService {
    private val value = redis.opsForValue()

    override fun addEvent(eventRequestVo: EventRequestVo) {
        assertNotExistSameEvent(eventRequestVo.eventName)

        val event = TbEvent(eventName = eventRequestVo.eventName).apply {
            for (idx in 0 until eventRequestVo.couponQuantity ) {
                addCoupon(TbCoupon())
            }
            tbEventRepository.save(this)
        }

        value.set("EVENT:${event.eventId}", eventRequestVo.couponQuantityString)
    }

    private fun assertNotExistSameEvent(eventName: String) {
        if (tbEventRepository.findSameEvent(eventName).isNotEmpty()) {
            throw Exception()
        }
    }
}
