package com.toy.coupon.api.mvc.repository

import com.toy.coupon.api.mvc.model.entity.TbCoupon
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface TbCouponRepository : JpaRepository<TbCoupon, String>, DslTbCouponRepository {
    @Query("""
        from TbCoupon c
        where c.tbEvent.eventId = :eventId
        and c.userId = :userId
    """)
    fun findCouponProvidedToUser(eventId: Long, userId: Long): List<TbCoupon>

    @Modifying
    @Query("""
        update TbCoupon c
        set c.userId = :userId
        where c.tbEvent.eventId = :eventId
        and c.couponId = :couponId
    """)
    fun provideCoupon(eventId: Long, couponId: Long, userId: Long)
}

interface DslTbCouponRepository {
    fun findAllocableCoupon(eventId: Long): TbCoupon?
}