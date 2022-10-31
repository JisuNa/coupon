package com.toy.coupon.api.mvc.repository

import com.toy.coupon.api.mvc.model.entity.TbEvent
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface TbEventRepository: JpaRepository<TbEvent, Long> {
    @Query("""
        from TbEvent e
        where e.eventName = :eventName
    """)
    fun findSameEvent(eventName: String): List<TbEvent>
}