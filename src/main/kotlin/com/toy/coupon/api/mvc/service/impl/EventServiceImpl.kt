package com.toy.coupon.api.mvc.service.impl

import com.toy.coupon.api.mvc.model.entity.TbCoupon
import com.toy.coupon.api.mvc.model.entity.TbEvent
import com.toy.coupon.api.mvc.model.vo.EventRequestVo
import com.toy.coupon.api.mvc.repository.TbCouponRepository
import com.toy.coupon.api.mvc.repository.TbEventRepository
import com.toy.coupon.api.mvc.service.EventService
import org.springframework.stereotype.Service
import javax.persistence.EntityManager

@Service
class EventServiceImpl(
    private val tbEventRepository: TbEventRepository,
    private val tbCouponRepository: TbCouponRepository,
    private val entityManger: EntityManager
) : EventService {
    override fun addEvent(eventRequestVo: EventRequestVo) {
        assertNotExistSameEvent(eventRequestVo.couponQuantityString)

        TbEvent(eventName = eventRequestVo.eventName).apply {
            addCoupon(TbCoupon())
            tbEventRepository.save(this)
        }
    }

    private fun assertNotExistSameEvent(eventName: String) {
        tbEventRepository.findSameEvent(eventName)
    }
}
